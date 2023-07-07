package com.innovoak.util.webhelpers.criteria;

import java.io.Serializable;

// represents a criteria
// TODO: Build the rest of the API
public interface Criteria extends Serializable {
	
	// None criteria
	public static enum NoneHolder implements Criteria {
		NONE
	}
	
	
	// Type of criteria
	public static enum Type {
		EQUALS, GREATER_THAN, LESS_THAN
	}

	// Equals criteria
	public static Criteria equalsCriteria(String column, Serializable value) {
		return new PredicateCriteria(Type.EQUALS, column, value);
	}

	// Greater than criteria
	public static Criteria greaterThanCriteria(String column, Serializable value) {
		return new PredicateCriteria(Type.GREATER_THAN, column, value);
	}

	// Less than criteria
	public static Criteria lessThanCriteria(String column, Serializable value) {
		return new PredicateCriteria(Type.LESS_THAN, column, value);
	}

	// And criteria
	public static Criteria and(Criteria left, Criteria right) {
		return new AndCriteria(left, right);
	}

	// Or criteria
	public static Criteria or(Criteria left, Criteria right) {
		return new OrCriteria(left, right);
	}

	// Not criteria
	public static Criteria not(Criteria value) {
		return new NotCriteria(value);
	}

	// From the range of lower to higher
	public static Criteria range(String column, Serializable lower, Serializable higher) {
		return and(lessThanCriteria(column, higher), greaterThanCriteria(column, lower));
	}

	// Not in the range of lower to higher
	public static Criteria notRange(String column, Serializable lower, Serializable higher) {
		return not(range(column, lower, higher));
	}

	// Greater than equals criteria
	public static Criteria greaterThanEqualsCriteria(String column, Serializable value) {
		return or(equalsCriteria(column, value), greaterThanCriteria(column, value));
	}

	// Less than equals criteria
	public static Criteria lessThanEqualsCriteria(String column, Serializable value) {
		return or(equalsCriteria(column, value), lessThanCriteria(column, value));
	}

	// Not equals criteria
	public static Criteria notEqualsCriteria(String column, Serializable value) {
		return not(equalsCriteria(column, value));
	}
	
	String toString();
}
