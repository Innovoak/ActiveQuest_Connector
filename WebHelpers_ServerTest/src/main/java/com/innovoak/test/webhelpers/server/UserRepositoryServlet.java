package com.innovoak.test.webhelpers.server;

import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.util.webhelpers.server.DatabaseRepositoryServlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class UserRepositoryServlet
 */
@WebServlet("/user")
public class UserRepositoryServlet extends DatabaseRepositoryServlet<User> {
	private static final long serialVersionUID = 1L;

	// Keep the users class here
	public UserRepositoryServlet() {
		super(User.class);
	}

}
