package com.hexaware.model;

import java.util.Date;

public class FinancialRecord {
  private int recordId;
  private int employeeID;
  private Date recordDate;
  private String description;
  private double amount;
  private String recordType;

  
  
  public FinancialRecord() {
	  super();
  }
  
  
  public FinancialRecord(int recordId, int employeeID, Date recordDate, String description, double amount,
		String recordType) {
	super();
	this.recordId = recordId;
	this.employeeID = employeeID;
	this.recordDate = recordDate;
	this.description = description;
	this.amount = amount;
	this.recordType = recordType;
}



public int getRecordId() {
	return recordId;
}



public void setRecordId(int recordId) {
	this.recordId = recordId;
}



public int getEmployeeID() {
	return employeeID;
}



public void setEmployeeID(int employeeID) {
	this.employeeID = employeeID;
}



public Date getRecordDate() {
	return recordDate;
}



public void setRecordDate(Date recordDate) {
	this.recordDate = recordDate;
}



public String getDescription() {
	return description;
}



public void setDescription(String description) {
	this.description = description;
}



public double getAmount() {
	return amount;
}



public void setAmount(int amount) {
	this.amount = amount;
}



public String getRecordType() {
	return recordType;
}



public void setRecordType(String recordType) {
	this.recordType = recordType;
}



@Override
public String toString() {
	return "\nFinancialRecord [recordId=" + recordId + ", employeeID=" + employeeID + ", recordDate=" + recordDate
			+ ", description=" + description + ", amount=" + amount + ", recordType=" + recordType + "]\n";
}
  
}
