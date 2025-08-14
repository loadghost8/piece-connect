package com.piececonnect.data.model;

import com.google.firebase.firestore.ServerTimestamp;
import java.util.Date;

public class Application {
	private String id;
	private String jobId;
	private String seekerId;
	private String status; // pending, accepted, rejected
	private String message;
	@ServerTimestamp
	private Date createdAt;

	public Application() { }

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getJobId() { return jobId; }
	public void setJobId(String jobId) { this.jobId = jobId; }
	public String getSeekerId() { return seekerId; }
	public void setSeekerId(String seekerId) { this.seekerId = seekerId; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }
	public Date getCreatedAt() { return createdAt; }
	public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}