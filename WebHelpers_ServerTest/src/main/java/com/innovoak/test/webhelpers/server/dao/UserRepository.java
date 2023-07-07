package com.innovoak.test.webhelpers.server.dao;

import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.util.webhelpers.data.DatabaseRepository;
import com.innovoak.util.webhelpers.data.DatabaseSession;

// User Database repository
public class UserRepository extends DatabaseRepository<User> {

	// Constructor
	public UserRepository(DatabaseSession session) {
		super(session);
	}

	@Override
	public String getTableName() {
		return "user";
	}

	@Override
	public User newInstance() {
		return new User();
	}

}
