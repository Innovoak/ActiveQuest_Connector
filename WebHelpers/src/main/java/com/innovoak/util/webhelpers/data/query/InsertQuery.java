package com.innovoak.util.webhelpers.data.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.innovoak.util.webhelpers.data.Query;

// insert statements - INSERT INTO <table> (<columns>) VALUES (<column values>)
public class InsertQuery implements Query, Iterable<Integer> {
	private static final long serialVersionUID = 1L;

	// Set the sql, values
	private String sql;
	private List<List<Object>> values;
	private transient int[] result;

	// No arg constructor - for serialization
	public InsertQuery() {
	}

	// Arg constructor
	public InsertQuery(String tableName, List<Map<String, Object>> valuesMap) {
		// Create the columns
		List<String> columns = new LinkedList<>();

		// Turn to string
		String columnsString = valuesMap.get(0).keySet().stream().map(e -> {
			// Add to columns
			columns.add(e);
			return e;
		}).collect(Collectors.joining(","));

		// Turn to string
		String questionString = valuesMap.get(0).keySet().stream().map(e -> "?").collect(Collectors.joining());

		// Create the values
		values = new LinkedList<>();

		// Go through all the rows
		for (Map<String, Object> itemsMap : valuesMap) {
			// Create row of values
			List<Object> row = new ArrayList<>();

			// Get the map and turn to value and add
			columns.stream().map(itemsMap::get).forEach(row::add);

			// add row
			values.add(row);
		}

		// Turn to sql
		sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (").append(columnsString)
				.append(") VALUES (").append(questionString).append(")").toString();
	}

	// In the case we are using an array
	public InsertQuery(String tableName, Map<String, Object>[] values) {
		this(tableName, Arrays.asList(values));
	}

	// Execute the statement
	@Override
	public void execute(Connection connection) throws SQLException {

		// Create a prepared statement
		PreparedStatement statement = connection.prepareStatement(sql);

		// Go through the rows
		for (List<Object> row : values) {

			// Set objects
			for (int i = 1; i <= row.size(); i++) {
				statement.setObject(i, row.get(i));
			}

			// Add batch
			statement.addBatch();
		}

		// Execute the batch
		result = statement.executeBatch();

		// close the statement
		statement.close();
	}

	// Returns result of execution
	@Override
	public Iterator<Integer> iterator() {
		// make sure that this has executed
		if (result == null)
			throw new IllegalStateException("Query has not been executed yet");

		// Return result iterator
		return IntStream.of(result).iterator();
	}

}
