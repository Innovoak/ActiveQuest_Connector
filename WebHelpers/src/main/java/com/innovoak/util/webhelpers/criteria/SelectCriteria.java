package com.innovoak.util.webhelpers.criteria;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.innovoak.util.webhelpers.criteria.predicate.PredicateCriteria;

// USED FOR ONLY SELECT STATEMENTS - DO NOT USE FOR DELETE OR UPDATE
// Represents the criteria (where clause) along with limit and order by
// Should be the root node
public class SelectCriteria implements BranchCriteria {
	private static final long serialVersionUID = 1L;
	// Where clause
	private Criteria criteria;
	// Limit number
	private int limit = -1;
	// Linked hash map for columns to preserve order
	private LinkedHashMap<String, Boolean> orderBy;

	// Constructor
	public SelectCriteria(PredicateCriteria criteria, int limit, LinkedHashMap<String, Boolean> orderBy) {
		super();
		this.criteria = criteria;
		this.limit = limit;
		this.orderBy = orderBy;
	}

	// Getters and Setters
	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public LinkedHashMap<String, Boolean> getOrderBy() {
		return orderBy;
	}

	// Make this a branch criteria
	@Override
	public List<Criteria> getNodeCriteria() {
		return Arrays.asList(criteria);
	}

	@Override
	public String toString() {
		// Create the string builder
		StringBuilder clause = new StringBuilder(criteria.toString()).append(" ");

		// Make sure limit is greater than 0
		if (limit > 0)
			clause = clause.append(String.format("LIMIT %d ", limit));

		// Check if the map exists and has values
		if (orderBy != null && !orderBy.isEmpty()) {
			// Add order by
			clause = clause.append("ORDER BY ");

			// Set order
			for (Entry<String, Boolean> entry : orderBy.entrySet())
				clause = clause.append(entry.getKey()).append(" ").append(entry.getValue() ? "DESC" : "ASC")
						.append(" ");
		}

		// Trim to remove any unnecessary whitespaces
		return clause.toString();
	}

}
