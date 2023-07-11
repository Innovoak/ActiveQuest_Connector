package com.innovoak.util.webhelpers.criteria.predicate.comparing;

import java.util.Arrays;
import java.util.List;

public class GreaterThanOrEqualToCriteria implements ComparisonOperator {

	private static final long serialVersionUID = 1L;
	//Column
	private String column; 
	//Value
	private Object value; 
	
	//Constructor 
	public GreaterThanOrEqualToCriteria(String column, Object value) {
		super();
		this.column = column;
		this.value = value;
	}
	
	// No-argument constructor (For serializable fields)
	public GreaterThanOrEqualToCriteria() {

	}
	
	//To String 
	@Override
	public String toString() {
		return String.format("%s >= ?", column); 
	}

	//Parameters 
	@Override
	public List<Object> getParameters() {
		return Arrays.asList(value);
	}



}
