package com.innovoak.util.webhelpers.criteria;

import java.util.function.Predicate;

// Represents the super class of all logical operators and all Comparison operators
public interface PredicateCriteria extends Criteria {

	Predicate<Object> toPredicate();
}
