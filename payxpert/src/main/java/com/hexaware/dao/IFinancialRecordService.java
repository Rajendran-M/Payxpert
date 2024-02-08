package com.hexaware.dao;

import com.hexaware.model.FinancialRecord;
import java.util.Date;
import java.util.List;
/**
 * Interface for Financial Record Service operations.
 * Provides methods to add financial records, retrieve financial records by ID,
 * and get financial records for employees and specific dates.
 */
public interface IFinancialRecordService {
	
	void addFinancialRecord(int employeeId,String description,double amount, String recordType);
	FinancialRecord getFinancialRecordById(int recordId);
	List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
	List<FinancialRecord> getFinancialRecordsForDate(Date recordDate);	
	
	

}
