package com.innovoak.util.webhelpers.criteria.predicate.logical;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.criteria.PredicateCriteria;

// Represents OR notation on criterias
public class OrCriteria implements LogicalOperator {
	private static final long serialVersionUID = 1L;

	// Criterias
	private PredicateCriteria first;
	private PredicateCriteria second;

	public OrCriteria() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Ands the criterias
	public OrCriteria(PredicateCriteria first, PredicateCriteria second) {
		this.first = first;
		this.second = second;
	}

	// getters
	public PredicateCriteria getFirst() {
		return first;
	}

	public PredicateCriteria getSecond() {
		return second;
	}

	public void setFirst(PredicateCriteria first) {
		this.first = first;
	}

	public void setSecond(PredicateCriteria second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return String.format("(%s OR %s)", first, second);
	}

	@Override
	public List<Criteria> getNodeCriteria() {
		return Arrays.asList(first, second);
	}

	@Override
	public Predicate<Object> toPredicate() {
		return first.toPredicate().or(second.toPredicate());
	}
}
