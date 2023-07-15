package com.innovoak.util.webhelpers.data.query;

import java.util.List;
import java.util.Map;

import com.innovoak.util.webhelpers.builders.ConstructableBuilder;
import com.innovoak.util.webhelpers.criteria.PredicateCriteria;
import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.data.Columns;
import com.innovoak.util.webhelpers.data.Query;

// Used to build queries
public abstract class QueryBuilder<T extends Query> extends ConstructableBuilder<T> {
	private Object[] ctorArgs;

	// Constructor
	private QueryBuilder(Class<T> clazz, int args) {
		super(clazz);
		ctorArgs = new Object[args];
	}

	// New instance
	@Override
	protected T newInstance() {
		super.setCtorArgs(ctorArgs);
		return super.newInstance();
	}

	// Internal use only
	void setCtorArg(int index, Object value) {
		ctorArgs[index] = value;
	}

	// Select query
	public static class SelectQueryBuilder extends QueryBuilder<SelectQuery> {
		private SelectQueryBuilder() {
			super(SelectQuery.class, 4);
		}

		public SelectQueryBuilder setDistinct(boolean distinct) {
			setCtorArg(0, distinct);
			return this;
		}

		public SelectQueryBuilder setColumns(Columns columns) {
			setCtorArg(1, columns);
			return this;
		}

		public SelectQueryBuilder setTableName(String tableName) {
			setCtorArg(2, tableName);
			return this;
		}

		public SelectQueryBuilder setCriteria(SelectCriteria criteria) {
			setCtorArg(3, criteria);
			return this;
		}

	}

	// Insert query
	public static class InsertQueryBuilder extends QueryBuilder<InsertQuery> {

		private InsertQueryBuilder() {
			super(InsertQuery.class, 2);
		}

		public InsertQueryBuilder setTableName(String tableName) {
			setCtorArg(0, tableName);
			return this;
		}

		public InsertQueryBuilder setValuesMap(List<Map<String, Object>> valuesMap) {
			setCtorArg(1, valuesMap);
			return this;
		}

	}

	// delete query
	public static class DeleteQueryBuilder extends QueryBuilder<DeleteQuery> {

		private DeleteQueryBuilder() {
			super(DeleteQuery.class, 2);
		}

		public DeleteQueryBuilder setTableName(String tableName) {
			setCtorArg(0, tableName);
			return this;
		}

		public DeleteQueryBuilder setCriteria(PredicateCriteria criteria) {
			setCtorArg(1, criteria);
			return this;
		}

	}

	// update query
	public static class UpdateQueryBuilder extends QueryBuilder<UpdateQuery> {

		private UpdateQueryBuilder() {
			super(UpdateQuery.class, 2);
		}

		public UpdateQueryBuilder setTableName(String tableName) {
			setCtorArg(0, tableName);
			return this;
		}

		public UpdateQueryBuilder setValues(Map<String, Object> values) {
			setCtorArg(1, values);
			return this;
		}

		public UpdateQueryBuilder setCriteria(PredicateCriteria criteria) {
			setCtorArg(2, criteria);
			return this;
		}

	}

	// Static ways to get builder/queries
	public static SelectQueryBuilder createSelectBuilder() {
		return new SelectQueryBuilder();
	}

	public static InsertQueryBuilder createInsertBuilder() {
		return new InsertQueryBuilder();
	}

	public static UpdateQueryBuilder createUpdateBuilder() {
		return new UpdateQueryBuilder();
	}

	public static DeleteQueryBuilder createDeleteBuilder() {
		return new DeleteQueryBuilder();
	}

	// Get the values from sql (raw query)
	public static Query fromSQL(String sql) {
		if (sql.startsWith("SELECT"))
			return new SelectQuery(sql);
		else if (sql.startsWith("INSERT"))
			return new InsertQuery(sql);
		else if (sql.startsWith("UPDATE"))
			return new UpdateQuery(sql);
		else if (sql.startsWith("DELETE"))
			return new DeleteQuery(sql);
		else
			throw new IllegalArgumentException("Not a valid SQL DQL or DML statement");
	}
}
