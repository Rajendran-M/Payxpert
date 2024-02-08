package com.hexaware.dao;

import com.hexaware.model.Employee;
import java.util.List;

/**
 * Interface for Employee Service operations.
 * Provides methods to retrieve, add, update, and remove employee records.
 */
public interface IEmployeeService {


	 /**
     * Retrieves an employee by their ID.
     *
     * @param employeeId the ID of the employee to retrieve.
     * @return the Employee object containing details of the employee with the given ID.
     */
  Employee getEmployeeById(int employeeId);
	
  /**
   * Retrieves details of all employees.
   *
   * @return a list of Employee objects containing details of all employees.
   */
	
  List<Employee>getAllEmployees();

  /**
   * Adds a new employee to the database.
   *
   * @param employeeData the Employee object containing details of the new employee.
   */
  void addEmployee(Employee employeeData);
    
	/**
	 * Updates the details of an existing employee.
	 *
	 * @param employeeData The Employee object containing updated details.
	 * @return The updated Employee object.
	 */
  Employee updateEmployee(Employee employeeData);
    
    /**
	 * Removes an employee from the database based on the specified employee ID.
	 *
	 * @param employeeId The ID of the employee to be removed.
	 *                                   found.
	 */
    
  void removeEmployee(int employeeId);
    
	

}
