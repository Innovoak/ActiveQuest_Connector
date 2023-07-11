package com.innovoak.util.webhelpers.criteria;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.innovoak.util.webhelpers.criteria.predicate.comparing.ComparisonOperator;

// represents a criteria
// TODO: Build the rest of the API
// XXX: ERROR: TODO: WARNING: BIG ERRRORRR: WE NEED TO ENSURE THAT ALL FIELDS IN THE CRITERIA API ARE VISIBLE THROUGH GETTERS AND SETTERS (ESSENTIALLY CREATEING THEM AS MODELS) AND MAKING SURE THAT THEY ALL HAVE NO ARG CONSTRUCTORS DUE TO SERIALIZABILITY RESONS
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
