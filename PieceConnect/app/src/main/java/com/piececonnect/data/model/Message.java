package com.piececonnect.data.model;

import com.google.firebase.firestore.ServerTimestamp;
import java.util.Date;
import java.util.List;

public class Message {
	private String id;
	private String senderId;
	private String content;
	private String type; // text
	@ServerTimestamp
	private Date timestamp;
	private List<String> readBy;

	public Message() { }

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getSenderId() { return senderId; }
	public void setSenderId(String senderId) { this.senderId = senderId; }
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	public Date getTimestamp() { return timestamp; }
	public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
	public List<String> getReadBy() { return readBy; }
	public void setReadBy(List<String> readBy) { this.readBy = readBy; }
}