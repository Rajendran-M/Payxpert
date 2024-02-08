package com.hexaware.entitytest;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.hexaware.model.Payroll;

public class CalculateNetSalaryAfterDeductionsTest {
	

	@Test
	public void CalculateNetSalaryAfterDeduction() {
		Payroll payrollCheck = new Payroll();
		payrollCheck.setBasicSalary(7000.0);
		payrollCheck.setOvertimePay(1000.0);
		payrollCheck.setDeductions(500.0);
		payrollCheck.setNetSalary();//
		double netSalary =7000+1000-500;
		 assertEquals(netSalary,payrollCheck.getNetSalary(), 0.001);
		
	}

}
