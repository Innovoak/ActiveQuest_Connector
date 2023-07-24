package com.innovoak.test.webhelpers.client.model;

import java.sql.Date;
import java.sql.JDBCType;
import java.util.Objects;

import com.innovoak.util.webhelpers.data.Model;
import com.innovoak.util.webhelpers.data.annotations.Column;
import com.innovoak.util.webhelpers.data.annotations.Table;

@Table(name = "profiles")
public class Profile extends Model {
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String bio;
	private Date dob;
	private boolean isStudent;

	public Profile() {
	}

	public Profile(String name, String email, String bio, Date dob, boolean isStudent) {
		this.name = name;
		this.email = email;
		this.bio = bio;
		this.dob = dob;
		this.isStudent = isStudent;
	}

	public Profile(String id, String name, String email, String bio, Date dob, boolean isStudent) {
		super(id);
		this.name = name;
		this.email = email;
		this.bio = bio;
		this.dob = dob;
		this.isStudent = isStudent;
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

	@Column(columnName = "dob", type = JDBCType.DATE)
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(columnName = "is_student")
	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(bio, dob, email, isStudent, name);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		return Objects.equals(bio, other.bio) && Objects.equals(dob, other.dob) && Objects.equals(email, other.email)
				&& isStudent == other.isStudent && Objects.equals(name, other.name);
	}

}
