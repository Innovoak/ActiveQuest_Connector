package com.innovoak.util.webhelpers.data;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.innovoak.util.webhelpers.Repository;
import com.innovoak.util.webhelpers.criteria.PredicateCriteria;
import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.data.annotations.Column;
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
				.setCriteria(criteria).build();
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
				return DatabaseRepository.objectToMap(t);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toList()));
	}

	@Override
	public void updateAllBy(T object, PredicateCriteria criteria) throws Exception {
		checkClosed();

		// Create the query
		QueryBuilder.createUpdateBuilder().setTableName(getTableName()).setCriteria(criteria)
				.setValues(objectToMap(object)).build().execute(session);

	}

	// Get the connection from the session
	protected final Connection getConnection() throws Exception {
		return session.getConnection();
	}

	// Gets object from map
	private static Map<String, Object> objectToMap(Object object) throws Exception {
		// Create a map
		Map<String, Object> values = new HashMap<>();

		// Get the bean info and iterate over properties
		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
		for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
			// Skip id
			if (descriptor.getName().equals("id"))
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

	// Gets object data from map
	private static <T> T objectFromMap(T value, Map<String, Object> result) throws Exception {
		// Get the bean info
		BeanInfo info = Introspector.getBeanInfo(value.getClass());

		// Iterate over the properties
		for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {
			// Skip id
			if (descriptor.getName().equals("id"))
				continue;

			// Skip if its transient
			if (descriptor.getValue("transient") == Boolean.TRUE)
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
	protected abstract String getTableName();

	// Create a new instance of the object
	protected abstract T newInstance() throws Exception;
}
