package com.innovoak.util.webhelpers.data.query;

import com.innovoak.util.webhelpers.data.DatabaseSession;

/*
Should accurately represent all the statements

select statements - SELECT (DISTINCT) <columns> FROM <table> (WHERE <conditions>) (LIMIT <number>) (ORDER BY <columns (ASC | DESC)>)
insert statements - INSERT INTO <table> (<columns>) VALUES (<column values>)
update statements - DELETE FROM <table> (WHERE <conditions>)
delete statements - UPDATE <table> SET <column = value> (WHERE <condition>)

 */
public abstract class Query implements Iterable<Object[]> {
	// Session
	private final DatabaseSession session;

	public Query(DatabaseSession session) {
		this.session = session;
	}

	// Executes the query
	public abstract void execute();

	// Get the session
	public DatabaseSession getSession() {
		return session;
	}

}
