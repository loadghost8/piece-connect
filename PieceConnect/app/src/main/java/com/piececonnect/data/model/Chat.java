package com.piececonnect.data.model;

import com.google.firebase.firestore.ServerTimestamp;
import java.util.Date;
import java.util.List;

public class Chat {
	private String id;
	private String jobId;
	private List<String> participants;
	private String lastMessage;
	private String lastSenderId;
	@ServerTimestamp
	private Date lastTimestamp;

	public Chat() { }

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getJobId() { return jobId; }
	public void setJobId(String jobId) { this.jobId = jobId; }
	public List<String> getParticipants() { return participants; }
	public void setParticipants(List<String> participants) { this.participants = participants; }
	public String getLastMessage() { return lastMessage; }
	public void setLastMessage(String lastMessage) { this.lastMessage = lastMessage; }
	public String getLastSenderId() { return lastSenderId; }
	public void setLastSenderId(String lastSenderId) { this.lastSenderId = lastSenderId; }
	public Date getLastTimestamp() { return lastTimestamp; }
	public void setLastTimestamp(Date lastTimestamp) { this.lastTimestamp = lastTimestamp; }
}