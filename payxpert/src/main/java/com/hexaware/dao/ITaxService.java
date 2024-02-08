package com.hexaware.dao;

import java.time.Year;
import java.util.List;

import com.hexaware.model.Tax;
/**
 * Interface for Tax Service operations.
 * Provides methods to calculate tax, retrieve tax details by ID, and get tax records for employees and years.
 */

public interface ITaxService {
	double calculateTax(int employeeId,Year taxYear);
	Tax getTaxById(int taxId);
	List <Tax> getTaxesForEmployee(int employeeId);
	List<Tax>getTaxesForYear(Year taxYear);

}
