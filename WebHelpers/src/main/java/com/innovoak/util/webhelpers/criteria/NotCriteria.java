package com.innovoak.util.webhelpers.criteria;

//Represents NOT notation on criterias
class NotCriteria implements Criteria {
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
}
