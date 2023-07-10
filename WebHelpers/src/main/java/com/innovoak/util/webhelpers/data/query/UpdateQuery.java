package com.innovoak.util.webhelpers.data.query;

import java.sql.Connection;
import java.util.Iterator;

import com.innovoak.util.webhelpers.data.Query;

// update statements - DELETE FROM <table> (WHERE <conditions>)
public class UpdateQuery implements Query<String> {

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(Connection session) {
		// TODO Auto-generated method stub
		
	}

}
