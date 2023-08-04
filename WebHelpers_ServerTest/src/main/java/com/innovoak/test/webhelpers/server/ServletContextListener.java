package com.innovoak.test.webhelpers.server;

import javax.servlet.annotation.WebListener;

import com.innovoak.util.webhelpers.data.Configuration;
import com.innovoak.util.webhelpers.data.Configuration.ConfigurationBuilder;
import com.innovoak.util.webhelpers.server.DatabaseContextListener;

@WebListener
public class ServletContextListener extends DatabaseContextListener {

    @Override
    public Configuration getConfiguration() {
    	
        return ConfigurationBuilder.create().setDriverClassName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/homework_database").setUsername("root").setPassword("root")
                .setDefaultAutoCommit(true).setMaxIdle(15).setMinIdle(10).setMaxOpenPreparedStatements(150).build();
    }

}

