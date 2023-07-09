package com.innovoak.util.webhelpers.data.query;

import com.innovoak.util.webhelpers.data.DatabaseSession;

/*
Should accurately represent all the statements

select statements - SELECT (DISTINCT) <columns> FROM <table> (WHERE <conditions>) (LIMIT <number>) (ORDER BY <columns (ASC | DESC)>)
insert statements - INSERT INTO <table> (<columns>) VALUES (<column values>)
update statements - DELETE FROM <table> (WHERE <conditions>)
delete statements - UPDATE <table> SET <column = value> (WHERE <condition>)

 */
public interface Query extends Iterable<Object[]> {

	// Executes the query
	public void execute(DatabaseSession session);

}
