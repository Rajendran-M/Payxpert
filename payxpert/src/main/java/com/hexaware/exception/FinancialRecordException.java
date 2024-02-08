package com.hexaware.exception;
/**
 * Exception class for financial record-related errors.
 * This exception is thrown when there is an issue with financial record management.
 */
@SuppressWarnings("serial")
public class FinancialRecordException extends Exception {

	
	public FinancialRecordException() {
		super();
		System.out.println("FinancialRecordException occured there is an issue with financial record management.");
	}

	public FinancialRecordException(String message) {
		super(message);
	System.out.println(message);
	}

	@Override
	public String toString() {
		return "FinancialRecordException []";
	}

	
}
