package com.raos.util.webhelpers.server;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.raos.util.webhelpers.InvalidMessageException;
import com.raos.util.webhelpers.Message;
import com.raos.util.webhelpers.Repository;
import com.raos.util.webhelpers.Message.MessageBuilder;
import com.raos.util.webhelpers.criteria.Criteria;

// A servlet which acts as a REST repository for ONLY JAVA CLIENTS to access
// Acts as abstract class to access data from server
public abstract class RepositoryServlet<T extends Serializable> extends MessageServlet implements Repository<T> {
	private static final long serialVersionUID = 1L;

	// Templates
	// Create templates
	public static final MessageBuilder CREATE_SINGLE_BUILDER_TEMPLATE;
	public static final MessageBuilder CREATE_MULTI_BUILDER_TEMPLATE;
	// Insert templates
	public static final MessageBuilder READ_BUILDER_TEMPLATE;
	// Destroy templates
	public static final MessageBuilder DESTROY_BUILDER_TEMPLATE;
	// Update templates
	public static final MessageBuilder UPDATE_BUILDER_TEMPLATE;

	// Create the template builders
	static {
		// Creational messages
		CREATE_SINGLE_BUILDER_TEMPLATE = MessageBuilder.create().setAction("CREATE").setName("SINGLE")
				.setDescription("Client sends a model to the servlet to create. EXPECT a Model");
		CREATE_MULTI_BUILDER_TEMPLATE = MessageBuilder.create().setAction("CREATE").setName("MULTI")
				.setDescription("Client sends a collection of models to the servlet to create. EXPECT a Collection");
		// Reading messages
		READ_BUILDER_TEMPLATE = MessageBuilder.create().setAction("READ").setName("READ")
				.setDescription("Client sends criteria via message content to read. Expect a Criteria");
		// Destructional messages
		DESTROY_BUILDER_TEMPLATE = MessageBuilder.create().setAction("DESTROY").setName("DESTROY")
				.setDescription("Client sends criteria via message content to delete. Expect a Criteria");
		// Updating messages
		UPDATE_BUILDER_TEMPLATE = MessageBuilder.create().setAction("UPDATE").setName("UPDATE").setDescription(
				"Client sends both a model object and criteria for records to update with this model, EXPECTS a Map<Class, Serializable> with its contents being one criteria and the other a model");
	}

	// Processes the message
	@SuppressWarnings("unchecked")
	@Override
	public Message process(Message message) {
		// Declare the response
		Message response;

		// Get the action from the message
		try {
			if (CREATE_SINGLE_BUILDER_TEMPLATE.matches(message)) {

				// Get the message
				T value = (T) message.getContent();

				// Insert it
				insert(value);

				// Send Success Message
				response = new Message("SUCCESS", "SUCCESS", "Operation Completed Successfully",
						"Model created successfully");

			} else if (CREATE_MULTI_BUILDER_TEMPLATE.matches(message)) {

				// Get the message
				List<T> value = (List<T>) message.getContent();

				// Insert it
				insertAll(value);

				// Send Success Message
				response = new Message("SUCCESS", "SUCCESS", "Operation Completed Successfully",
						"Models created successfully");

			} else if (READ_BUILDER_TEMPLATE.matches(message)) {

				// Get the message
				Criteria value = (Criteria) message.getContent();

				// Get it
				List<T> content = getAllBy(value);

				// Send Success Message
				response = new Message("SUCCESS", "SUCCESS", "Operation Completed Successfully", content);

			} else if (DESTROY_BUILDER_TEMPLATE.matches(message)) {

				// Get the message
				Criteria value = (Criteria) message.getContent();

				// Delete it
				deleteAllBy(value);

				// Send Success Message
				response = new Message("SUCCESS", "SUCCESS", "Operation Completed Successfully",
						"Models deleted successfully");

			} else if (UPDATE_BUILDER_TEMPLATE.matches(message)) {

				// Get the message
				Map<Class<?>, Serializable> map = (Map<Class<?>, Serializable>) message.getContent();

				// Get the values
				Criteria critera = (Criteria) map.get(Criteria.class);
				T value = (T) map.get(Serializable.class);

				// Delete it
				updateAllBy(value, critera);

				// Send Success Message
				response = new Message("SUCCESS", "SUCCESS", "Operation Completed Successfully",
						"Models deleted successfully");

			} else {

				// Try to handle a custom message
				try {
					response = handleCustomMessage(message);
				} catch (InvalidMessageException e) {
					// Keep error message
					throw new RuntimeException("Message doesn't match template");
				}
			}
		} catch (Exception e) {

			// Keep error message
			response = MessageBuilder.fromTemplate(ERROR_BUILDER_TEMPLATE).setContent(e.getMessage()).build();
		}

		return response;
	}

	// Any class which later wants to handle custom messages
	public Message handleCustomMessage(Message input) throws InvalidMessageException {
		throw new InvalidMessageException("Message doesn't match template");
	}

}
