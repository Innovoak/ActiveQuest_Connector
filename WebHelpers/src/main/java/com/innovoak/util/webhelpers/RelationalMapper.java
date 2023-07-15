package com.innovoak.util.webhelpers;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;

import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.criteria.predicate.comparing.EqualsCriteria;
import com.innovoak.util.webhelpers.data.Model;
import com.innovoak.util.webhelpers.data.annotations.Column;

public final class RelationalMapper {

	private RelationalMapper() {
	}

	// Gets 1 to 1 provided by Supplier
	public static <T extends Model, V extends Model> V get___ToOne(Repository<V> repo,
			Supplier<Serializable> idSupplier) throws Exception {
		return repo.get(idSupplier.get());
	}

	// Gets 1 to 1 provided by Object and property name
	public static <T extends Model, V extends Model> V get___ToOne(Repository<V> repo, T value, String propertyName)
			throws Exception {
		// Get the descriptor and read methods
		PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, value.getClass());
		Method method = descriptor.getReadMethod();

		return repo.get((Serializable) method.invoke(value));
	}

	// Gets 1 to 1 provided by Object and property name
	public static <T extends Model, V extends Model> List<V> get___ToMany(Repository<V> repo, Class<V> clazz, T value,
			String propertyName) throws Exception {

		// Get the property descriptor of the name
		PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, clazz);
		Method method = descriptor.getReadMethod();

		// Get all by the select criteria
		return repo.getAllBy(new SelectCriteria(new EqualsCriteria(
				// Get the ID mapping
				// If there is an annotation for it
				// Get it through annotation
				// Else get it through name
				method.isAnnotationPresent(Column.class) ? method.getAnnotation(Column.class).columnName()
						: descriptor.getName(),
				value.getId())));
	}
}
