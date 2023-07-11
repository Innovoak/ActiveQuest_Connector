package com.innovoak.util.webhelpers.criteria.predicate.logical;

import java.util.Arrays;
import java.util.List;

import com.innovoak.util.webhelpers.criteria.Criteria;

// Represents OR notation on criterias
public class OrCriteria implements LogicalOperator {
	private static final long serialVersionUID = 1L;

	// Criterias
	private final Criteria first;
	private final Criteria second;

	// Ands the criterias
	public OrCriteria(Criteria first, Criteria second) {
		this.first = first;
		this.second = second;
	}

	// getters
	public Criteria getFirst() {
		return first;
	}

	public Criteria getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return String.format("(%s OR %s)", first, second);
	}

	@Override
	public List<Criteria> getNodeCriteria() {
		return Arrays.asList(first, second);
	}
}
