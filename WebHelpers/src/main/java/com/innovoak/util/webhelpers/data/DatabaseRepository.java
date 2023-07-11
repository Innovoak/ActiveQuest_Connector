package com.innovoak.util.webhelpers.data;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import com.innovoak.util.webhelpers.Repository;
import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.criteria.predicate.PredicateCriteria;

// Will create a persisted object into database... ignores any transient values
// To access classes of other models or tables.. we must parse them too and figure out the table they come from

// mappings will occur where
// 
public abstract class DatabaseRepository<T extends Serializable> implements Repository<T> {
	// Current session
	private DatabaseSession session;

	// Create a database repo from this constructor
	public DatabaseRepository(DatabaseSession session) {
		this.session = session;
	}

	// Check if the connection is closed
	public void checkClosed() throws Exception {
		if (session.isClosed())
			throw new IllegalAccessError("Session is already closed");
	}

	@Override
	public List<T> getAllBy(SelectCriteria criteria) throws Exception {
		checkClosed();
		// TODO: FINISH

		return null;
	}

	@Override
	public void deleteAllBy(PredicateCriteria criteria) throws Exception {
		checkClosed();
		// TODO Auto-generated method stub

	}

	@Override
	public void insertAll(List<T> objects) throws Exception {
		checkClosed();
		// TODO Auto-generated method stub
		// Use batch statements here

	}

	@Override
	public void updateAllBy(T object, PredicateCriteria criteria) throws Exception {
		checkClosed();
		// TODO Auto-generated method stub

	}

	// Get the connection from the session
	protected final Connection getConnection() throws Exception {
		return session.getConnection();
	}
	
	// Table specific data
	protected abstract String getTableName();

	// Create a new instance of the object
	protected abstract T newInstance();
}
