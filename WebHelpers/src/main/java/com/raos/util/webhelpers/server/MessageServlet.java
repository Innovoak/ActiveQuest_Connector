package com.raos.util.webhelpers.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raos.util.webhelpers.Message;
import com.raos.util.webhelpers.Message.MessageBuilder;

public abstract class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Error templates
	public static final MessageBuilder ERROR_BUILDER_TEMPLATE;
	
	static {
		// Error messages
		ERROR_BUILDER_TEMPLATE = MessageBuilder.create().setAction("ERROR").setName("ERROR")
				.setDescription("Invalid Message Received");
	}

	// Do post
	@Override
	public final void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// Get the message and log
			Message message = ping(req);
			log("Message Received: " + message);

			// Process the message
			message = process(message);

			// Send message and log
			pong(resp, message);
			log("Message Sent: " + message);

		} catch (Exception e) {
			try {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				
				pong(resp, MessageBuilder.fromTemplate(ERROR_BUILDER_TEMPLATE).setContent(e.getMessage()).build());
			} catch (IOException ioe) {
			}
		}

	}

	// Get ping
	private Message ping(HttpServletRequest req) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(req.getInputStream())) {
			return (Message) in.readObject();
		}
	}

	// Send pong
	private void pong(HttpServletResponse resp, Message message) throws IOException {
		// Set request as bad request
		if (message.isError())
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		try (ObjectOutputStream out = new ObjectOutputStream(resp.getOutputStream())) {
			out.writeObject(message);
		}
	}

	// Process the message
	public abstract Message process(Message message);
}
