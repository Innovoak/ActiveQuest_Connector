package com.innovoak.util.webhelpers.data.query;

import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.innovoak.util.webhelpers.SQLUtils;
import com.innovoak.util.webhelpers.criteria.BranchCriteria;
import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.criteria.LeafCriteria;
import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.data.Columns;
import com.innovoak.util.webhelpers.data.Query;

// select statements - SELECT (DISTINCT) <columns> FROM <table> (WHERE <conditions>) (LIMIT <number>) (ORDER BY <columns (ASC | DESC)>)
//                 bolean distinct - Columns columns - String tableName - Criteria criteria
public final class SelectQuery implements Query<Map<String, Object>> {
	private static final long serialVersionUID = 1L;
	// The sql statement
	private String sql;
	private transient List<Map<String, Object>> iterable;

	// Parameters from criteria
	private List<Object> params;

	// Serialization purposes only
	public SelectQuery() {
		// Create
		params = Collections.emptyList();
	}

	// protected constructor
	protected SelectQuery(String sql) {
		this();
		// Sql
		this.sql = sql;

	}

	// Public constructor
	public SelectQuery(boolean distinct, Columns columns, String tableName, SelectCriteria criteria) {

		// Build the sql
		sql = new StringBuilder().append("SELECT ").append(distinct ? "DISTINCT " : "").append(columns.toString())
				.append(" FROM ").append(tableName).append(criteria == null ? "" : criteria).toString();

		// Get params
		getParams(criteria);
	}

	// Uses DFS algorithm to get params from criteria
	public void getParams(Criteria criteria) {
		// Base case
		if (criteria instanceof LeafCriteria) {
			// Get parameters
			params.addAll(((LeafCriteria) criteria).getParameters());

			// Recursive call
		} else {
			// Get the branch
			BranchCriteria branch = (BranchCriteria) criteria;

			// Get its nodes
			for (Criteria criterion : branch.getNodeCriteria())
				getParams(criterion);
		}
	}

	@Override
	public Iterator<Map<String, Object>> iterator() {
		// make sure that this has executed
		if (iterable == null)
			throw new IllegalStateException("Data has not been loaded yet");

		// Return the list iterator
		return iterable.iterator();
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		// Prepare the statement
		PreparedStatement ps = connection.prepareStatement(sql);

		// Set the params
		for (int i = 0; i < params.size(); i++)
			ps.setObject(i, params.get(i));

		// Execute query
		ResultSet rs = ps.executeQuery();

		// Get columns
		Set<String> columns = SQLUtils.getColumnNames(rs.getMetaData());

		// Create a new linked list
		iterable = new LinkedList<>();

		// While there are values in the result set
		while (rs.next()) {
			// Create a map
			Map<String, Object> row = new HashMap<>();

			// Populate it with result set data
			for (String column : columns)
				row.put(column, rs.getObject(column));

			// add the map to the list
			iterable.add(row);
		}

		// Make it unmodifiable
		iterable = Collections.unmodifiableList(iterable);

		// Close the result set and preparedstatements
		rs.close();
		ps.close();
	}

}
