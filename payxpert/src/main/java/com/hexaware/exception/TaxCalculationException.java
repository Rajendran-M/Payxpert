package com.hexaware.exception;
/**
 * Exception class for errors that occur during tax calculation.
 * This exception is thrown when an issue is encountered while calculating tax.
 */
@SuppressWarnings("serial")
public class TaxCalculationException extends Exception {

	
	
	public TaxCalculationException() {
		super();
		System.out.println("Problem occured while calculation of Tax");
	}

	public TaxCalculationException(String message) {
		super(message);
		System.out.println(message);
		
	}

	@Override
	public String toString() {
		return "TaxCalulationException []";
	}

}
