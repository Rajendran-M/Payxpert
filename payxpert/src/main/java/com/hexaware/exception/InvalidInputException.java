package com.hexaware.exception;
/**
 * Exception class for invalid input errors.
 * This exception is thrown when the input data does not meet the required criteria.
 */
@SuppressWarnings("serial")
public class InvalidInputException extends Exception{

	
	
	public InvalidInputException() {
		super();
		System.out.println("input data doesn't meet the required criteria.");	}

	public InvalidInputException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "InvalidInputException []";
	}

	
}
