package com.hexaware.dao;

import java.util.List;

import com.hexaware.model.Employee;

public interface IEmployeeService {
	Employee getEmployeeById(int employeeId);
	List<Employee>getAllEmployees();
    void addEmployee(Employee employeeData);
    Employee updateEmployee(Employee employeeData);
    void removeEmployee(int employeeId);
	

}
