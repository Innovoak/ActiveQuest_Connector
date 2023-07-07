package com.innovoak.util.webhelpers.data;

import java.io.Serializable;
import java.util.List;

import com.innovoak.util.webhelpers.Repository;
import com.innovoak.util.webhelpers.criteria.Criteria;

// Will create a persisted object into database... ignores any transient values
// To access classes of other models or tables.. we must parse them too and figure out the table they come from

// mappings will occur where
// 
public abstract class DatabaseRepository<T extends Serializable> implements Repository<T> {
	private DatabaseSession session;
	// Use Query API

	public DatabaseRepository(DatabaseSession session) {
		this.session = session;
	}

	@Override
	public List<T> getAllBy(Criteria criteria) throws Exception {

//		String query = String.format("select %s from %s", );
		// Placeholder
		session.createQuery(Columns.ALL, criteria);

		return null;
	}

	@Override
	public void deleteAllBy(Criteria criteria) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(T object) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAllBy(T object, Criteria criteria) throws Exception {
		// TODO Auto-generated method stub

	}

	// Table specific data
	abstract String getTableName();

	abstract List<String> getColumnNames();

	abstract T newInstance();
}
