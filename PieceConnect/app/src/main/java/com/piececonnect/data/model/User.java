package com.piececonnect.data.model;

import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.GeoPoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	private String id;
	private String name;
	private String email;
	private String role; // employer or jobseeker
	private List<String> skills;
	private String locationCity;
	private GeoPoint location;
	private Double rating;
	private String photoUrl;
	@ServerTimestamp
	private Date createdAt;
	private List<String> deviceTokens;
	private List<String> blockedUserIds;
	private Boolean isVerified;

	public User() { }

	public User(String id, String name, String email, String role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
		this.skills = new ArrayList<>();
		this.deviceTokens = new ArrayList<>();
		this.blockedUserIds = new ArrayList<>();
		this.isVerified = false;
	}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getRole() { return role; }
	public void setRole(String role) { this.role = role; }
	public List<String> getSkills() { return skills; }
	public void setSkills(List<String> skills) { this.skills = skills; }
	public String getLocationCity() { return locationCity; }
	public void setLocationCity(String locationCity) { this.locationCity = locationCity; }
	public GeoPoint getLocation() { return location; }
	public void setLocation(GeoPoint location) { this.location = location; }
	public Double getRating() { return rating; }
	public void setRating(Double rating) { this.rating = rating; }
	public String getPhotoUrl() { return photoUrl; }
	public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
	public Date getCreatedAt() { return createdAt; }
	public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
	public List<String> getDeviceTokens() { return deviceTokens; }
	public void setDeviceTokens(List<String> deviceTokens) { this.deviceTokens = deviceTokens; }
	public List<String> getBlockedUserIds() { return blockedUserIds; }
	public void setBlockedUserIds(List<String> blockedUserIds) { this.blockedUserIds = blockedUserIds; }
	public Boolean getVerified() { return isVerified; }
	public void setVerified(Boolean verified) { isVerified = verified; }
}