package com.innovoak.test.webhelpers.client.repository;

import java.net.MalformedURLException;
import java.net.URL;

import com.innovoak.test.webhelpers.client.model.Address;
import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.util.webhelpers.client.ClientRepository;

public class AddressClientRepository extends ClientRepository<Address> {

	// Keep repository URL
	@Override
	protected URL getRepositoryURL() throws MalformedURLException {
		return new URL("http://localhost:8080/WebHelpers_ServerTest/address");
	}

}
