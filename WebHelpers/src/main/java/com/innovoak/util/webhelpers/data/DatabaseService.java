package com.innovoak.util.webhelpers.data;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.innovoak.util.webhelpers.data.internal.BasicPoolingDataSource;

// Singleton class for database service
public final class DatabaseService implements AutoCloseable {
	// Singleton values
	private static volatile DatabaseService service;
	private static final Map<Class<?>, Class<? extends DatabaseRepository<?>>> TOTAL_REPOSITORIES;

	static {
		// TODO: In reality we would scan classpath and add anything which implements
		// DatabaseRepository to the Total repositories
		// Anything which is a concrete class
		TOTAL_REPOSITORIES = new HashMap<>();
	}

	//
	private boolean opened, closed;
	private BasicPoolingDataSource ds;

	// Create a new Database service
	private DatabaseService() {
		if (service == null) {
			return;
		}

		throw new IllegalStateException("Already instantiated");
	}

	// Gets instance of the database service
	public static DatabaseService getInstance() {
		if (service == null) {
			synchronized (DatabaseService.class) {
				if (service == null) {
					service = new DatabaseService();
				}
			}
		}

		return service;
	}

	// Opens up the connections
	public void open(String url, Properties properties) {
		// Set opened to true
		opened = true;
	}

	// Create a new session
	public DatabaseSession createSession() throws SQLException {
		return new DatabaseSession(service);
	}

	@Override
	public void close() throws Exception {
		// Check if opened and not closed
		if (opened && !closed) {
			// Close
			((AutoCloseable) ds).close();
			closed = true;
		}

	}

	// Get the connection for the database session
	protected Connection openConnection() throws SQLException {
		return ds.getConnection();
	}

	// Get the repository class
	// TODO: get or default a regular simple database repository
	@SuppressWarnings("unchecked")
	protected static <T extends Serializable> Class<? extends DatabaseRepository<T>> getRepositoryClass(
			Class<T> clazz) {
		return (Class<? extends DatabaseRepository<T>>) TOTAL_REPOSITORIES.get(clazz);

	}
}
