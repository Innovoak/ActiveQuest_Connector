package com.innovoak.util.webhelpers.criteria;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import com.innovoak.util.webhelpers.criteria.predicate.comparing.ComparisonOperator;
import com.innovoak.util.webhelpers.data.DatabaseRepository;

// represents a criteria
public interface Criteria extends Serializable {

	// None criteria
	public static enum NoneHolder implements ComparisonOperator {
		NONE;

		@Override
		public List<Object> getParameters() {
			return Collections.emptyList();
		}

		@Override
		public Predicate<Object> toPredicate() {
			return e -> true;
		}
	}

	public String toString();
	
	public static Object getFromColumn(String column, Object e) {
		PropertyDescriptor descriptor = DatabaseRepository.getPropertyFromColumn(e.getClass(), column);

		try {
			return descriptor.getReadMethod().invoke(e);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}
}
