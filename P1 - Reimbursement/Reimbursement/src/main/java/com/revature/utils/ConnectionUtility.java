package com.revature.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtility {
	private static Properties properties;
	private static ConnectionUtility conn;

	private ConnectionUtility() throws IOException {
		properties = new Properties();
		properties.load(ConnectionUtility.class.getClassLoader().getResourceAsStream("database.properties"));
	}

	public static synchronized ConnectionUtility getConnectionUtil() throws IOException {
		if (conn == null)
			conn = new ConnectionUtility();
		
		conn.getConnection();
		
		return conn;
	}

	public Connection getConnection() {
		try  {
			Class.forName(properties.getProperty("drv"));
			Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("pass"));
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("getting here");
		return null;
	}
}
