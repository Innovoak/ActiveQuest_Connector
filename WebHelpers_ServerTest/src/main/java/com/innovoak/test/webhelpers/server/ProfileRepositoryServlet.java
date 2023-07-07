package com.innovoak.test.webhelpers.server;

import com.innovoak.test.webhelpers.client.model.Profile;
import com.innovoak.util.webhelpers.server.DatabaseRepositoryServlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class UserRepositoryServlet
 */
@WebServlet("/profile")
public class ProfileRepositoryServlet extends DatabaseRepositoryServlet<Profile> {
	private static final long serialVersionUID = 1L;

	// Keep the users class here
	public ProfileRepositoryServlet() {
		super(Profile.class);
	}

}
