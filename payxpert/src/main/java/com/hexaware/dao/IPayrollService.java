package com.hexaware.dao;

import java.util.Date;
import java.util.List;

import com.hexaware.model.Payroll;
/**
 * Interface for Payroll Service operations.
 * Provides methods to generate payroll, retrieve payroll details by ID, and get payroll records for employees and periods.
 */
public interface IPayrollService {
  void generatePayroll(int employeeID,Date startDate,Date endDate);
  Payroll getPayrollById(int payrollId);
  List<Payroll> getPayrollsForEmployee(int employeeId);
  List<Payroll> getPayrollsForPeriod(Date startDate, Date endDate);
  
}
