package com.innovoak.util.webhelpers.data.query;

import java.util.Iterator;

import com.innovoak.util.webhelpers.data.DatabaseSession;

// insert statements - INSERT INTO <table> (<columns>) VALUES (<column values>)
public class InsertQuery extends Query {

	public InsertQuery(DatabaseSession session) {
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
