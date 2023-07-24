package com.innovoak.test.webhelpers.client.model;

import java.sql.JDBCType;
import java.util.Objects;

import com.innovoak.util.webhelpers.data.Model;
import com.innovoak.util.webhelpers.data.annotations.Column;
import com.innovoak.util.webhelpers.data.annotations.Table;

@Table(name = "users")
public class User extends Model {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String profileID;

	public User() {
	}

	public User(String username, String password, String profileID) {
		super();
		this.username = username;
		this.password = password;
		this.profileID = profileID;
	}

	public User(String id, String username, String password, String profileID) {
		super(id);
		this.username = username;
		this.password = password;
		this.profileID = profileID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(columnName = "profile_id", unique = true)
	public String getProfileID() {
		return profileID;
	}

	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(password, profileID, username);
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
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(profileID, other.profileID)
				&& Objects.equals(username, other.username);
	}

}
