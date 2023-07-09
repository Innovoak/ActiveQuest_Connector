package com.innovoak.util.webhelpers.data.query;

import java.sql.Connection;
import java.util.Iterator;
import java.util.Map;

import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.data.Columns;
import com.innovoak.util.webhelpers.data.DatabaseSession;
import com.innovoak.util.webhelpers.data.Query;

// select statements - SELECT (DISTINCT) <columns> FROM <table> (WHERE <conditions>) (LIMIT <number>) (ORDER BY <columns (ASC | DESC)>)
public final class SelectQuery implements Query<Map<String, Object>> {
	
	private String sql;
	
	protected SelectQuery(String sql) {
		this.sql = sql;
	}
	
	public SelectQuery(boolean distinct, Columns columns, String tableName, Criteria criteria) {
		
	}
	

	@Override
	public Iterator<Map<String, Object>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(Connection session) {
		
	}

}
