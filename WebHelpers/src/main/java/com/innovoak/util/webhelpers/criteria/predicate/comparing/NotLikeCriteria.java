package com.innovoak.util.webhelpers.criteria.predicate.comparing;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class NotLikeCriteria implements ComparisonOperator {

	private static final long serialVersionUID = 1L;

	// column
	private String column;
	// pattern
	private String pattern;

	// Constructor
	public NotLikeCriteria(String column, String pattern) {
		super();
		this.column = column;
		this.pattern = pattern;
	}

	// No Argument Constructor
	public NotLikeCriteria() {

	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	// To String
	@Override
	public String toString() {
		return String.format("%s NOT LIKE ?", column);
	}

	// parameters
	@Override
	public List<Object> getParameters() {
		return Arrays.asList(pattern);
	}

	@Override
	public Predicate<Object> toPredicate() {
		throw new UnsupportedOperationException("This feature has not been implemented yet");
	}

}
