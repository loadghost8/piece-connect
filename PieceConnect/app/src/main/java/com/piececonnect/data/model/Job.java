package com.piececonnect.data.model;

import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.GeoPoint;
import java.util.Date;
import java.util.List;

public class Job {
	private String id;
	private String title;
	private String description;
	private Double pay;
	private String payUnit; // hour, job, etc.
	private String locationCity;
	private GeoPoint location;
	private String employerId;
	private Date deadline;
	@ServerTimestamp
	private Date datePosted;
	private String jobType;
	private String status; // open, closed
	private List<String> tags;
	private Long applicantsCount;

	public Job() { }

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public Double getPay() { return pay; }
	public void setPay(Double pay) { this.pay = pay; }
	public String getPayUnit() { return payUnit; }
	public void setPayUnit(String payUnit) { this.payUnit = payUnit; }
	public String getLocationCity() { return locationCity; }
	public void setLocationCity(String locationCity) { this.locationCity = locationCity; }
	public GeoPoint getLocation() { return location; }
	public void setLocation(GeoPoint location) { this.location = location; }
	public String getEmployerId() { return employerId; }
	public void setEmployerId(String employerId) { this.employerId = employerId; }
	public Date getDeadline() { return deadline; }
	public void setDeadline(Date deadline) { this.deadline = deadline; }
	public Date getDatePosted() { return datePosted; }
	public void setDatePosted(Date datePosted) { this.datePosted = datePosted; }
	public String getJobType() { return jobType; }
	public void setJobType(String jobType) { this.jobType = jobType; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public List<String> getTags() { return tags; }
	public void setTags(List<String> tags) { this.tags = tags; }
	public Long getApplicantsCount() { return applicantsCount; }
	public void setApplicantsCount(Long applicantsCount) { this.applicantsCount = applicantsCount; }
}