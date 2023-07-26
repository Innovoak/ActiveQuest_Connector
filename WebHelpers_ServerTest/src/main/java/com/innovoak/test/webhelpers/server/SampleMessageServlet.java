package com.innovoak.test.webhelpers.server;

import com.innovoak.util.webhelpers.Message;
import com.innovoak.util.webhelpers.Message.MessageBuilder;
import com.innovoak.util.webhelpers.server.MessageServlet;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class SampleMessageServlet
 */
@WebServlet("/test")
public class SampleMessageServlet extends MessageServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SampleMessageServlet() {
	}

	@Override
	public Message process(Message message) {
		System.out.println("Received Message: " + message);

		message = MessageBuilder.create().setName("SUCCESS!").setAction("SUCCESS!").setDescription("Description")
				.setContent("Pong!").build();

		System.out.println("Sending message: " + message);

		return message;
	}

}
