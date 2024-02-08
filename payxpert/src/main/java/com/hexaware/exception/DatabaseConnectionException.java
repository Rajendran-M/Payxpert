package com.hexaware.exception;
/**
 * Exception class for database connection issues.
 * This exception is thrown when there are problems related to database connections.
 */
@SuppressWarnings("serial")
public class DatabaseConnectionException extends Exception{

	
	
	
	public DatabaseConnectionException() {
		super();
		System.out.println("The given DB input data doesn't meet the required criteria.");
	}

	public DatabaseConnectionException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "DatabaseConnectionException []";
	}

	
}
