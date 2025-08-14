package com.piececonnect.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.piececonnect.R;
import com.piececonnect.data.model.Job;
import java.util.ArrayList;
import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobViewHolder> {
	public interface OnJobClickListener { void onJobClick(Job job); }
	private final List<Job> jobs = new ArrayList<>();
	private final OnJobClickListener listener;

	public JobsAdapter(OnJobClickListener listener) { this.listener = listener; }

	public void submitList(List<Job> newJobs) {
		jobs.clear();
		jobs.addAll(newJobs);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
		return new JobViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
		Job job = jobs.get(position);
		holder.title.setText(job.getTitle());
		holder.desc.setText(job.getDescription());
		String meta = (job.getLocationCity() != null ? job.getLocationCity() : "") +
			(job.getPay() != null ? (" • $" + job.getPay() + (job.getPayUnit() != null ? "/" + job.getPayUnit() : "")) : "");
		holder.meta.setText(meta);
		holder.itemView.setOnClickListener(v -> listener.onJobClick(job));
	}

	@Override
	public int getItemCount() { return jobs.size(); }

	static class JobViewHolder extends RecyclerView.ViewHolder {
		TextView title, desc, meta;
		JobViewHolder(@NonNull View itemView) {
			super(itemView);
			title = itemView.findViewById(R.id.title);
			desc = itemView.findViewById(R.id.desc);
			meta = itemView.findViewById(R.id.meta);
		}
	}
}