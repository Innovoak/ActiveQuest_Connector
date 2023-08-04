package com.innovoak.util.webhelpers.data;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.innovoak.util.webhelpers.Repository;
import com.innovoak.util.webhelpers.criteria.PredicateCriteria;
import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.data.annotations.Column;
import com.innovoak.util.webhelpers.data.annotations.Table;
import com.innovoak.util.webhelpers.data.query.QueryBuilder;
import com.innovoak.util.webhelpers.data.query.SelectQuery;

// Will create a persisted object into database... ignores any transient values
// To access classes of other models or tables.. we must parse them too and figure out the table they come from

// mappings will occur where
// 
// Eager loading
public abstract class DatabaseRepository<T extends Model> implements Repository<T> {
	// Current session
	private DatabaseSession session;

	// Create a database repo from this constructor
	public DatabaseRepository(DatabaseSession session) {
		this.session = session;
	}

	// Check if the connection is closed
	public void checkClosed() throws Exception {
		if (session.isClosed())
			throw new IllegalAccessError("Session is already closed");
	}

	@Override
	public List<T> getAllBy(SelectCriteria criteria) throws Exception {
		checkClosed();

		// Create the query
		SelectQuery query = QueryBuilder.createSelectBuilder().setTableName(getTableName()).setColumns(Columns.ALL)
				.setTypeMap(classToMap(newInstance().getClass())).setCriteria(criteria).build();
		// Execute the query
		query.execute(session);

		// Create the values
		List<T> values = new LinkedList<>();

		// For each result.. parse
		for (Map<String, Object> result : query) {

			// Add value to list
			values.add(objectFromMap(newInstance(), result));
		}

		// Return the values
		return values;
	}

	@Override
	public void deleteAllBy(PredicateCriteria criteria) throws Exception {
		checkClosed();

		// Delete all values from the table represented by this repo with the required
		// criteria
		QueryBuilder.createDeleteBuilder().setTableName(getTableName()).setCriteria(criteria).build().execute(session);

	}

	@Override
	public void insertAll(List<T> objects) throws Exception {
		checkClosed();

		QueryBuilder.createInsertBuilder().setTableName(getTableName()).setValuesMap(objects.stream().map(t -> {
			// Get or throw
			try {
				return DatabaseRepository.objectToMap(t, true);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toList())).build().execute(session);

	}

	@Override
	public void updateAllBy(T object, PredicateCriteria criteria) throws Exception {
		checkClosed();

		// Create the query
		QueryBuilder.createUpdateBuilder().setTableName(getTableName()).setCriteria(criteria)
				.setValues(objectToMap(object, false)).build().execute(session);
	}

	// Get the connection from the session
	protected final Connection getConnection() throws Exception {
		return session.getConnection();
	}

	// Gets object from map
	private static Map<String, Object> objectToMap(Object object, boolean withID) throws Exception {
		// Create a map
		Map<String, Object> values = new HashMap<>();

		// Get the bean info and iterate over properties
		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
		for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
			// Skip id
			if (descriptor.getName().equals("id") && !withID)
				continue;

			if (descriptor.getName().equals("class"))
				continue;

			// Skip if its transient
			if (descriptor.getValue("transient") == Boolean.TRUE)
				continue;

			// Get the read and write method
			Method read = descriptor.getReadMethod();

			// Check if
			values.put(read.isAnnotationPresent(Column.class) ? read.getAnnotation(Column.class).columnName()
					: descriptor.getName(), read.invoke(object));

		}

		// Return values
		return values;

	}

	// Gets object from map
	private static Map<String, Class<?>> classToMap(Class<?> object) throws Exception {
		// Create a map
		Map<String, Class<?>> values = new HashMap<>();

		// Get the bean info and iterate over properties
		BeanInfo beanInfo = Introspector.getBeanInfo(object);
		for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {

			if (descriptor.getName().equals("class"))
				continue;
			// Skip if its transient
			if (descriptor.getValue("transient") == Boolean.TRUE)
				continue;

			// Get the read and write method
			Method read = descriptor.getReadMethod();

			// Check if
			values.put(read.isAnnotationPresent(Column.class) ? read.getAnnotation(Column.class).columnName()
					: descriptor.getName(), read.getReturnType());

		}

		// Return values
		return values;

	}

	// Gets object from map
	public static List<Column> classToTypesList(Class<?> object) throws Exception {
		// Create a map
		List<Column> values = new ArrayList<>();

		// Get the bean info and iterate over properties
		BeanInfo beanInfo = Introspector.getBeanInfo(object);
		for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {

			// Skip if its transient
			if (descriptor.getValue("transient") == Boolean.TRUE)
				continue;

			if (descriptor.getName().equals("class"))
				continue;

			// Get the read and write method
			Method read = descriptor.getReadMethod();

			// Check if
			values.add(read.isAnnotationPresent(Column.class) ? read.getAnnotation(Column.class)
					: new CustomColumn(descriptor));
		}

		// Return values
		return values;

	}

	public static PropertyDescriptor getPropertyFromColumn(Class<?> clazz, String column) {
		// Get the bean info and iterate over properties
		BeanInfo beanInfo;

		try {
			beanInfo = Introspector.getBeanInfo(clazz);
		} catch (IntrospectionException e) {
			throw new RuntimeException("This is not working", e);
		}

		for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {

			// Skip if its transient
			if (descriptor.getValue("transient") == Boolean.TRUE)
				continue;

			if (descriptor.getName().equals("class"))
				continue;

			// Get the read and write method
			Method read = descriptor.getReadMethod();

			// Check if
			if (read.isAnnotationPresent(Column.class) && read.getAnnotation(Column.class).columnName().equals(column))
				return descriptor;
			else if (!read.isAnnotationPresent(Column.class) && descriptor.getName().equals(column))
				return descriptor;
		}

		return null;
	}

	private static class CustomColumn implements Column {
		private PropertyDescriptor descriptor;

		public CustomColumn(PropertyDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		@Override
		public Class<? extends Annotation> annotationType() {
			return Column.class;
		}

		@Override
		public boolean unique() {
			return false;
		}

		@Override
		public JDBCType type() {
			Class<?> readType = descriptor.getReadMethod().getReturnType();

			if (readType == Boolean.class || readType == boolean.class)
				return JDBCType.BOOLEAN;
			else if (readType == Integer.class || readType == int.class)
				return JDBCType.INTEGER;
			else if (readType == Double.class || readType == double.class)
				return JDBCType.DOUBLE;
			else if (readType == Float.class || readType == float.class)
				return JDBCType.FLOAT;
			else if (readType == String.class || readType == char[].class)
				return JDBCType.VARCHAR;

			return JDBCType.OTHER;
		}

		@Override
		public boolean primaryKey() {
			return false;
		}

		@Override
		public String columnName() {
			return descriptor.getName();
		}

		@Override
		public boolean nullable() {
			return true;
		}
	}

	// Gets object data from map
	private static <T> T objectFromMap(T value, Map<String, Object> result) throws Exception {
		// Get the bean info
		BeanInfo info = Introspector.getBeanInfo(value.getClass());

		// Iterate over the properties
		for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {

			// Skip if its transient
			if (descriptor.getValue("transient") == Boolean.TRUE)
				continue;

			if (descriptor.getName().equals("class"))
				continue;

			// Get the write method
			Method read = descriptor.getReadMethod();
			Method write = descriptor.getWriteMethod();

			// Set the values in the write method
			write.invoke(value, result.get(
					// If the column annotation is present, get name from annotation, otherwise, get
					// from field name
					read.isAnnotationPresent(Column.class) ? read.getAnnotation(Column.class).columnName()
							: descriptor.getName()));
		}

		return value;
	}

	// Table specific data
	@SuppressWarnings("unchecked")
	protected String getTableName() {

		Class<T> clazz = null;
		try {
			clazz = (Class<T>) newInstance().getClass();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clazz.getAnnotation(Table.class).name();

	}

	// Create a new instance of the object
	protected abstract T newInstance() throws Exception;
}
