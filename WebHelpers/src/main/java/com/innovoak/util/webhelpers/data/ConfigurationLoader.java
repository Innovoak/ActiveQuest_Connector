package com.innovoak.util.webhelpers.data;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import com.innovoak.util.webhelpers.PrimitiveConverter;
import com.innovoak.util.webhelpers.data.Configuration.ConfigurationBuilder;

public final class ConfigurationLoader {

	private ConfigurationLoader() {
	}

//	 Loads from a properties file - Sample looks like below
//	 defaultAutoCommit=true
//	 driverClassName=com.mysql.cj.jdbc.Driver
//	 url=https://localhost:3306/sampledb
//	 minIdle=10
//	 maxIdle=15
//	 maxOpenPreparedStatements=150
//	 username=root
//	 password=root
//	 poolPreparedStatements=true
	public static Configuration loadFromProperties(URL url) throws IOException {
		// Loads properties from file
		Properties properties = new Properties();
		properties.load(url.openStream());

		// Config builder
		ConfigurationBuilder builder = ConfigurationBuilder.create();

		// Get the property names
		Enumeration<?> e = properties.propertyNames();

		// Iterate over the keys
		while (e.hasMoreElements()) {
			// Get the string
			String key = (String) e.nextElement();
			String value = properties.getProperty(key);

			// Setting value to primitives before to string
			if (PrimitiveConverter.isBoolean(value))
				builder.setProperty(key, Boolean.valueOf(value));
			else if (PrimitiveConverter.isInt(value))
				builder.setProperty(key, Integer.valueOf(value));
			else if (PrimitiveConverter.isFloat(value))
				builder.setProperty(key, Float.valueOf(value));
			else if (PrimitiveConverter.isLong(value))
				builder.setProperty(key, Long.valueOf(value));
			else if (PrimitiveConverter.isDouble(value))
				builder.setProperty(key, Double.parseDouble(value));
			else
				builder.setProperty(key, value);

		}

		// Build the configuration
		return builder.build();
	}

}
