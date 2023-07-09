package com.innovoak.util.webhelpers.data.query;

import java.util.Iterator;

import com.innovoak.util.webhelpers.data.DatabaseSession;

// delete statements - UPDATE <table> SET <column = value> (WHERE <condition>)
public class DeleteQuery implements Query {

	@Override
	public Iterator<Object[]> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(DatabaseSession session) {
	}

}
