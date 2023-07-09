package com.innovoak.util.webhelpers.criteria;

import java.util.List;

// Acts as a leaf for the criterias
// https://www.w3schools.com/sql/sql_operators.asp
// All subclasses should be comparison operators and Logical operators from the above link
public interface LeafCriteria extends Criteria{

	// Gets parameters
	List<Object> getParameters();

}
