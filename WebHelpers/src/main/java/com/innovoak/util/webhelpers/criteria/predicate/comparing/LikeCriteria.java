package com.innovoak.util.webhelpers.criteria.predicate.comparing;

import java.util.Arrays;
import java.util.List;

public class LikeCriteria implements ComparisonOperator {

	private static final long serialVersionUID = 1L;
	
	//Column
	private String column; 
	//Pattern 
	private String pattern; 
	
	//Constructor 
	public LikeCriteria(String column, String pattern) {
		super();
		this.column = column;
		this.pattern = pattern;
	}
	
	//No Argument Constructor 
	public LikeCriteria() {
		
	}

	//toString
	@Override
	public String toString() {
		return String.format("%s LIKE ?", column); 
	}

	// Parameters 
	@Override
	public List<Object> getParameters() {
		return Arrays.asList(pattern); 
	}

}
