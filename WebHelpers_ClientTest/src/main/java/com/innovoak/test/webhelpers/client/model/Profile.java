package com.innovoak.test.webhelpers.client.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String email;
	private String bio;
	private Date dob;
	private boolean isStudent;

	public Profile() {
	}

	public Profile(String name, String email, String bio, Date dob, boolean isStudent) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.email = email;
		this.bio = bio;
		this.dob = dob;
		this.isStudent = isStudent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

}
