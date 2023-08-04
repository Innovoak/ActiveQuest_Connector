package com.innovoak.util.webhelpers.builders;

import java.beans.Expression;
import java.util.Arrays;
import java.util.Objects;

// Create a new constructable builder - builder which invokes a specialized constructor
// NOTE: Only works for public constructors
public class ConstructableBuilder<T> extends AbstractBuilder<T> {
	// Fields
	private Object[] ctorArgs;
	private Class<T> clazz;

	// Protected constructor
	protected ConstructableBuilder(Class<T> clazz) {
		ctorArgs = new Object[] {};
		this.clazz = clazz;
	}

	// Set ctor args
	public ConstructableBuilder<T> setCtorArgs(Object... args) {
		ctorArgs = args;

		return this;
	}

	// New instance
	@SuppressWarnings("unchecked")
	@Override
	protected T newInstance() throws Exception {
		Expression expression = new Expression(clazz, "new", ctorArgs);
		
		expression.execute();
		
		return (T) expression.getValue();
	}

}
