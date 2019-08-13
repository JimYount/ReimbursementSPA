package com.revature.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtil {
	// Singleton Connection Factory.
	// ConnectionUtil is a singleton object that makes Connections to DB
	private static ConnectionUtil cu = null;
	private static Properties prop;
	
	private ConnectionUtil() {
		prop = new Properties();
		try {
			// Using the class loader to reduce reliance on file system
			InputStream dbProps = ConnectionUtil.class.getClassLoader()
					.getResourceAsStream("database.properties");
			prop.load(dbProps);
		} catch(Exception e) {
			LogUtil.logException(e, ConnectionUtil.class);
		}
	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if(cu == null) 
			cu = new ConnectionUtil();
		return cu;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			// We have to register our driver class
			Class.forName(prop.getProperty("drv"));
			conn = DriverManager.getConnection(prop.getProperty("url"),
					prop.getProperty("usr"),
					prop.getProperty("pswd"));
		} catch(Exception e) {
			LogUtil.logException(e, ConnectionUtil.class);
		}
		return conn;
	}
}
