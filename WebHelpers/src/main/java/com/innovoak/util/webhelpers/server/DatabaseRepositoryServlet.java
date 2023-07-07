package com.innovoak.util.webhelpers.server;

import java.io.Serializable;
import java.util.List;

import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.data.DatabaseService;
import com.innovoak.util.webhelpers.data.DatabaseSession;

// Wraps DatabaseRepositoryServlet
// Make sure that this class or any subclasses of this class are not scanned in classpath scanner
public abstract class DatabaseRepositoryServlet<T extends Serializable> extends RepositoryServlet<T> {
	private static final long serialVersionUID = 1L;
	// Uses database service to handle all database required stuff
	private static final DatabaseService SERVICE = DatabaseService.getInstance();
	private Class<T> clazz;

	// Constructor
	public DatabaseRepositoryServlet(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public List<T> getAllBy(Criteria criteria) throws Exception {
		try (DatabaseSession session = SERVICE.createSession()) {

			return session.getRepository(clazz).getAllBy(criteria);

		}
	}

	@Override
	public void deleteAllBy(Criteria criteria) throws Exception {
		try (DatabaseSession session = SERVICE.createSession()) {

			session.getRepository(clazz).deleteAllBy(criteria);

		}
	}

	@Override
	public void insert(T object) throws Exception {
		try (DatabaseSession session = SERVICE.createSession()) {

			session.getRepository(clazz).insert(object);

		}
	}

	// Make this abstract
	@Override
	public void insertAll(List<T> objects) throws Exception {
		try (DatabaseSession session = SERVICE.createSession()) {

			session.getRepository(clazz).insertAll(objects);

		}
	}

	@Override
	public void updateAllBy(T object, Criteria criteria) throws Exception {
		try (DatabaseSession session = SERVICE.createSession()) {

			session.getRepository(clazz).updateAllBy(object, criteria);

		}
	}

}
