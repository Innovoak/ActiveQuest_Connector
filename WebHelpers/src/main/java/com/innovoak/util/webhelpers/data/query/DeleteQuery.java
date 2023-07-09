package com.innovoak.util.webhelpers.data.query;

import java.util.Iterator;

import com.innovoak.util.webhelpers.data.DatabaseSession;

// delete statements - UPDATE <table> SET <column = value> (WHERE <condition>)
public class DeleteQuery extends Query {

	public DeleteQuery(DatabaseSession session) {
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
