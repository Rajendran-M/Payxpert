package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.hexaware.exception.DatabaseConnectionException;

public class DBConnUtil {

	static Connection connection;

	@SuppressWarnings("unlikely-arg-type")
	public static Connection getConnection() {
		DBPropertyUtil.getConnection();
		String url = DBPropertyUtil.url;
		String user = DBPropertyUtil.user;
		String password = DBPropertyUtil.password;

		try {
			connection = DriverManager.getConnection(url, user, password);
	       	   
		if (connection.equals("null")) {
			throw new DatabaseConnectionException();
		}
		} catch (DatabaseConnectionException e) {
			System.out.println("Check DataBase connection");
			e.printStackTrace(); 
		}
		
		
		catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;

	}

}
