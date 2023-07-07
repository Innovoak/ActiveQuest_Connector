package com.innovoak.util.webhelpers.builders;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

// Builder for any object
public abstract class AbstractBuilder<T> implements Builder<T> {
	// Properties
	protected Map<String, Object> properties;

	// Protected Constructor
	protected AbstractBuilder() {
		properties = new HashMap<>();
	}

	// Set the property and return the current builder
	public AbstractBuilder<T> setProperty(String key, Object value) {
		properties.put(key, value);

		return this;
	}

	// Copy from another builder
	public AbstractBuilder<T> copy(AbstractBuilder<T> other) {
		properties.putAll(other.properties);

		return this;
	}

	// Get a new instance of the buildable object
	protected abstract T newInstance();

	// Create a new instance
	@Override
	public T build() {
		// Get a new instance
		T value = newInstance();

		// Add its properties
		for (String fieldName : properties.keySet()) {
			// Set the value of the object
			try {
				// Invoke setter
				new PropertyDescriptor(fieldName, value.getClass()).getWriteMethod().invoke(value,
						properties.get(value));
			} catch (Exception e) {

				// If there is no getter, resort to changing the field directly
				try {
					Field field = value.getClass().getDeclaredField(fieldName);
					field.setAccessible(true);
					field.set(value, properties.get(fieldName));
				} catch (Exception e1) {
					// throw exception
					throw new RuntimeException(String.format("Unable to access property: %s", fieldName));
				}

			}
		}

		// return the value
		return value;
	}

}
