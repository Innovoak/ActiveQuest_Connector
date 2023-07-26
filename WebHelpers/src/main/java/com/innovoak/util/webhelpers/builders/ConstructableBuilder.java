package com.innovoak.util.webhelpers.builders;

import java.beans.Expression;
import java.util.Arrays;

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
		System.out.println(Arrays.toString(ctorArgs));

		Expression expression = new Expression(clazz, "newInstance", ctorArgs);
		
		expression.execute();
		
		System.out.println(expression.getValue());

		return (T) expression.getValue();
	}

}
