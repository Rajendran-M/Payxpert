package com.hexaware.dao;

import java.time.Year;
import java.util.List;

import com.hexaware.model.Tax;

public interface ITaxService {
	double calculateTax(int employeeId,Year taxYear);
	Tax getTaxById(int taxId);
	List <Tax> getTaxesForEmployee(int employeeId);
	List<Tax>getTaxesForYear(Year taxYear);
	
}
