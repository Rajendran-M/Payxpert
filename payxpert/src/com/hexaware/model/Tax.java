package com.hexaware.model;

import java.time.Year;

public class Tax {
   private int taxID;
   private int employeeID;
   private Year taxYear;
   private double taxableIncome;
   private double taxAmount;

   
   public Tax() {
	  super(); 
   }
   public Tax(int taxID, int employeeID, Year taxYear, double taxableIncome, double taxAmount) {
	super();
	this.taxID = taxID;
	this.employeeID = employeeID;
	this.taxYear = taxYear;
	this.taxableIncome = taxableIncome;
	this.taxAmount = taxAmount;
}



public int getTaxID() {
	return taxID;
}



public void setTaxID(int taxID) {
	this.taxID = taxID;
}



public int getEmployeeID() {
	return employeeID;
}



public void setEmployeeID(int employeeID) {
	this.employeeID = employeeID;
}



public Year getTaxYear() {
	return taxYear;
}



public void setTaxYear(Year taxYear) {
	this.taxYear = taxYear;
}



public double getTaxableIncome() {
	return taxableIncome;
}



public void setTaxableIncome(int taxableIncome) {
	this.taxableIncome = taxableIncome;
}



public double getTaxAmount() {
	return taxAmount;
}



public void setTaxAmount(int taxAmount) {
	this.taxAmount = taxAmount;
}



@Override
public String toString() {
	return "\nTax [taxID=" + taxID + ", employeeID=" + employeeID + ", taxYear=" + taxYear + ", taxableIncome="
			+ taxableIncome + ", taxAmount=" + taxAmount + "]\n";
}
   
}
