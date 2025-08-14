const functions = require("firebase-functions/v2");
const admin = require("firebase-admin");
admin.initializeApp();
const db = admin.firestore();

exports.onNewMessage = functions.firestore.document("chats/{chatId}/messages/{messageId}").onCreate(async (snap, ctx) => {
	const message = snap.data();
	const chatRef = db.collection("chats").doc(ctx.params.chatId);
	const chat = (await chatRef.get()).data();
	if (!chat) return;
	const recipientId = chat.participants.find(id => id !== message.senderId);
	if (!recipientId) return;
	const userDoc = await db.collection("users").doc(recipientId).get();
	const tokens = (userDoc.data() && userDoc.data().deviceTokens) || [];
	if (tokens.length === 0) return;
	await admin.messaging().sendEachForMulticast({
		tokens,
		notification: { title: "New message", body: message.content || "New message" },
		data: { chatId: ctx.params.chatId }
	});
});

exports.onApplicationStatus = functions.firestore.document("applications/{applicationId}").onUpdate(async (change, ctx) => {
	const before = change.before.data();
	const after = change.after.data();
	if (!before || !after || before.status === after.status) return;
	const userDoc = await db.collection("users").doc(after.seekerId).get();
	const tokens = (userDoc.data() && userDoc.data().deviceTokens) || [];
	if (tokens.length === 0) return;
	await admin.messaging().sendEachForMulticast({
		tokens,
		notification: { title: "Application Update", body: `Status: ${after.status}` },
		data: { applicationId: ctx.params.applicationId, jobId: after.jobId }
	});
});