package com.innovoak.util.webhelpers.data;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.innovoak.util.webhelpers.criteria.Criteria;

// Acts as a session or connection to the database
public class DatabaseSession implements AutoCloseable {

	// Fields
	private Map<Class<?>, DatabaseRepository<?>> repositories;
	private Connection connection;

	public DatabaseSession(DatabaseService service) throws SQLException {
		connection = service.openConnection();
	}

	// Getting a database repository
	@SuppressWarnings("unchecked")
	public <T extends Serializable> DatabaseRepository<T> getRepository(Class<T> clazz) {
		// Check whether the repository contains the class associated
		if (repositories.containsKey(clazz))
			return (DatabaseRepository<T>) repositories.get(clazz);

		// Otherwise create a repository of that class
		return null;
	}

	// Creates a new query
	public Query createQuery(String sql) {
		return QueryBuilder.create(this).setCommand(sql).build();
	}

	// TODO: Fix so that its a Columns class instead of a string array
	// Creates a new query
	public Query createQuery(Columns columns, Criteria criteria) {
		return QueryBuilder.create(this).setCriteria(columns, criteria).build();
	}

	@Override
	public void close() throws Exception {
		if (!connection.isClosed())
			connection.close();
	}

}
