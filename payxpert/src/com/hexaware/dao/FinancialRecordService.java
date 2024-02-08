package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.hexaware.exception.EmployeeNotFoundException;
import com.hexaware.exception.FinancialRecordException;
import com.hexaware.model.FinancialRecord;
import com.hexaware.util.DBConnUtil;

public class FinancialRecordService implements IFinancialRecordService{

	@Override
	public void addFinancialRecord(int employeeId, String description, double amount, String recordType) {
		try {
			Connection con = DBConnUtil.getConnection();
	
			PreparedStatement pstmt1 =con.prepareStatement("SELECT 1 FROM Employee WHERE EmployeeID = ?");
			pstmt1.setInt(1, employeeId);
			ResultSet rs1 = pstmt1.executeQuery(); 
		    if(rs1.next()) { 
		    	// check for employee
			    //if employee present then 
			
				PreparedStatement pstmt2 =con.prepareStatement("INSERT INTO FinancialRecord (EmployeeID, RecordDate, Description, Amount, RecordType) " +
						"VALUES (?, CURRENT_DATE, ?, ?, ?)");
				pstmt2.setInt(1, employeeId);
				pstmt2.setString(2, description);
				pstmt2.setDouble(3, amount);
				pstmt2.setString(4, recordType);
				pstmt2.executeUpdate();
				if(pstmt2.executeUpdate()>0) {
					System.out.println("Financial record  added ..");
				}
				else {
					throw new FinancialRecordException();
					
				}
		    }
		    else {//if employee not present
		    	throw(new EmployeeNotFoundException("No employee with id:"+employeeId+" exists"));
		    	}
		}catch (EmployeeNotFoundException enfe) {
			enfe.printStackTrace();
		}catch(SQLException se) {
			se.printStackTrace();
		} catch (FinancialRecordException e) {
				e.printStackTrace();
		}
		
	}

	@Override
	public FinancialRecord getFinancialRecordById(int recordId) {
		Connection con = DBConnUtil.getConnection();
		
		
		try {
			
			PreparedStatement pstmt1 =con.prepareStatement( "SELECT * FROM FinancialRecord WHERE RecordID = ?") ;
			pstmt1.setInt(1, recordId);
	        ResultSet rs1 = pstmt1.executeQuery();
	        if (rs1.next()) {//check if recordId present or not
	        	//if present
	        	int recordid= rs1.getInt("RecordID");
	        	int employeeid = rs1.getInt("EmployeeID");
	        	Date recordDate = rs1.getDate("RecordDate");
	        	String description = rs1.getString("Description");
	        	Double amount = rs1.getDouble("Amount");
	        	String recordtype = rs1.getString("RecordType");    	
	        	FinancialRecord fr = new FinancialRecord(recordid, employeeid, recordDate, description, amount,recordtype);
	        	return fr;
	        }
	        else {//if not present
	        	throw(new FinancialRecordException("No such record with record id "+recordId+" found"));
	        }
      	}catch (FinancialRecordException fre) {
      		fre.printStackTrace();
      	}catch (SQLException e) {
            e.printStackTrace();
        }

		return null;
	}

	@Override
	public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
		try {
			Connection con = DBConnUtil.getConnection();
		
			PreparedStatement pstmt1 = con.prepareStatement("SELECT 1 FROM Employee WHERE EmployeeID = ?");
			pstmt1.setInt(1, employeeId);
			ResultSet rs1 = pstmt1.executeQuery(); 
		    if(!rs1.next()) { // check for employee
		    	throw(new EmployeeNotFoundException("employee with empid: "+employeeId+" doesnot exist"));
		    }
		    
		    //if present
			List<FinancialRecord> financialRecords = new ArrayList<FinancialRecord>();
	        
	        PreparedStatement pstmt2 = con.prepareStatement("SELECT * FROM financialrecord WHERE EmployeeID = ?");
	        pstmt2.setInt(1, employeeId);
	        ResultSet rs2 = pstmt2.executeQuery();
            while (rs2.next()) {
            	int recordid= rs2.getInt("RecordID");
	        	int employeeid = rs2.getInt("EmployeeID");
	        	Date recordDate = rs2.getDate("RecordDate");
	        	String description = rs2.getString("Description");
	        	Double amount = rs2.getDouble("Amount");
	        	String recordtype = rs2.getString("RecordType");    	
	        	FinancialRecord fr = new FinancialRecord(recordid, employeeid, recordDate, description, amount,recordtype);
	        	financialRecords.add(fr);
            }
	        if(financialRecords.isEmpty()) {
	        	throw (new FinancialRecordException("No record found for employeeId: "+employeeId));
	        }   
	        return financialRecords;
		}catch(EmployeeNotFoundException enfe){
			enfe.printStackTrace();
		}catch(FinancialRecordException fre){
			fre.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}

		
		return null;
	}

	@Override
	public List<FinancialRecord> getFinancialRecordsForDate(Date recordDate) {
      List<FinancialRecord> financialRecords = new ArrayList<>();
	 
	 try {
		 Connection con = DBConnUtil.getConnection();
		 
		 PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM FinancialRecord WHERE RecordDate = ?") ;
		
		 pstmt1.setObject(1, recordDate);
		 ResultSet rs1 = pstmt1.executeQuery();
		 while (rs1.next()) {
			int recordid= rs1.getInt("RecordID");
			int employeeid = rs1.getInt("EmployeeID");
			Date recorddate = rs1.getDate("RecordDate");
			String description = rs1.getString("Description");
			Double amount = rs1.getDouble("Amount");
			String recordtype = rs1.getString("RecordType");    	
				FinancialRecord fr = new FinancialRecord(recordid, employeeid, recorddate, description,amount,recordtype);
				financialRecords.add(fr);
			}
		 if(financialRecords.isEmpty()) {
			 throw (new FinancialRecordException("Financial Records on RecordDate: "+recordDate+" doesnot exist"));
	        }   
	        return financialRecords;
	 }catch(FinancialRecordException enfe){
		 enfe.printStackTrace();
	 } catch (SQLException e) {
		 e.printStackTrace();
	 }

		return null;
	}



}
