package com.aracenasc.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;
import java.sql.Connection; 

public class ConnectionUtil {
	private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	private static Connection conn = null;
	
	private ConnectionUtil() {
		super();
	}
	
	public static Connection getConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				//logger.info("Returned re-used connection object");
				return conn;
			}
			
		} catch (SQLException e) {
			logger.error("Failed to re-use a connection");
			e.printStackTrace();
			return null;
		}		
		
		try {
			Properties p = new Properties();
			p.load(new FileReader("E:\\D Drive\\Documents\\Revature\\Projects\\project-0-AracenaSC\\src\\main\\resources\\application.properties"));
			String url = p.getProperty("url");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			conn = DriverManager.getConnection(url, username, password);
			//logger.info("Database connection established");
			
		} catch (SQLException e) {
			logger.error("SQL Exception thrown: Failed to establish connection");
			e.printStackTrace();		
		
		} catch (FileNotFoundException e) {
			logger.error("Cannot locate application.properties file");
			e.printStackTrace();
	
		} catch (IOException e) {
			logger.error("IO Exception thrown: Confirm contents in the application.properties file");
			e.printStackTrace();
		}
		return conn; // if not successful, this will be null
	}
}
