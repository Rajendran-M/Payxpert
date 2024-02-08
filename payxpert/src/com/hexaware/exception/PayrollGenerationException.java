package com.hexaware.exception;

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
