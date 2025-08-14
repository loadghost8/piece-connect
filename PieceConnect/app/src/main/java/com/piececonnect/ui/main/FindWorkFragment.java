package com.piececonnect.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.piececonnect.data.model.Job;
import com.piececonnect.data.repo.JobRepository;
import com.piececonnect.databinding.FragmentFindWorkBinding;
import com.piececonnect.ui.main.adapter.JobsAdapter;
import java.util.ArrayList;
import java.util.List;

public class FindWorkFragment extends Fragment {
	private FragmentFindWorkBinding binding;
	private JobsAdapter adapter;
	private JobRepository jobRepository;
	private ListenerRegistration jobsListener;
	private List<Job> allJobs = new ArrayList<>();

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		binding = FragmentFindWorkBinding.inflate(inflater, container, false);
		return binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		adapter = new JobsAdapter(job -> {
			// TODO: navigate to job details
		});
		binding.recyclerJobs.setLayoutManager(new LinearLayoutManager(requireContext()));
		binding.recyclerJobs.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
		binding.recyclerJobs.setAdapter(adapter);

		jobRepository = new JobRepository();
		jobsListener = jobRepository.listOpenJobs().addSnapshotListener(new EventListener<QuerySnapshot>() {
			@Override
			public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
				if (error != null || value == null) return;
				List<Job> jobs = new ArrayList<>();
				for (DocumentSnapshot doc : value.getDocuments()) {
					Job job = doc.toObject(Job.class);
					if (job != null) {
						job.setId(doc.getId());
						jobs.add(job);
					}
				}
				allJobs = jobs;
				adapter.submitList(jobs);
			}
		});

		binding.inputSearch.addTextChangedListener(new TextWatcher() {
			@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override public void afterTextChanged(Editable s) {
				filterJobs(s.toString());
			}
		});
	}

	private void filterJobs(String query) {
		String q = query == null ? "" : query.toLowerCase();
		List<Job> filtered = new ArrayList<>();
		for (Job job : allJobs) {
			if ((job.getTitle() != null && job.getTitle().toLowerCase().contains(q)) ||
				(job.getDescription() != null && job.getDescription().toLowerCase().contains(q))) {
				filtered.add(job);
			}
		}
		adapter.submitList(filtered);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (jobsListener != null) jobsListener.remove();
		binding = null;
	}
}