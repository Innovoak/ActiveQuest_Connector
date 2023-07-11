package com.innovoak.util.webhelpers.criteria;

import java.io.Serializable;

// represents a criteria
// TODO: Build the rest of the API
// XXX: ERROR: TODO: WARNING: BIG ERRRORRR: WE NEED TO ENSURE THAT ALL FIELDS IN THE CRITERIA API ARE VISIBLE THROUGH GETTERS AND SETTERS (ESSENTIALLY CREATEING THEM AS MODELS) AND MAKING SURE THAT THEY ALL HAVE NO ARG CONSTRUCTORS DUE TO SERIALIZABILITY RESONS
public interface Criteria extends Serializable {

	// None criteria
	public static enum NoneHolder implements Criteria {
		NONE
	}

	public String toString();
}
