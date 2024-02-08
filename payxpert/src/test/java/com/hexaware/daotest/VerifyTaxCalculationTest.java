package com.hexaware.daotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.Year;

import org.junit.Test;

import com.hexaware.dao.TaxService;
import com.hexaware.util.DBConnUtil;

public class VerifyTaxCalculationTest {
	@Test
	public void VerifyTaxCalculationForHighIncomeEmployee() {
		DBConnUtil.getConnection();
		TaxService taxService = new TaxService();
		int employeeId = 107; // Replace with a valid employee ID in your database
		Year taxYear = Year.of(2024); // Replace with the desired tax year

		try {
			double tax = taxService.calculateTax(employeeId, taxYear);
			System.out.println(tax);
			double expectedValue = 1421.0;
			assertEquals(expectedValue, tax, 0.01);
		} catch (Exception e) {
			fail("Exception occurred: " + e.getMessage());
		}

	}

}
