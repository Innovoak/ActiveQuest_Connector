package com.innovoak.util.webhelpers.criteria.predicate.comparing;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.innovoak.util.webhelpers.criteria.Criteria;

// Equals criteria
public class EqualsCriteria implements ComparisonOperator {
	private static final long serialVersionUID = 1L;

	// Column
	private String column;
	// Value
	private Object value;

	// Constructor
	public EqualsCriteria() {
	}

	public EqualsCriteria(String column, Object value) {
		super();
		this.column = column;
		this.value = value;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
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

	@Override
	public Predicate<Object> toPredicate() {
		return e -> {
			e = Criteria.getFromColumn(column, e);
			return e.equals(value);
		};
	}

}
