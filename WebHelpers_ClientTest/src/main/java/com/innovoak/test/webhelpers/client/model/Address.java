package com.innovoak.test.webhelpers.client.model;

import java.util.Objects;

import com.innovoak.util.webhelpers.data.Model;

public class Address extends Model {
	private static final long serialVersionUID = 1L;

	private String city;
	private String country;
	private String zip;
	private String streetAddress;
	private String userID;

	public Address() {
	}

	public Address(String city, String country, String zip, String streetAddress, String userID) {
		this.city = city;
		this.country = country;
		this.zip = zip;
		this.streetAddress = streetAddress;
		this.userID = userID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(city, country, streetAddress, userID, zip);
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
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(streetAddress, other.streetAddress) && Objects.equals(userID, other.userID)
				&& Objects.equals(zip, other.zip);
	}

}
