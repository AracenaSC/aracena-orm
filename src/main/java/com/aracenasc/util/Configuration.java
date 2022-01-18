package com.aracenasc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * The purpose of this class is to have the User only provide a few
 * things in order for the ORM to establish a connection and build the tables
 * based on a list of User-Defined classes that the user passes to the ORM to
 * iuntrospect and construct in the DB 
 *
 */
public class Configuration {
	private static Logger logger = Logger.getLogger(Configuration.class);
	private static Connection conn = null;
	
	public static String dbUrl;
	public static String dbUsername;
	public static String dbPassword;
	// this is the list of classes that the user wants our ORM to "scan" aka introspect and build 
	// as DB objects
	private List<MetaModel<Class<?>>> metaModelList;
	
	// This method doesn't technically follow SRP Single Responsibility Principle
	public Configuration addAnnotatedClass(Class annotatedClass) {
		
		// first check if a linked List has been instantiated...
		// if not, instantiate it!
		if (metaModelList == null) {
			metaModelList = new LinkedList<>();
		}
		
		// we need to call the method that transforms a class into an appropriate
		// data model that our ORM can introspect (a MetaModel)
		metaModelList.add(MetaModel.of(annotatedClass));
		
		return this; // returns the configuration object on which the addAnnotatedClass() method is being called
	}
	
	public List<MetaModel<Class<?>>> getMetaModels() {
		
		// in the case that the metaModelList of the Configuration object is empty, return an empty list.
		// otherwise, return the metaModelList.
		return (metaModelList == null) ? Collections.emptyList() : metaModelList;
	}
	
	public void setConnection(String url, String username, String password) {
		
		dbUrl = url;
		dbUsername = username;
		dbPassword = password;
	}
	// return a Connection object OR call on a separate class like Connection Util
	public static Connection getConnection(String url, String username, String password) {
	
		try {
			if (conn != null && !conn.isClosed()) {
				logger.info("Returned re-used connection object");
				return conn;
			}
			
		} catch (SQLException e) {
			logger.error("Failed to re-use a connection");
			e.printStackTrace();
			return null;
		}
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			logger.info("Database connection established");
			
		} catch (SQLException e) {
			logger.error("SQL Exception thrown: Failed to establish connection");
			e.printStackTrace();	
		}
		
		return conn;
		
	}
}