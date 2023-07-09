package com.innovoak.util.webhelpers.data.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.criteria.PredicateCriteria;
import com.innovoak.util.webhelpers.data.Columns;
import com.innovoak.util.webhelpers.data.DatabaseSession;
import com.innovoak.util.webhelpers.data.Query;

// select statements - SELECT (DISTINCT) <columns> FROM <table> (WHERE <conditions>) (LIMIT <number>) (ORDER BY <columns (ASC | DESC)>)
public final class SelectQuery implements Query<Map<String, Object>> {

	// The sql statement
	private String sql;
	private boolean executed = false;

	// protected constructor
	protected SelectQuery(String sql) {
		this.sql = sql;
	}

	// Public constructor
	public SelectQuery(boolean distinct, Columns columns, String tableName, Criteria criteria) {
		// Build the sql
		sql = new StringBuilder().append("SELECT ").append(distinct ? "DISTINCT " : "").append(columns.toString())
				.append(" FROM ").append(tableName)
				.append(criteria == Criteria.NoneHolder.NONE ? "" : " WHERE " + criteria.toString()).toString();

	}

	@Override
	public Iterator<Map<String, Object>> iterator() {
		// make sure that this has executed
		if (!executed)
			throw new IllegalStateException("Data has not been loaded yet");
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		// Prepare the statement
		PreparedStatement ps = connection.prepareStatement(sql);
	}

}
