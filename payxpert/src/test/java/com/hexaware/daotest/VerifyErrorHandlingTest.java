package com.hexaware.daotest;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import com.hexaware.exception.*;
import com.hexaware.dao.EmployeeService;
import com.hexaware.model.Employee;
import com.hexaware.util.DBConnUtil;

public class VerifyErrorHandlingTest {

	@Test(expected = NullPointerException.class)
	public void VerifyErrorHandlingForInvalidEmployeeData() throws ParseException,EmployeeNotFoundException {

		DBConnUtil.getConnection();

		int employeeID = 108;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Employee employee1 = new Employee(106, "toni", "m", sdf.parse("2006-12-09"), "male", "toni@123.com","695869696", "50B,goodshed,Ramnad", "SDE", sdf.parse("2023-01-02"), null);

		EmployeeService employee = new EmployeeService();

		Employee employee2 = employee.getEmployeeById(employeeID);
        
		assertTrue((employee1.getFirstName()).equals(employee2.getFirstName()));

	}

}
