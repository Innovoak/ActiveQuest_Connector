package com.raos.util.webhelpers.criteria;

import java.io.Serializable;

// Predicate criteria
public class PredicateCriteria implements Criteria {
	private static final long serialVersionUID = 1L;

	// Fields
	private final Type type;
	private final String column;
	private final Serializable value;

	// Constructor
	public PredicateCriteria(Type type, String column, Serializable value) {
		super();
		this.type = type;
		this.column = column;
		this.value = value;
	}

	// Getters and Setters
	public Type getType() {
		return type;
	}

	public String getColumn() {
		return column;
	}

	public Serializable getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.format("%s = ?", column);
	}
	
	// TODO: figure out a way to set th eparams
}
