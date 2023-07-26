package com.innovoak.util.webhelpers.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import com.innovoak.util.webhelpers.Message;
import com.innovoak.util.webhelpers.builders.AbstractBuilder;
import com.innovoak.util.webhelpers.builders.ConstructableBuilder;

// Create a messenger client
public class HttpMessageClient implements Callable<Message> {

	public static enum ContentType {
		JAVA_OBJECT, XML, JSON
	}
	// Create the fields
	private Message input;
	private URL url;
	private ContentType contentType = ContentType.JAVA_OBJECT;
	

	// Private constructor
	private HttpMessageClient(URL location, Message input) {
		this.input = input;
		this.url = location;
	}

	// Receive outputted message
	public Message call() throws Exception {
		// Open a connection
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);

		// Set input
		Message message = input;

		// Write to the server
		try (ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream())) {
			out.writeObject(message);
		}

		// get message back from the server
		try (ObjectInputStream in = new ObjectInputStream(connection.getInputStream())) {
			return (Message) in.readObject();
		}
	}

	// Create a builder for the httpmessagebuilder
	public static class HttpMessageClientBuilder extends AbstractBuilder<HttpMessageClient> {
		private URL url;
		private Message message;

		// Set the values
		public HttpMessageClientBuilder setURL(URL url) {
			this.url = url;
			return this;
		}

		// Set the values
		public HttpMessageClientBuilder setMessage(Message body) {
			this.message = body;
			return this;
		}

		@Override
		public HttpMessageClient newInstance() {
			return new HttpMessageClient(url, message);
		}

		// Add the class
		private HttpMessageClientBuilder() {
		}

		// Factory method
		public static HttpMessageClientBuilder create() {
			return new HttpMessageClientBuilder();
		}

	}
}
