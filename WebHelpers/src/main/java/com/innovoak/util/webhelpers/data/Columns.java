package com.innovoak.util.webhelpers.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

// Factory for getting columns
public final class Columns implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final Columns ALL = new Columns(new String[] { "*" });

	// Columns
	private String[] columns;

	//
	public Columns() {
	}

	// Columns
	private Columns(String[] columns) {
		this.columns = columns;
	}

	// Static initializer
	public static Columns of(String... columns) {
		return new Columns(columns);
	}

	//
	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	// Utility methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(columns);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Columns other = (Columns) obj;
		return Arrays.equals(columns, other.columns);
	}

	// Turn to string
	@Override
	public String toString() {
		return Arrays.stream(columns).collect(Collectors.joining(","));
	}

}
