package com.innovoak.util.webhelpers.data;

import com.innovoak.util.webhelpers.builders.ConstructableBuilder;
import com.innovoak.util.webhelpers.criteria.Criteria;

// Builds the query to be evaluated by the database
public class QueryBuilder extends ConstructableBuilder<Query> {
	private DatabaseSession session;

	private QueryBuilder(DatabaseSession session) {
		super(Query.class);
		this.session = session;
	}

	// Sets the constructor args to the sql string
	public QueryBuilder setCommand(String sql) {
		setCtorArgs(session, sql);
		return this;
	}

	// Sets the constructor args to the sql string
	public QueryBuilder setCriteria(Columns columns, Criteria criteria) {
		setCtorArgs(session, columns, criteria);
		return this;
	}

	public static QueryBuilder create(DatabaseSession session) {
		return new QueryBuilder(session);
	}

}
