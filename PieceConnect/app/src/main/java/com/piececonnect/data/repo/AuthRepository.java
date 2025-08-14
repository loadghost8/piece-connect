package com.piececonnect.data.repo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthRepository {
	private final FirebaseAuth auth = FirebaseAuth.getInstance();

	public Task<AuthResult> signIn(String email, String password) {
		return auth.signInWithEmailAndPassword(email, password);
	}

	public Task<AuthResult> signUp(String email, String password) {
		return auth.createUserWithEmailAndPassword(email, password);
	}

	public void signOut() {
		auth.signOut();
	}

	public String getCurrentUserId() {
		return auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : null;
	}
}