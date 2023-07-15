package com.innovoak.util.webhelpers.criteria.predicate.comparing;

import java.util.Arrays;
import java.util.List;

public class NotLikeCriteria implements ComparisonOperator {

	private static final long serialVersionUID = 1L;
	
	//column
	private String column; 
	//pattern 
	private String pattern; 
	
	//Constructor
	public NotLikeCriteria(String column, String pattern) {
		super();
		this.column = column;
		this.pattern = pattern;
	}

	//No Argument Constructor 
	public NotLikeCriteria() {
		
	}
	
	//To String 
	@Override
	public String toString() {
		return String.format("%s NOT LIKE ?", column); 
	}

	//parameters 
	@Override
	public List<Object> getParameters() {
		return Arrays.asList(pattern); 
	}

	
}
