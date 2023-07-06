package com.raos.util.webhelpers;

// For any invalid messages
public class InvalidMessageException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidMessageException(String string) {
		super(string);
	}

}
