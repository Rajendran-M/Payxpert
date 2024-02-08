package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hexaware.exception.*;
import com.hexaware.model.Payroll;
import com.hexaware.util.DBConnUtil;
/**
 * This class provides services related to payroll management such as generating payroll,
 * retrieving payroll details by ID, retrieving payrolls for employees, and retrieving
 * payrolls for a specific period. It implements the IPayrollService interface.
 */
public class PayrollService implements IPayrollService {
	  /**
     * Generates payroll for a specific employee within the given period.
     *
     * @param employeeID The ID of the employee for whom payroll needs to be generated.
     * @param startDate  The start date of the pay period.
     * @param endDate    The end date of the pay period.
     */
	@Override
	public void generatePayroll(int employeeID, Date startDate, Date endDate) {

		Connection con = DBConnUtil.getConnection();

		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("SELECT position FROM EMPLOYEE WHERE EMPLOYEEID = ?");
			preparedStatement.setInt(1, employeeID);

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String position = rs.getString(1);
				double basicSalary = 15000;
				double overtimePay = 6000;
				double deduction = 700;

				if (position.equalsIgnoreCase("Manager") || position.equalsIgnoreCase("HR Specialist")) {
					basicSalary = 30000;
					overtimePay = 4000;
					deduction = 1200;
				}
				double netSalary = basicSalary + overtimePay - deduction;

				PreparedStatement ps = con
						.prepareStatement("INSERT INTO PAYROLL(EmployeeID, PayPeriodStartDate, PayPeriodEndDate, "
								+ "BasicSalary, OvertimePay, Deductions, NetSalary) VALUES (?,?,?,?,?,?,?) ");
				ps.setInt(1, employeeID);
				ps.setObject(2, startDate);
				ps.setObject(3, endDate);
				ps.setDouble(4, basicSalary);
				ps.setDouble(5, overtimePay);
				ps.setDouble(6, deduction);
				ps.setDouble(7, netSalary);

				if (ps.executeUpdate() > 0) {
					System.out.println("Payroll Generated..");
				} else {
					throw new PayrollGenerationException("Failed to generate payroll.");
				}
			} else {
				System.out.println("Employee not found.");
			}

		} catch (Exception e) {
			System.out.println("Error in generating payroll.");
			e.printStackTrace();
		}
	}
	/**
     * Retrieves payroll details by the specified payroll ID.
     *
     * @param payrollId The ID of the payroll to retrieve.
     * @return A Payroll object containing details of the payroll with the given ID.
     */
	@Override
	public Payroll getPayrollById(int payrollId) {

		try {
			Connection con = DBConnUtil.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM PAYROLL WHERE PAYROLLID =?");
			ps.setInt(1, payrollId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int payrollID = rs.getInt(1);
				int employeeID = rs.getInt(2);
				Date payPeriodStartDate = rs.getDate(3);
				Date payPeriodEndDate = rs.getDate(4);
				double basicSalary = rs.getDouble(5);
				double overtimePay = rs.getDouble(6);
				double deductions = rs.getDouble(7);
				double netSalary = rs.getDouble(8);
				Payroll payrollObj = new Payroll(payrollID, employeeID, payPeriodStartDate, payPeriodEndDate,
						basicSalary, overtimePay, deductions, netSalary);

				return payrollObj;
			} else {
				System.out.println("Payroll Id not exists" + payrollId);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}
    /**
     * Retrieves all payrolls for the specified employee.
     *
     * @param employeeId The ID of the employee.
     * @return A List containing all payrolls for the specified employee.
     */
	@Override
	public List<Payroll> getPayrollsForEmployee(int employeeId) {

		try {
			Connection con = DBConnUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Payroll WHERE EmployeeID = ?");
			pstmt.setInt(1, employeeId);
			ResultSet resultSet = pstmt.executeQuery();

			List<Payroll> empPayrollList = new ArrayList<>();

			while (resultSet.next()) {
				int payrollID = resultSet.getInt("PayrollID");
				int employeeID = resultSet.getInt("EmployeeID");
				Date payPeriodStartDate = resultSet.getDate("PayPeriodStartDate");
				Date payPeriodEndDate = resultSet.getDate("PayPeriodEndDate");
				double basicSalary = resultSet.getDouble("BasicSalary");
				double overtimePay = resultSet.getDouble("OvertimePay");
				double deductions = resultSet.getDouble("Deductions");
				double netSalary = resultSet.getDouble("NetSalary");
				Payroll payroll = new Payroll(payrollID, employeeID, payPeriodStartDate, payPeriodEndDate, basicSalary,
						overtimePay, deductions, netSalary);
				empPayrollList.add(payroll);
			
			}
			if (empPayrollList.isEmpty()) {
			     throw new PayrollGenerationException("Payroll Data is Empty for employee id : " + employeeId);
			}
			return empPayrollList;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (PayrollGenerationException e) {

			e.printStackTrace();
		}

		return null;
	}
    /**
     * Retrieves all payrolls within the specified period.
     *
     * @param startDate The start date of the period.
     * @param endDate   The end date of the period.
     * @return A List containing all payrolls within the specified period.
     */
	@Override
	public List<Payroll> getPayrollsForPeriod(Date startDate, Date endDate) {

		Connection con = DBConnUtil.getConnection();
		try {
			PreparedStatement pstmt = con
					.prepareStatement("SELECT * FROM Payroll WHERE PayPeriodStartDate BETWEEN ? AND ?");

			pstmt.setObject(1, startDate);
			pstmt.setObject(2, endDate);
			ResultSet resultSet = pstmt.executeQuery();

			List<Payroll> empPayrolllist = new ArrayList<>();
			while (resultSet.next()) {
				int payrollID = resultSet.getInt("PayrollID");
				int employeeID = resultSet.getInt("EmployeeID");
				Date payPeriodStartDate = resultSet.getDate("PayPeriodStartDate");
				Date payPeriodEndDate = resultSet.getDate("PayPeriodEndDate");
				double basicSalary = resultSet.getDouble("BasicSalary");
				double overtimePay = resultSet.getDouble("OvertimePay");
				double deductions = resultSet.getDouble("Deductions");
				double netSalary = resultSet.getDouble("NetSalary");
				Payroll payroll = new Payroll(payrollID, employeeID, payPeriodStartDate, payPeriodEndDate, basicSalary,
						overtimePay, deductions, netSalary);
				empPayrolllist.add(payroll);
			}
			if (empPayrolllist.isEmpty()) {
				 throw new PayrollGenerationException("No payroll Data found for these days : ");
			}
			return empPayrolllist;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (PayrollGenerationException e) {
						e.printStackTrace();
		}

		return null;
	}




}
