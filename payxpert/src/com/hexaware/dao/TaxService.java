package com.hexaware.dao;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.hexaware.model.Tax;
import com.hexaware.util.DBConnUtil;
import com.hexaware.exception.*;

public class TaxService implements ITaxService {

	@Override
	public double calculateTax(int employeeId, Year taxYear) {
		try {
			Connection con = DBConnUtil.getConnection();

			PreparedStatement pstmt1 = con.prepareStatement("SELECT 1 FROM Employee WHERE EmployeeID = ?");
			pstmt1.setInt(1, employeeId);
			ResultSet rs1 = pstmt1.executeQuery();
			if (!rs1.next()) { // check for employee
				throw (new EmployeeNotFoundException("No employee with id:" + employeeId + " exists"));
			}

			double taxableIncome = 0;

			PreparedStatement pstmt2 = con.prepareStatement(
					"SELECT SUM(NetSalary)  FROM payroll WHERE EmployeeID = ? AND YEAR(PayPeriodEndDate) = ?");
			pstmt2.setInt(1, employeeId);
			pstmt2.setInt(2, taxYear.getValue());
			ResultSet rs2 = pstmt2.executeQuery();
			if (rs2.next()) {

				taxableIncome = rs2.getDouble(1);

				PreparedStatement pstmt3 = con.prepareStatement(
						"INSERT INTO Tax ( EmployeeID, TaxYear, TaxableIncome, TaxAmount) VALUES (?, ?, ?, ?)");

				pstmt3.setInt(1, employeeId);
				pstmt3.setInt(2, taxYear.getValue());
				pstmt3.setDouble(3, taxableIncome);
				Double taxamount = taxableIncome * 0.84 / 12;
				pstmt3.setDouble(4, taxamount);

				int affectedRows = pstmt3.executeUpdate();
				if (affectedRows > 0) {
					System.out.println("Tax record added successfully.");
				} else {
					throw new SQLException("Failed to insert tax record.");
				}

				return taxableIncome;
			} else {//
				throw (new TaxCalculationException(
						"No record found to calculate tax for eid:" + employeeId + " for taxyear" + taxYear));
			}
		} catch (EmployeeNotFoundException enfe) {
			enfe.printStackTrace();
		} catch (TaxCalculationException tce) {
			tce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	return 0;}

	@Override
	public Tax getTaxById(int taxId) {
		try {
			Connection con = DBConnUtil.getConnection();

			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Tax WHERE TaxID = ?");
			pstmt.setInt(1, taxId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int taxid = rs.getInt("TaxID");
				int empid = rs.getInt("EmployeeID");
				Year year = Year.of(rs.getInt("TaxYear"));
				double taxableincome = rs.getDouble("TaxableIncome");
				double taxamount = rs.getDouble("TaxAmount");
				Tax tx = new Tax(taxid, empid, year, taxableincome, taxamount);
				return tx;
			} else {
				throw new TaxCalculationException("No record found for taxid:" + taxId);
			}
		} catch (TaxCalculationException tce) {
			tce.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Tax> getTaxesForEmployee(int employeeId) {
		Connection con = DBConnUtil.getConnection();
		List<Tax> taxList = new ArrayList<>();
		Tax tax;
		try {
			PreparedStatement psmt = con.prepareStatement("SELECT * FROM TAX WHERE EMPLOYEEID = ?");
			psmt.setInt(1, employeeId);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				int taxID = rs.getInt(1);
				int employeeID = rs.getInt(2);
				Year taxYear = Year.of(rs.getInt(3));
				double taxableIncome = rs.getDouble(4);
				double taxAmount = rs.getDouble(5);
				tax = new Tax(taxID, employeeID, taxYear, taxableIncome, taxAmount);
				taxList.add(tax);

			}
			if (taxList.isEmpty()) {
				throw new TaxCalculationException("No record found ");
			}
			return taxList;

		} catch (TaxCalculationException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Tax> getTaxesForYear(Year taxYear) {
		try {
			Connection con = DBConnUtil.getConnection();

			PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM Tax WHERE TaxYear = ?");
			pstmt1.setInt(1, taxYear.getValue());
			ResultSet rs1 = pstmt1.executeQuery();
			ArrayList<Tax> txsforty = new ArrayList<Tax>();
			while (rs1.next()) {
				int taxid = rs1.getInt("TaxID");
				int empid = rs1.getInt("EmployeeID");
				Year year = Year.of(rs1.getInt("TaxYear"));
				double taxableincome = rs1.getDouble("TaxableIncome");
				double taxamount = rs1.getDouble("TaxAmount");
				Tax tx = new Tax(taxid, empid, year, taxableincome, taxamount);
				txsforty.add(tx);
			}
			if (txsforty.isEmpty()) {
				throw (new TaxCalculationException("No record found for taxyear:" + taxYear));
			}
			return txsforty;
		} catch (TaxCalculationException tce) {
			tce.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
