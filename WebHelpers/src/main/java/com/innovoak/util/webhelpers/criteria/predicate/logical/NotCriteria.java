package com.innovoak.util.webhelpers.criteria.predicate.logical;

import java.util.Arrays;
import java.util.List;

import com.innovoak.util.webhelpers.criteria.Criteria;

//Represents NOT notation on criterias
public class NotCriteria implements LogicalOperator {
	private static final long serialVersionUID = 1L;

	// Criterias
	private final Criteria criteria;

	// Ands the criterias
	public NotCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	// getters
	public Criteria getCriteria() {
		return criteria;
	}

	@Override
	public String toString() {
		return String.format("NOT (%s)", criteria);
	}

	@Override
	public List<Criteria> getNodeCriteria() {
		return Arrays.asList(criteria);
	}
}
