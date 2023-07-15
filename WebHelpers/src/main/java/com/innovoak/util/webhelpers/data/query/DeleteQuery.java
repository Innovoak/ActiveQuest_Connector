package com.innovoak.util.webhelpers.data.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.innovoak.util.webhelpers.criteria.BranchCriteria;
import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.criteria.LeafCriteria;
import com.innovoak.util.webhelpers.criteria.PredicateCriteria;
import com.innovoak.util.webhelpers.data.Query;

// delete statements - DELETE FROM <table> (WHERE <conditions>)
public class DeleteQuery implements Query {
	private static final long serialVersionUID = 1L;
	// The sql statement
	private String sql;

	// (idk purpose help)
	// Parameters from criteria
	private List<Object> params;

	// Serialization purposes only
	public DeleteQuery() {
		// (idk purpose)
		// Create
		params = Collections.emptyList();
	}

	// Serialization purposes only
	public DeleteQuery(String sql) {
		this();
		this.sql = sql;
	}

	// Public constructor
	public DeleteQuery(String tableName, PredicateCriteria criteria) {

		// Build the sql
		sql = new StringBuilder().append("DELETE ").append(" FROM ").append(tableName)
				.append(criteria == null || criteria.equals(Criteria.NoneHolder.NONE) ? "" : " WHERE " + criteria)
				.toString();

		// Get params
		getParams(criteria);
	}

	// (rough understanding of DFS but idk how it's used here + idk leaf and branch
	// criteria)
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
	public void execute(Connection connection) throws SQLException {

		// Create a prepared statement
		PreparedStatement ps = connection.prepareStatement(sql);

		// Set the params
		for (int i = 0; i < params.size(); i++)
			ps.setObject(i + 1, params.get(i));

		// idk executeUpdate
		ps.executeUpdate(sql);

		// Close prepared statements
		ps.close();

	}

}