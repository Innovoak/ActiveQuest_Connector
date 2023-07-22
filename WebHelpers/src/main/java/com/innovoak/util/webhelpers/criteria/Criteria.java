package com.innovoak.util.webhelpers.criteria;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.innovoak.util.webhelpers.criteria.predicate.comparing.ComparisonOperator;

// represents a criteria
public interface Criteria extends Serializable {

	// None criteria
	public static enum NoneHolder implements ComparisonOperator {
		NONE;

		@Override
		public List<Object> getParameters() {
			return Collections.emptyList();
		}
	}

	public String toString();
}
