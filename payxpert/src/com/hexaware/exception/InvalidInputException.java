package com.hexaware.exception;

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
