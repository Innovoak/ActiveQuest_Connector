package com.innovoak.util.webhelpers.criteria.predicate.comparing;

import java.util.Arrays;
import java.util.List;

public class NotEqualCriteria implements ComparisonOperator {

	private static final long serialVersionUID = 1L;
	//Column
	private String column; 
	//Value 
	private Object value;  
	
	//Constructor 
	public NotEqualCriteria(String column, Object value) {
		super();
		this.column = column;
		this.value = value;
	}
	
	// No-argument constructor (For serializable fields)
	public NotEqualCriteria() {
			
	}
		

	//To String 
	@Override
	public String toString() {
		return String.format("%s <> ?", column);
	}

	@Override
	public List<Object> getParameters() {
		return Arrays.asList(value); 
	}


}
