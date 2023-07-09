package com.innovoak.util.webhelpers.data;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

// Acts as a session or connection to the database
public final class DatabaseSession implements AutoCloseable {

	// Fields
	private Map<Class<?>, DatabaseRepository<?>> repositories;
	private Connection connection;

	public DatabaseSession(DatabaseService service) throws SQLException {
		// Open a connection
		connection = service.openConnection();

		// Create the database repositories
		DatabaseService.getRepositoryMap().forEach((e, v) -> {
			// Create the database repository
			try {
				repositories.put(e, v.getConstructor(DatabaseSession.class).newInstance(this));
			} catch (Exception e1) {
				throw new RuntimeException("This shouldnt happen");
			}
		});
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

	// Close the session
	@Override
	public void close() throws Exception {
		if (!isClosed())
			connection.close();
	}

	// Check whether it is closed
	public boolean isClosed() throws Exception {
		return connection.isClosed();
	}

	// Give package access
	protected Connection getConnection() throws Exception {
		if (isClosed())
			throw new IllegalAccessException("Connection is closed");
		
		
		return connection;
	}
	
}
