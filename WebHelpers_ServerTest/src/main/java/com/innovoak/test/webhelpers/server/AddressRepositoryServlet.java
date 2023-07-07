package com.innovoak.test.webhelpers.server;

import com.innovoak.test.webhelpers.client.model.Address;
import com.innovoak.util.webhelpers.server.DatabaseRepositoryServlet;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class AddressRepositoryServlet
 */
@WebServlet("/address")
public class AddressRepositoryServlet extends DatabaseRepositoryServlet<Address> {
	private static final long serialVersionUID = 1L;

	public AddressRepositoryServlet() {
		super(Address.class);
	}

}
