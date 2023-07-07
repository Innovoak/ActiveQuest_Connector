package com.innovoak.test.webhelpers.client.model;

import java.io.Serializable;
import java.util.UUID;

public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String city;
	private String country;
	private String zip;
	private String streetAddress;

	public Address() {
	}

	public Address(String city, String country, String zip, String streetAddress) {
		this.id = UUID.randomUUID().toString();
		this.city = city;
		this.country = country;
		this.zip = zip;
		this.streetAddress = streetAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
