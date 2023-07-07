package com.innovoak.util.webhelpers.data.internal;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.PooledConnection;

public class BasicPoolingDataSource implements DataSource, ConnectionPoolDataSource, AutoCloseable {
	// TODO: FIX THIS SOON
	// Acts as a connection pool
	private BlockingQueue<Connection> idleConnectionPool;
	private List<Connection> usedConnections;

	// Logging, parent logger for logging
	private static final Logger PARENT_LOGGER = Logger.getLogger(DataSource.class.getName());
	private final Logger LOGGER = Logger.getLogger(BasicPoolingDataSource.class.getName());

	// Print Writer
	private PrintWriter logWriter;
	private OutputStream outputStream;

	public BasicPoolingDataSource() {
		
		
		// configure the logger
		LOGGER.setParent(PARENT_LOGGER);
		LOGGER.addHandler(new StreamHandler());
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return LOGGER;
	}

	// Not supported
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new SQLException("This is not a wrapper");
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	// Connections
	// TODO: Must implement these connections
	@Override
	public PooledConnection getPooledConnection() throws SQLException {
		return null;
	}

	@Override
	public PooledConnection getPooledConnection(String user, String password) throws SQLException {
		return null;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return null;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
