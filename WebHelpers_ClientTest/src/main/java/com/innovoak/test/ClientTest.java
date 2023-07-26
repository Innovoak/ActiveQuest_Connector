package com.innovoak.test;

import java.net.URL;

import com.innovoak.util.webhelpers.Message;
import com.innovoak.util.webhelpers.Message.MessageBuilder;
import com.innovoak.util.webhelpers.client.HttpMessageClient.HttpMessageClientBuilder;

public class ClientTest {

	public static void main(String[] args) throws Exception {

		Message message = MessageBuilder.create().setName("Cool Message").setAction("Cool Action")
				.setDescription("Cool Description").setContent("Cool Content").build();

		System.out.println("Sending Message: " + message);

		message = HttpMessageClientBuilder.create().setURL(new URL("http://localhost:8080/WebHelpers_ServerTest/test"))
				.setMessage(message).build().call();

		System.out.println("Receiving Message: " + message);

	}
}
