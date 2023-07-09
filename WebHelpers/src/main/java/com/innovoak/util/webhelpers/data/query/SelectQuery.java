package com.innovoak.util.webhelpers.data.query;

import java.util.Iterator;

import com.innovoak.util.webhelpers.data.DatabaseSession;

// select statements - SELECT (DISTINCT) <columns> FROM <table> (WHERE <conditions>) (LIMIT <number>) (ORDER BY <columns (ASC | DESC)>)
public class SelectQuery extends Query {

	public SelectQuery(DatabaseSession session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Iterator<Object[]> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
