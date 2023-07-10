package com.innovoak.util.webhelpers.criteria;

import java.io.Serializable;

// represents a criteria
// TODO: Build the rest of the API
public interface Criteria extends Serializable {

	// None criteria
	public static enum NoneHolder implements Criteria {
		NONE
	}

	public String toString();
}
