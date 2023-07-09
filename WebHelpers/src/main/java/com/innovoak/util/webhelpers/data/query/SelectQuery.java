package com.innovoak.util.webhelpers.data.query;

import java.util.Iterator;
import java.util.Map;

import com.innovoak.util.webhelpers.data.DatabaseSession;

// select statements - SELECT (DISTINCT) <columns> FROM <table> (WHERE <conditions>) (LIMIT <number>) (ORDER BY <columns (ASC | DESC)>)
public class SelectQuery implements Query<Map<String, Object>> {

	@Override
	public Iterator<Map<String, Object>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(DatabaseSession session) {
		// TODO Auto-generated method stub
		
	}

}
