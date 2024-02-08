package com.hexaware.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception{

	
	
	
	public EmployeeNotFoundException() {
		super();
		System.out.println("Employee not found 'Invalid' employee data ");
			}

	public EmployeeNotFoundException(String message) {
		super(message);
		System.out.println(message);
			}

	@Override
	public String toString() {
		return "EmployeeNotFoundException []";
	}

	
	
	
}
