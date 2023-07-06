package com.raos.test.webhelpers.client.repository;

import java.net.MalformedURLException;
import java.net.URL;

import com.raos.test.webhelpers.client.model.User;
import com.raos.util.webhelpers.client.ClientRepository;

public class UserClientRepository extends ClientRepository<User> {

	// Keep repository URL
	@Override
	protected URL getRepositoryURL() {
		try {
			return new URL("http://localhost:8080/WebHelpers/test/user");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

}
