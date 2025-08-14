package com.piececonnect.data.repo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.piececonnect.data.model.Job;

public class JobRepository {
	private final FirebaseFirestore db = FirebaseFirestore.getInstance();
	private final CollectionReference jobs = db.collection("jobs");

	public Query listOpenJobs() {
		return jobs.whereEqualTo("status", "open").orderBy("datePosted", Query.Direction.DESCENDING).limit(50);
	}

	public Task<DocumentReference> createJob(Job job) {
		return jobs.add(job);
	}
}