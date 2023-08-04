package com.innovoak.util.webhelpers.criteria.predicate.logical;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.criteria.PredicateCriteria;

//Represents NOT notation on criterias
public class NotCriteria implements LogicalOperator {
	private static final long serialVersionUID = 1L;

	// Criterias
	private PredicateCriteria criteria;

	// Ands the criterias
	public NotCriteria(PredicateCriteria criteria) {
		this.criteria = criteria;
	}

	public NotCriteria() {
		super();
		// TODO Auto-generated constructor stub
	}

	// getters
	public PredicateCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(PredicateCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public String toString() {
		return String.format("NOT (%s)", criteria);
	}

	@Override
	public List<Criteria> getNodeCriteria() {
		return Arrays.asList(criteria);
	}

	@Override
	public Predicate<Object> toPredicate() {
		return criteria.toPredicate().negate();
	}
}
