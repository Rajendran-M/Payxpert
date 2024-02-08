package com.hexaware.dao;

import java.util.ArrayList;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import com.hexaware.util.DBConnUtil;
import com.hexaware.exception.EmployeeNotFoundException;
import com.hexaware.exception.InvalidInputException;
import com.hexaware.model.Employee;
import java.util.List;

public class EmployeeService implements IEmployeeService {
@Override
	public Employee getEmployeeById(int employeeId) {

		try {
			Connection con = DBConnUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE EMPLOYEEID = ?");

			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int employeeID = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Date dateOfBrith = rs.getDate(4);
				String gender = rs.getString(5);
				String email = rs.getString(6);
				String phoneNumber = rs.getString(7);
				String address = rs.getString(8);
				String position = rs.getString(9);
				Date joiningDate = rs.getDate(10);
				Date terminationDate = rs.getDate(11);
				Employee employee = new Employee(employeeID, firstName, lastName, dateOfBrith, gender, email,
						phoneNumber, address, position, joiningDate, terminationDate);
				return employee;

			} else {
				throw new EmployeeNotFoundException("Invalid Employee ID");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (EmployeeNotFoundException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		Employee employee;
		List<Employee> employeeList = new ArrayList<>();
		try {
			Connection con = DBConnUtil.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");
			while (rs.next()) {
				int employeeID = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Date dateOfBrith = rs.getDate(4);
				String gender = rs.getString(5);
				String email = rs.getString(6);
				String phoneNumber = rs.getString(7);
				String address = rs.getString(8);
				String position = rs.getString(9);
				Date joiningDate = rs.getDate(10);
				Date terminationDate = rs.getDate(11);
				employee = new Employee(employeeID, firstName, lastName, dateOfBrith, gender, email, phoneNumber,
						address, position, joiningDate, terminationDate);
				employeeList.add(employee);

			}
			if (employeeList.isEmpty()) {
				throw new EmployeeNotFoundException("There is no Employee data in record");
			}
			return employeeList;
		} catch (EmployeeNotFoundException e) {

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void addEmployee(Employee employeeData) {
		try {

			Connection con = DBConnUtil.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, employeeData.getEmployeeID());
			ps.setString(2, employeeData.getFirstName());
			ps.setString(3, employeeData.getLastName());
			ps.setObject(4, employeeData.getDateOfBrith());
			ps.setString(5, employeeData.getGender());
			ps.setString(6, employeeData.getEmail());
			ps.setString(7, employeeData.getPhoneNumber());
			ps.setString(8, employeeData.getAddress());
			ps.setString(9, employeeData.getPosition());
			ps.setObject(10, employeeData.getJoiningDate());
			ps.setObject(11, employeeData.getTerminationDate());

			if (ps.executeUpdate() > 0) {
				System.out.println("Employee Added Successfully..");
			} else {
				throw new InvalidInputException();

			}

		} catch (InvalidInputException e) {
			System.out.println("Enter valid input");

			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("Check 'EmployeeId'");
			e.printStackTrace();
		}

	}

	@Override
	public Employee updateEmployee(Employee employeeData) {

		Connection con = DBConnUtil.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE EMPLOYEE SET FIRSTNAME = ?,LastName=?, "
		+ "DateOfBirth=?,Gender=?, Email=?, PhoneNumber=?, Address=?, Position=?," + "JoiningDate=?,"
					+ "TerminationDate=? WHERE EmployeeID=?");

			ps.setString(1, employeeData.getFirstName());
			ps.setString(2, employeeData.getLastName());
			ps.setObject(3, employeeData.getDateOfBrith());
			ps.setString(4, employeeData.getGender());
			ps.setString(5, employeeData.getEmail());
			ps.setString(6, employeeData.getPhoneNumber());
			ps.setString(7, employeeData.getAddress());
			ps.setString(8, employeeData.getPosition());
			ps.setObject(9, employeeData.getJoiningDate());
			ps.setObject(10, employeeData.getTerminationDate());
			ps.setInt(11, employeeData.getEmployeeID());

			if (ps.executeUpdate() > 0) {
				System.out.println("Employee Updated Successfully..");
				return employeeData;
			} else {
				throw new InvalidInputException();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (InvalidInputException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void removeEmployee(int employeeId) {
		Connection con = DBConnUtil.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("Delete FROM EMPLOYEE WHERE EMPLOYEEID = ?");
			ps.setInt(1, employeeId);
			if (ps.executeUpdate() > 0) {
				System.out.println("Employee Removed...");
			} else {
				throw new EmployeeNotFoundException("There is no Employee data in record");
			}

		} catch (EmployeeNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Sql exception occurred");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
