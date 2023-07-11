package com.innovoak.util.webhelpers.data;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

/*
Should accurately represent all the statements

select statements - SELECT (DISTINCT) <columns> FROM <table> (WHERE <conditions>) (LIMIT <number>) (ORDER BY <columns (ASC | DESC)>)
insert statements - INSERT INTO <table> (<columns>) VALUES (<column values>)
update statements - DELETE FROM <table> (WHERE <conditions>)
delete statements - UPDATE <table> SET <column = value> (WHERE <condition>)

 */
public interface Query extends Serializable {

	// Executes the query
	public default void execute(DatabaseSession session) throws Exception {

		// Execute query
		execute(session.getConnection());

	}

	public void execute(Connection connection) throws SQLException;

}
