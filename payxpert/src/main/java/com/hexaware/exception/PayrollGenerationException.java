package com.hexaware.exception;
/**
 * Exception class for errors that occur during payroll generation.
 * This exception is thrown when an issue is encountered while generating payroll.
 */
@SuppressWarnings("serial")
public class PayrollGenerationException extends Exception {

	
	
	public PayrollGenerationException() {
		super();
		System.out.println("Something went Wrong while 'Generating payroll'");
	}

	public PayrollGenerationException(String message) {
		super(message);System.out.println(message);
	}

	@Override
	public String toString() {
		return "PayrollGenerationException []";
	}
	 
	

}
