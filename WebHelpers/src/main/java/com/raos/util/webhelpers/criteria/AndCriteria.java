package com.raos.util.webhelpers.criteria;

// Represents AND notation on criterias
class AndCriteria implements Criteria {
	private static final long serialVersionUID = 1L;

	// Criterias
	private final Criteria first;
	private final Criteria second;

	// Ands the criterias
	public AndCriteria(Criteria first, Criteria second) {
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
		return String.format("(%s AND %s)", first, second);
	}
}
