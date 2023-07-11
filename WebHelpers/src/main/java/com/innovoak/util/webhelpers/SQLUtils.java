package com.innovoak.util.webhelpers;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// SQL and JDBC utilities
public class SQLUtils {

	public static Set<String> getColumnNames(ResultSetMetaData metaData) throws SQLException {

		// Header set
		Set<String> columnNames = new HashSet<>();

		// Add the header labels
		for (int column = 1; column <= metaData.getColumnCount(); column++) {
			columnNames.add(metaData.getColumnLabel(1));
		}

		return Collections.unmodifiableSet(columnNames);
	}

}
