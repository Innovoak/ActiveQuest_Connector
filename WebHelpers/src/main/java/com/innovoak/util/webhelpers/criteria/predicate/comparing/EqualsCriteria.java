package com.innovoak.util.webhelpers.criteria.predicate.comparing;

import java.util.Arrays;
import java.util.List;

// Equals criteria
public class EqualsCriteria implements ComparisonOperator {
	private static final long serialVersionUID = 1L;

	// Column
	private String column;
	// Value
	private Object value;

	// Constructor
	public EqualsCriteria(String column, Object value) {
		super();
		this.column = column;
		this.value = value;
	}

	// To string and parameters
	@Override
	public String toString() {
		return String.format("%s = ?", column);
	}

	@Override
	public List<Object> getParameters() {
		return Arrays.asList(value);
	}

}
