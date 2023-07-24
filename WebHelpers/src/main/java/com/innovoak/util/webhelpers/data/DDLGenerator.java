package com.innovoak.util.webhelpers.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.innovoak.util.webhelpers.ClasspathUtils;
import com.innovoak.util.webhelpers.data.annotations.Column;
import com.innovoak.util.webhelpers.data.annotations.Table;

public class DDLGenerator {
	private String url;
	private String password;
	private String username;

	public DDLGenerator(String url, String password, String username) {
		super();
		this.url = url;
		this.password = password;
		this.username = username;
	}

	public void generate() throws Exception {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {

			// Get the list of classes
			Set<Class<? extends Model>> modelClasses = ClasspathUtils.findAllConcreteInstances(Model.class);

			Statement statement = connection.createStatement();

			// For each class
			for (Class<? extends Model> clazz : modelClasses) {

				Table table = clazz.getAnnotation(Table.class);

				StringBuilder sql = new StringBuilder("CREATE TABLE ").append(table.name()).append(" (");

				// Create the ddl from the classes
				List<Column> fieldsMap = DatabaseRepository.classToTypesList(clazz);

				for (Column column : fieldsMap) {

					sql.append(column.columnName()).append(" ");
					sql.append(column.type().name());

					if (column.type() == JDBCType.VARCHAR)
						sql.append("(255)");

					if (column.primaryKey())
						sql.append(" PRIMARY KEY");
					else if (column.unique())
						sql.append(" UNIQUE");

					if (!column.nullable())
						sql.append(" NOT NULL");
					sql.append(", ");
				}

				sql.setLength(sql.length() - 2);
				sql.append(")");

				System.out.println(sql);

				statement.addBatch(sql.toString());
			}

			statement.executeBatch();
		}
	}

}
