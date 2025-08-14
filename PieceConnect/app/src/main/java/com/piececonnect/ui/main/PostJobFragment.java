package com.piececonnect.ui.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.piececonnect.data.model.Job;
import com.piececonnect.data.repo.JobRepository;
import com.piececonnect.databinding.FragmentPostJobBinding;
import java.util.Locale;

public class PostJobFragment extends Fragment {
	private FragmentPostJobBinding binding;
	private JobRepository jobRepository;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		binding = FragmentPostJobBinding.inflate(inflater, container, false);
		return binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		jobRepository = new JobRepository();
		binding.btnCreateJob.setOnClickListener(v -> createJob());
	}

	private void createJob() {
		String title = binding.inputTitle.getText().toString().trim();
		String desc = binding.inputDesc.getText().toString().trim();
		String payStr = binding.inputPay.getText().toString().trim();
		String city = binding.inputCity.getText().toString().trim();
		if (TextUtils.isEmpty(title) || TextUtils.isEmpty(desc)) {
			Toast.makeText(requireContext(), "Title and Description required", Toast.LENGTH_SHORT).show();
			return;
		}
		Double pay = null;
		try { if (!payStr.isEmpty()) pay = Double.parseDouble(payStr); } catch (Exception ignored) {}
		Job job = new Job();
		job.setTitle(title);
		job.setDescription(desc);
		job.setPay(pay);
		job.setPayUnit("hr");
		job.setLocationCity(city.isEmpty() ? null : city);
		job.setStatus("open");
		jobRepository.createJob(job).addOnSuccessListener(ref -> {
			Toast.makeText(requireContext(), "Job created", Toast.LENGTH_SHORT).show();
			binding.inputTitle.setText("");
			binding.inputDesc.setText("");
			binding.inputPay.setText("");
			binding.inputCity.setText("");
		}).addOnFailureListener(e -> Toast.makeText(requireContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show());
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}