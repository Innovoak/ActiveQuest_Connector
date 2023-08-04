package com.innovoak.util.webhelpers.criteria.predicate.comparing;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.data.DatabaseRepository;

public class BetweenCriteria implements ComparisonOperator {
	private static final long serialVersionUID = 1L;

	private String column;
	private Object lowerBound;
	private Object upperBound;

	public BetweenCriteria() {
	}

	public BetweenCriteria(String column, Object lowerBound, Object upperBound) {
		this.column = column;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Object getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(Object lowerBound) {
		this.lowerBound = lowerBound;
	}

	public Object getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(Object upperBound) {
		this.upperBound = upperBound;
	}

	@Override
	public String toString() {
		return String.format("%s BETWEEN ? AND ?", column);
	}

	@Override
	public List<Object> getParameters() {
		return Arrays.asList(lowerBound, upperBound);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Predicate<Object> toPredicate() {
		return e -> {
			e = Criteria.getFromColumn(column, e);

			return ((Comparable<Object>) e).compareTo(lowerBound) > 0
					&& ((Comparable<Object>) e).compareTo(upperBound) < 0;
		};
	}
}