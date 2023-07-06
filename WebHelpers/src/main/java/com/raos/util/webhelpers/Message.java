package com.raos.util.webhelpers;

import java.io.Serializable;
import java.util.Objects;

import com.raos.util.webhelpers.builders.AbstractBuilder;

// Message
public final class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
	private String action;
	private String name;
	private String description;
	private Object content;

	// Constructors
	public Message() {
	}

	public Message(String action, String name, String description, Object content) {
		super();
		this.action = action;
		this.name = name;
		this.description = description;
		this.content = content;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	// Utility methods
	@Override
	public int hashCode() {
		return Objects.hash(action, content, description, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(action, other.action) && Objects.equals(content, other.content)
				&& Objects.equals(description, other.description) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Message [action=" + action + ", name=" + name + ", description=" + description + ", content=" + content
				+ "]";
	}

	public boolean isSuccess() {
		return action.equalsIgnoreCase("success");
	}

	public boolean isError() {
		return action.equalsIgnoreCase("error");
	}

	// Message Builder
	public static class MessageBuilder extends AbstractBuilder<Message> {

		// Constructor
		private MessageBuilder() {
		}

		// Setters
		public MessageBuilder setName(String name) {
			setProperty("name", name);
			return this;
		}

		public MessageBuilder setDescription(String description) {
			setProperty("description", description);
			return this;
		}

		public MessageBuilder setContent(Object content) {
			setProperty("content", content);
			return this;
		}

		public MessageBuilder setAction(String action) {
			setProperty("action", action);
			return this;
		}

		// Build method
		@Override
		protected Message newInstance() {
			return new Message();
		}

		// Factory method
		public static MessageBuilder create() {
			return new MessageBuilder();
		}

		public static MessageBuilder fromTemplate(MessageBuilder other) {
			return (MessageBuilder) create().copy(other);
		}

		// Check if the message matches
		public boolean matches(Message message) {
			return message.getAction().equals(properties.get("action"))
					&& message.getName().equals(properties.get("name"));
		}
	}
}
