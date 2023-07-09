package com.innovoak.util.webhelpers.data.query;

import java.util.Iterator;

import com.innovoak.util.webhelpers.data.DatabaseSession;

// select statements - SELECT (DISTINCT) <columns> FROM <table> (WHERE <conditions>) (LIMIT <number>) (ORDER BY <columns (ASC | DESC)>)
public class SelectQuery implements Query {

	@Override
	public Iterator<Object[]> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(DatabaseSession session) {
		// TODO Auto-generated method stub
		
	}

}
