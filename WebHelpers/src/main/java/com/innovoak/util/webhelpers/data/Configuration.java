package com.innovoak.util.webhelpers.data;

import java.util.Objects;

import com.innovoak.util.webhelpers.builders.AbstractBuilder;

// Represents the database configuration
public class Configuration {

	// Fields
	private boolean defaultAutoCommit;
	private int minIdle = 0;
	private int maxIdle = -1;
	private int maxOpenPreparedStatements = -1;
	private boolean poolPreparedStatements;
	private String driverClassName;
	private String url;
	private String username;
	private String password;

	// Constructors
	public Configuration() {
	}

	public Configuration(boolean defaultAutoCommit, String driverClassName, String url, int minIdle, int maxIdle,
			int maxOpenPreparedStatements, String username, String password, boolean poolPreparedStatements) {
		this.defaultAutoCommit = defaultAutoCommit;
		this.driverClassName = driverClassName;
		this.url = url;
		this.minIdle = minIdle;
		this.maxIdle = maxIdle;
		this.maxOpenPreparedStatements = maxOpenPreparedStatements;
		this.username = username;
		this.password = password;
		this.poolPreparedStatements = poolPreparedStatements;
	}

	// Getters and Setters
	public boolean isDefaultAutoCommit() {
		return defaultAutoCommit;
	}

	public void setDefaultAutoCommit(boolean defaultAutoCommit) {
		this.defaultAutoCommit = defaultAutoCommit;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxOpenPreparedStatements() {
		return maxOpenPreparedStatements;
	}

	public void setMaxOpenPreparedStatements(int maxOpenPreparedStatements) {
		this.maxOpenPreparedStatements = maxOpenPreparedStatements;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	public void setPoolPreparedStatements(boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	// Utility methods
	@Override
	public int hashCode() {
		return Objects.hash(defaultAutoCommit, driverClassName, maxIdle, maxOpenPreparedStatements, minIdle, password,
				poolPreparedStatements, url, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuration other = (Configuration) obj;
		return defaultAutoCommit == other.defaultAutoCommit && Objects.equals(driverClassName, other.driverClassName)
				&& maxIdle == other.maxIdle && maxOpenPreparedStatements == other.maxOpenPreparedStatements
				&& minIdle == other.minIdle && Objects.equals(password, other.password)
				&& poolPreparedStatements == other.poolPreparedStatements && Objects.equals(url, other.url)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Configuration [defaultAutoCommit=" + defaultAutoCommit + ", driverClassName=" + driverClassName
				+ ", url=" + url + ", minIdle=" + minIdle + ", maxIdle=" + maxIdle + ", maxOpenPreparedStatements="
				+ maxOpenPreparedStatements + ", username=" + username + ", password=" + password
				+ ", poolPreparedStatements=" + poolPreparedStatements + "]";
	}

	// Builder class
	public static class ConfigurationBuilder extends AbstractBuilder<Configuration> {

		private ConfigurationBuilder() {
		}

		// Getters and Setters
		public ConfigurationBuilder setDefaultAutoCommit(boolean defaultAutoCommit) {
			setProperty("defaultAutoCommit", defaultAutoCommit);
			return this;
		}

		public ConfigurationBuilder setDriverClassName(String driverClassName) {
			setProperty("driverClassName", driverClassName);
			return this;
		}

		public ConfigurationBuilder setUrl(String url) {
			setProperty("url", url);
			return this;
		}

		public ConfigurationBuilder setMinIdle(int minIdle) {
			setProperty("minIdle", minIdle);
			return this;
		}

		public ConfigurationBuilder setMaxIdle(int maxIdle) {
			setProperty("maxIdle", maxIdle);
			return this;
		}

		public ConfigurationBuilder setMaxOpenPreparedStatements(int maxOpenPreparedStatements) {
			setProperty("maxOpenPreparedStatements", maxOpenPreparedStatements);
			return this;
		}

		public ConfigurationBuilder setUsername(String username) {
			setProperty("username", username);
			return this;
		}

		public ConfigurationBuilder setPassword(String password) {
			setProperty("password", password);
			return this;
		}

		public ConfigurationBuilder setPoolPreparedStatements(boolean poolPreparedStatements) {
			setProperty("poolPreparedStatements", poolPreparedStatements);
			return this;
		}

		@Override
		protected Configuration newInstance() {
			return new Configuration();
		}

		public static ConfigurationBuilder create() {
			return new ConfigurationBuilder();
		}

	}

}
