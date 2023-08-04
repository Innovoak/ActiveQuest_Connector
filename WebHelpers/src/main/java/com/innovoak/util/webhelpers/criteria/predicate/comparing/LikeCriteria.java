package com.innovoak.util.webhelpers.criteria.predicate.comparing;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LikeCriteria implements ComparisonOperator {

	private static final long serialVersionUID = 1L;

	// Column
	private String column;
	// Pattern
	private String pattern;

	// Constructor
	public LikeCriteria(String column, String pattern) {
		super();
		this.column = column;
		this.pattern = pattern;
	}

	// No Argument Constructor
	public LikeCriteria() {

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

	// toString
	@Override
	public String toString() {
		return String.format("%s LIKE ?", column);
	}

	// Parameters
	@Override
	public List<Object> getParameters() {
		return Arrays.asList(pattern);
	}

	@Override
	public Predicate<Object> toPredicate() {
		throw new UnsupportedOperationException("This feature has not been implemented yet");
	}

}
