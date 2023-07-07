package com.innovoak.test.webhelpers.server.dao;

import com.innovoak.test.webhelpers.client.model.Address;
import com.innovoak.util.webhelpers.data.DatabaseRepository;
import com.innovoak.util.webhelpers.data.DatabaseSession;

public class AddressRepository extends DatabaseRepository<Address> {

	public AddressRepository(DatabaseSession session) {
		super(session);
	}

	@Override
	public String getTableName() {
		return "Address";
	}

	@Override
	public Address newInstance() {
		return new Address();
	}

}
