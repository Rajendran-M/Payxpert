package com.hexaware.daotest;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

import com.hexaware.dao.PayrollService;
import com.hexaware.util.DBConnUtil;

public class PayrollForEmployeesTest {
	@Test
	public void ProcessPayrollForMultipleEmployees() {
		 DBConnUtil.getConnection();
		 
	        PayrollService payrollService = new PayrollService();
	     
	        int employeeId = 102; 
	        Date startDate = new Date(); 
	        Date endDate = new Date();
	       
	       
	        
	        try {
	            payrollService.generatePayroll(employeeId,startDate,endDate);
	            
	        } catch (Exception e ) {
	            fail("Exception occurred: "+ e.getMessage());
	        }

	
		
		
	}
}
