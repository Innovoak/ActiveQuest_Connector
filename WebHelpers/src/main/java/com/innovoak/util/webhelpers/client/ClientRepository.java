package com.innovoak.util.webhelpers.client;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.innovoak.util.webhelpers.Message;
import com.innovoak.util.webhelpers.Repository;
import com.innovoak.util.webhelpers.Message.MessageBuilder;
import com.innovoak.util.webhelpers.client.HttpMessageClient.HttpMessageClientBuilder;
import com.innovoak.util.webhelpers.criteria.Criteria;
import com.innovoak.util.webhelpers.criteria.PredicateCriteria;
import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.data.Model;
import com.innovoak.util.webhelpers.server.RepositoryServlet;

// Acts as a REST repository for the client side access from server
// Uses HttpConnection to get values (wrapped in a HttpMessageClient)
public abstract class ClientRepository<T extends Model> implements Repository<T> {

	// Method to get values
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllBy(SelectCriteria criteria) throws Exception {

		// Create a messenging service and send read message
		Message message = HttpMessageClientBuilder
				.create().setURL(getRepositoryURL()).setMessage(MessageBuilder
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
	public void deleteAllBy(PredicateCriteria criteria) throws Exception {
		// Create a messenging service and send read message
		Message message = HttpMessageClientBuilder
				.create().setURL(getRepositoryURL()).setMessage(MessageBuilder
						.fromTemplate(RepositoryServlet.DESTROY_BUILDER_TEMPLATE).setContent(criteria).build())
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
		Message message = HttpMessageClientBuilder
				.create().setURL(getRepositoryURL()).setMessage(MessageBuilder
						.fromTemplate(RepositoryServlet.CREATE_BUILDER_TEMPLATE).setContent(objects).build())
				.build().call();

		// Get message
		if (message.isError()) {
			throw new Exception(message.getContent().toString());
		}
	}

	// Method to update all values
	@Override
	public void updateAllBy(T object, PredicateCriteria criteria) throws Exception {
		// Create a messenging service and send update message
		Map<Class<?>, Serializable> map = new HashMap<>();
		map.put(Serializable.class, object);
		map.put(Criteria.class, criteria);
		
		Message message = HttpMessageClientBuilder.create()
				.setURL(getRepositoryURL()).setMessage(MessageBuilder.fromTemplate(RepositoryServlet.UPDATE_BUILDER_TEMPLATE)
						.setContent(map).build())
				.build().call();

		// Get message
		if (message.isError()) {
			throw new Exception(message.getContent().toString());
		}
	}

	protected abstract URL getRepositoryURL() throws MalformedURLException;

}
