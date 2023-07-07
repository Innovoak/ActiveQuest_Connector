package com.innovoak.util.webhelpers.builders;

// Regular builder
public class SimpleBuilder<T> extends AbstractBuilder<T> {
	// Class
	private Class<T> clazz;

	// Constructor
	public SimpleBuilder(Class<T> clazz) {
		this.clazz = clazz;
	}

	// New instance
	@Override
	protected T newInstance() {
		try {
			return (T) clazz.getConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Unable to access %s's default no-arg constructor", clazz.getSimpleName()));
		}
	}

}
