package com.innovoak.test.webhelpers.server;

import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.server.RepositoryServlet;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class UserRepositoryServlet
 */
@WebServlet("/user")
public class UserRepositoryServlet extends RepositoryServlet<User> {
	private static final long serialVersionUID = 1L;
	private static final List<User> USERS = new CopyOnWriteArrayList<>();

	@Override
	public List<User> getAllBy(Criteria criteria) throws Exception {
		return Collections.unmodifiableList(USERS);
	}

	@Override
	public void deleteAllBy(Criteria criteria) throws Exception {
		
	}

	@Override
	public void insert(User object) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertAll(List<User> objects) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAllBy(User object, Criteria criteria) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
