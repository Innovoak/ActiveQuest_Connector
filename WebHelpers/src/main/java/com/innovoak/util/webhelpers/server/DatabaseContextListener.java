package com.innovoak.util.webhelpers.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.innovoak.util.webhelpers.data.Configuration;
import com.innovoak.util.webhelpers.data.DatabaseService;

public abstract class DatabaseContextListener implements ServletContextListener {
	private DatabaseService service;

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		service = DatabaseService.getInstance();
		service.setConfiguration(getConfiguration());
		service.open();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			service.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		service = null;
	}

	public abstract Configuration getConfiguration();

}
