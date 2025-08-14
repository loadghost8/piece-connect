package com.piececonnect.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
	@Override
	public void onNewToken(String token) {
		String uid = FirebaseAuth.getInstance().getCurrentUser() != null ? FirebaseAuth.getInstance().getCurrentUser().getUid() : null;
		if (uid == null) return;
		FirebaseFirestore.getInstance()
			.collection("users").document(uid)
			.update("deviceTokens", FieldValue.arrayUnion(token));
	}

	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		// Handle notifications in-app if needed
	}
}