package com.hexaware.exception;

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
