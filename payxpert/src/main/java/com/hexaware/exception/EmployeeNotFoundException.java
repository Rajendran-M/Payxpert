package com.hexaware.exception;
/**
 * Exception class for employee not found errors.
 * This exception is thrown when an employee with the specified ID is not found.
 */
@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception{

	
	
	
	public EmployeeNotFoundException() {
		super();
		System.out.println("Employee not found 'Invalid' employee data ");
			}
	/**
     * Constructs a new EmployeeNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
	public EmployeeNotFoundException(String message) {
		super(message);
		System.out.println(message);
			}

	@Override
	public String toString() {
		return "EmployeeNotFoundException []";
	}

	
	
	
}
