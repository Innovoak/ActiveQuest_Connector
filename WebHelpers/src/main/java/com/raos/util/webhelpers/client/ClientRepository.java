package com.raos.util.webhelpers.client;

import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.raos.util.webhelpers.Message;
import com.raos.util.webhelpers.Message.MessageBuilder;
import com.raos.util.webhelpers.Repository;
import com.raos.util.webhelpers.client.HttpMessageClient.HttpMessageClientBuilder;
import com.raos.util.webhelpers.criteria.Criteria;
import com.raos.util.webhelpers.server.RepositoryServlet;

// Acts as a REST repository for the client side access from server
// Uses HttpConnection to get values (wrapped in a HttpMessageClient)
public abstract class ClientRepository<T extends Serializable> implements Repository<T> {

	// Method to get values
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllBy(Criteria criteria) throws Exception {

		// Create a messenging service and send read message
		Message message = HttpMessageClientBuilder
				.create().setValues(getRepositoryURL(), MessageBuilder
						.fromTemplate(RepositoryServlet.READ_BUILDER_TEMPLATE).setContent(criteria).build())
				.build().call();

		// Get message
		if (message.isError()) {
			throw new Exception(message.getContent().toString());
		} else {
			// Cast to list
			return (List<T>) message.getContent();
		}
	}

	// Method to delete values
	@Override
	public void deleteAllBy(Criteria criteria) throws Exception {
		// Create a messenging service and send read message
		Message message = HttpMessageClientBuilder
				.create().setValues(getRepositoryURL(), MessageBuilder
						.fromTemplate(RepositoryServlet.DESTROY_BUILDER_TEMPLATE).setContent(criteria).build())
				.build().call();

		// Get message
		if (message.isError()) {
			throw new Exception(message.getContent().toString());
		}
	}

	// Method to insert a singular value
	@Override
	public void insert(T object) throws Exception {
		// Create a messenging service and send create message
		Message message = HttpMessageClientBuilder.create()
				.setValues(getRepositoryURL(), MessageBuilder
						.fromTemplate(RepositoryServlet.CREATE_SINGLE_BUILDER_TEMPLATE).setContent(object).build())
				.build().call();

		// Get message
		if (message.isError()) {
			throw new Exception(message.getContent().toString());
		}
	}

	// Method to insert values
	@Override
	public void insertAll(List<T> objects) throws Exception {
		// Create a messenging service and send create message
		Message message = HttpMessageClientBuilder.create()
				.setValues(getRepositoryURL(), MessageBuilder
						.fromTemplate(RepositoryServlet.CREATE_MULTI_BUILDER_TEMPLATE).setContent(objects).build())
				.build().call();

		// Get message
		if (message.isError()) {
			throw new Exception(message.getContent().toString());
		}
	}

	// Method to update all values
	@Override
	public void updateAllBy(T object, Criteria criteria) throws Exception {
		// Create a messenging service and send update message
		Message message = HttpMessageClientBuilder.create()
				.setValues(getRepositoryURL(), MessageBuilder.fromTemplate(RepositoryServlet.UPDATE_BUILDER_TEMPLATE)
						.setContent(Arrays.<Serializable>asList(object, criteria)).build())
				.build().call();

		// Get message
		if (message.isError()) {
			throw new Exception(message.getContent().toString());
		}
	}

	protected abstract URL getRepositoryURL();

}
