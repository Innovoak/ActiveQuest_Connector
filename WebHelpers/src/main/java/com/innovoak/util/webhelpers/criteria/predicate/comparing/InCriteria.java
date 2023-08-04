package com.innovoak.util.webhelpers.criteria.predicate.comparing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.data.query.SelectQuery;

public class InCriteria implements ComparisonOperator {
	private static final long serialVersionUID = 1L;

	private String column;
	private List<Object> values;
	private SelectQuery selectQuery; // Additional field for select query

	// Constructor for collection of values
	public InCriteria(String column, Iterable<Object> values) {
		this.column = column;
		this.values = new ArrayList<>();
		for (Object value : values) {
			this.values.add(value);
		}
	}

	// Constructor for select query
	public InCriteria(String column, SelectQuery selectQuery) {
		this.column = column;
		this.selectQuery = selectQuery;
	}

	public InCriteria() {
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

	public SelectQuery getSelectQuery() {
		return selectQuery;
	}

	public void setSelectQuery(SelectQuery selectQuery) {
		this.selectQuery = selectQuery;
	}

	@Override
	public String toString() {
		if (selectQuery != null) {
			// If it's a select query, include its SQL in the criteria
			return String.format("%s IN (%s)", column, selectQuery.getSql());
		} else {
			// If it's a collection of values, format the IN statement accordingly
			StringBuilder sb = new StringBuilder(column);
			sb.append(" IN (");
			for (int i = 0; i < values.size(); i++) {
				sb.append("?");
				if (i < values.size() - 1) {
					sb.append(", ");
				}
			}
			sb.append(")");
			return sb.toString();
		}
	}

	@Override
	public List<Object> getParameters() {
		if (selectQuery != null) {
			// If it's a select query, return its parameters
			return selectQuery.getParams();
		} else {
			// If it's a collection of values, return them as parameters
			if (values.isEmpty()) {
				return Collections.emptyList();
			}
			return new ArrayList<>(values);
		}
	}

	@Override
	public Predicate<Object> toPredicate() {
		if (values == null)
			throw new UnsupportedOperationException(
					"This feature has not been implemented to support embedded queries");
		return e -> {
			e = Criteria.getFromColumn(column, e);
			return values.contains(e);
		};
	}
}