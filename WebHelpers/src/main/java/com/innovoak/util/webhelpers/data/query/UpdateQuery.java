package com.innovoak.util.webhelpers.data.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.innovoak.util.webhelpers.criteria.BranchCriteria;
import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.criteria.LeafCriteria;
import com.innovoak.util.webhelpers.criteria.PredicateCriteria;
import com.innovoak.util.webhelpers.data.Query;

// update statements - UPDATE <table> SET <column name>=<column value>... (WHERE <conditions>)
public class UpdateQuery implements Query {
	private static final long serialVersionUID = 1L;

	// The sql statement, row
	private String sql;

	// Parameters from criteria
	private List<Object> params;

	// Serialization purposes only
	public UpdateQuery() {
		// Create
		params = Collections.emptyList();
	}

	public UpdateQuery(String sql) {
		this();
		this.sql = sql;
	}

	// Public constructor
	public UpdateQuery(String tableName, Map<String, Object> valuesMap, PredicateCriteria criteria) {
		params = new LinkedList<>();

		// Turn both column and question into string
		String columnQuestionString = valuesMap.keySet().stream().map(e -> {
			params.add(valuesMap.get(e));

			return e + " = ?";
		}).collect(Collectors.joining(", "));

		// Build the sql
		sql = new StringBuilder().append("UPDATE ").append(tableName).append(" SET ").append(columnQuestionString)
				.append(criteria == Criteria.NoneHolder.NONE ? "" : " WHERE " + criteria.toString()).toString();

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
	public void execute(Connection connection) throws SQLException {

		// Create a prepared statement
		PreparedStatement ps = connection.prepareStatement(sql);

		// Go through the row
		for (int i = 0; i < params.size(); i++) {
			ps.setObject(i + 1, params.get(i));
		}

		// execute SQL query
		ps.executeUpdate();

		// Close prepared statements
		ps.close();

	}

}