package com.innovoak.util.webhelpers.data;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.sql.JDBCType;
import java.util.UUID;

import com.innovoak.util.webhelpers.data.annotations.Column;

// Model class - generic models
public abstract class Model implements Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
	private String id;

	// Constructors
	public Model() {
		this(UUID.randomUUID().toString());
	}

	public Model(String id) {
		super();
		this.id = id;
	}

	// Getters and Setters
	@Column(columnName = "id", primaryKey = true, type = JDBCType.VARCHAR, unique = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// Utility methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Model other = (Model) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// Programatic toString method
	@Override
	public final String toString() {
		
		// Create a string builder
		StringBuilder builder = new StringBuilder();
		
		// Add the class name and paranthesis
		builder.append(this.getClass()).append(" [ ");
		
		// Try to read bean info and append params
		try {
			BeanInfo info = Introspector.getBeanInfo(this.getClass());
			
			for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {
				builder.append(String.format("%s=%s, ", descriptor.getName(), descriptor.getReadMethod().invoke(this)));
			}
		} catch (Exception e) {
			throw new RuntimeException("Cannot read bean info");
		}
		
		// Remove last 2 chars and append the end chars
		builder.setLength(builder.length() - 2);
		builder.append(" ]");
		
		return builder.toString();
	}

}
