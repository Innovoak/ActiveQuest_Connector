package com.innovoak.test.webhelpers.server.dao;

import com.innovoak.test.webhelpers.client.model.Profile;
import com.innovoak.util.webhelpers.data.DatabaseRepository;
import com.innovoak.util.webhelpers.data.DatabaseSession;

public class ProfileRepository extends DatabaseRepository<Profile> {

	public ProfileRepository(DatabaseSession session) {
		super(session);
	}

	@Override
	public String getTableName() {
		return "Profile";
	}

	@Override
	public Profile newInstance() {
		return new Profile();
	}

}
