package com.elcuto.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {
	private static DBConnection dbconnection;
	public Connection connection;
	
	
	private DBConnection() throws SQLException {
		
        Properties connectionProps = new Properties();
        connectionProps.put("user", "edwin");
        connectionProps.put("password", "123456");
        String dbName = "elc3rdPartySmsService";
        String connectionUrl = "jdbc:sqlserver://DESKTOP-M1U6LN4\\SQLEXPRESS;databaseName="+dbName;

        connection = DriverManager.getConnection(connectionUrl, connectionProps);
	}
	
	public static DBConnection getInstance() throws SQLException {
		if (dbconnection == null) {
            dbconnection = new DBConnection();
        }
        return dbconnection;
	}
	
	public Connection getConnection() throws SQLException {
        return connection; 
    }
}

