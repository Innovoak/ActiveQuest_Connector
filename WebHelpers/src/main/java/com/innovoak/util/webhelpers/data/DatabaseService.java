package com.innovoak.util.webhelpers.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

import com.innovoak.util.webhelpers.ClasspathUtils;

// Singleton class for database service
@SuppressWarnings("rawtypes")
public final class DatabaseService implements AutoCloseable {
	// Singleton values
	private static volatile DatabaseService service;
	private static final Map<Class<?>, Class<? extends DatabaseRepository>> TOTAL_REPOSITORIES;

	// Create the repositories
	static {
		// Create a map to house the repositories
		HashMap<Class<?>, Class<? extends DatabaseRepository>> repositoryClasses = new HashMap<>();

		try {
			// Go through all concrete implementations of DatabaseRepository
			for (Class<? extends DatabaseRepository> clazz : ClasspathUtils
					.findAllConcreteInstances(DatabaseRepository.class)) {
				// Add this to the repositoryClasses
				repositoryClasses.put(clazz.getMethod("newInstance").getReturnType(), clazz);
			}
		} catch (Exception e) {
			throw new RuntimeException("This is not supposed to happen");
		}

		// Add the repositories
		TOTAL_REPOSITORIES = Collections.unmodifiableMap(repositoryClasses);

	}

	// States
	private boolean closed = true;
	private BasicDataSource ds;

	// Create a new Database service
	private DatabaseService() {
		if (service == null) {
			return;
		}

		// Singletn stuff
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

	// If the datasource is not already created, create it
	public void open() {
		// If there is no data source
		if (ds != null) {
			// Null check
			if (configuration == null)
				throw new NullPointerException("Database configuration cannot be null");

			// Create the datasource
			ds = new BasicDataSource();
			// Set properties
			ds.setDefaultAutoCommit(configuration.isDefaultAutoCommit());
			ds.setDriverClassName(configuration.getDriverClassName());
			ds.setUrl(configuration.getUrl());
			ds.setMinIdle(configuration.getMinIdle());
			ds.setMaxIdle(configuration.getMaxIdle());
			ds.setMaxOpenPreparedStatements(configuration.getMaxOpenPreparedStatements());
			ds.setUsername(configuration.getUsername());
			ds.setPassword(configuration.getPassword());
			ds.setPoolPreparedStatements(configuration.isPoolPreparedStatements());

			// set closed as false
			closed = false;
		}
	}

	// Create a new session
	public DatabaseSession createSession() throws SQLException {
		// Check access
		if (closed)
			throw new IllegalAccessError("Service has is not open");

		// Return a new instance
		return new DatabaseSession(service);
	}

	@Override
	public void close() throws Exception {
		// Check if opened and not closed
		if (!closed) {
			// Close
			ds.close();
			closed = true;
			ds = null;
		}

	}

	// Get the connection for the database session
	protected Connection openConnection() throws SQLException {
		return ds.getConnection();
	}

	// Get the repository class
	// TODO: get or default a regular simple database repository
	protected static Map<Class<?>, Class<? extends DatabaseRepository>> getRepositoryMap() {
		return TOTAL_REPOSITORIES;
	}

	// Fields related to the datasource
	// Database Configuration
	// Default configuation - bound to run into null pointers
	private Configuration configuration;

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}
