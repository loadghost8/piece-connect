package com.piececonnect.data.repo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.piececonnect.data.model.User;

public class UserRepository {
	private final FirebaseFirestore db = FirebaseFirestore.getInstance();

	public Task<Void> upsertUser(User user) {
		DocumentReference ref = db.collection("users").document(user.getId());
		return ref.set(user);
	}

	public Task<DocumentSnapshot> getUser(String uid) {
		return db.collection("users").document(uid).get();
	}
}