package com.hexaware.entitytest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.hexaware.model.Payroll;

public class CalculateGrossSalaryForEmployeeTest {
	
	@Test
	public void calculateGrossSalaryForEmployee() {
	Payroll payrollCheck = new Payroll();
	payrollCheck.setBasicSalary(7000.0);
	payrollCheck.setOvertimePay(1000.0);
	double checkGross =payrollCheck.getBasicSalary()+payrollCheck.getOvertimePay();
	assertEquals(8000.0,checkGross,0.001);
}
}