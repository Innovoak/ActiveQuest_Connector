package com.innovoak.util.webhelpers.data;

import com.innovoak.util.webhelpers.criteria.Criteria;

// TODO: Fix all this and BUild the rest of the api
public class Query implements AutoCloseable {

	// First ctor
	// Any statements
	public Query(DatabaseSession session, String sql) {

	}
	
	// Select statements only
	// Second ctor
	public Query(DatabaseSession session, String type, Object... params) {

	}

	// Select statements only
	// Second ctor
	public Query(DatabaseSession session, Columns columns, Criteria criteria) {

	}

	// TODO: 2nd ctor would be based on session with criteria if applicable

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

}
