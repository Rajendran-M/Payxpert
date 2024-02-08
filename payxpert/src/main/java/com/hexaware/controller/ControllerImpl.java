package com.hexaware.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
import java.util.Scanner;

import com.hexaware.dao.*;
import com.hexaware.model.Employee;

public class ControllerImpl implements IControllerservice {
	Scanner sc = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

	public void mainMenu() {

		System.out.println(" ----PayXpert Management System----\n");
		System.out.println(" 1.Employee Service\n 2.Payroll Service\n 3.Financial Record Service\n "
				+ "4.Tax service\n 5.Exit \n\n Please Enter a number to select the option.");

		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			employeeService();
			break;
		case 2:
			payrollService();
			break;
		case 3:
			financialRecordService();
			break;
		case 4:
			taxService();
			break;
		case 5:
			System.out.println("Bye for now..");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Option");
			mainMenu();

		}
	}

	private void employeeService() {
		IEmployeeService employeeService = new EmployeeService();
		System.out.println("------Employee Services-----");
		System.out.println("1.Add New Employee\n2.Get All Employee Details\n3.Get Employee By ID\n"
				+ "4.Remove Employee\n5.Update a Employee Details\n6.close");
		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			// Taking user input for employee details
			System.out.print("Enter Employee ID: ");
			int employeeID = sc.nextInt();

			System.out.print("Enter First Name: ");
			String firstName = sc.next();

			System.out.print("Enter Last Name: ");
			String lastName = sc.next();

			System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
			Date dateOfBirth = parseDate(sc.next());

			System.out.print("Enter Gender: ");
			String gender = sc.next();
            
			System.out.print("Enter Email: ");
			String email = sc.next();
           while(!email.contains("@")) {
        	   System.out.println(ANSI_RED+"Invalid 'Email ID'.Please enter valid email!(example@domain.com) "+ANSI_RESET);
        	   System.out.print("Enter Email: ");
        	   email = sc.next();
           }
			System.out.print("Enter Phone Number: ");
			String phoneNumber = sc.next();

			System.out.print("Enter Address: ");
			String address = sc.next();

			System.out.print("Enter Position: ");
			String position = sc.next();

			System.out.print("Enter Joining Date (YYYY-MM-DD): ");
			Date joiningDate = parseDate(sc.next());

			System.out.print("Enter Termination Date (or type null) (YYYY-MM-DD): ");
			Date terminationDate = parseDate(sc.next());
			 while (terminationDate != null && terminationDate.before(joiningDate)) {
                 System.out.println(ANSI_RED+"Termination date cannot be before joining date. Please enter a valid termination date or null."+ANSI_RESET);
             
              terminationDate = parseDate(sc.next());
			 }
			 
			Employee employee = new Employee(employeeID, firstName, lastName, dateOfBirth, gender, email, phoneNumber,
					address, position, joiningDate, terminationDate);
			employeeService.addEmployee(employee);
			break;

		case 2:
			System.out.println(employeeService.getAllEmployees());
			break;
		case 3:
			System.out.println("Enter Employee Id ");
			System.out.println(employeeService.getEmployeeById(sc.nextInt()));
			break;
		case 4:
			System.out.println("Enter Employee Id ");
			employeeService.removeEmployee(sc.nextInt());
			System.out.println();
			break;
		case 5:
			String ch1 = "";
			System.out.print("Enter Employee ID : ");
			int empId = sc.nextInt();
			do {
				System.out.println("Enter which data to update");
				System.out.println("First Name :      1");
				System.out.println("Last Name :       2");
				System.out.println("Date of Birth :   3");
				System.out.println("Gender :          4");
				System.out.println("Email :           5");
				System.out.println("Phone Number :    6");
				System.out.println("Address :         7");
				System.out.println("Position :        8");
				System.out.println("Joinning Date :   9");
				System.out.println("Termination Date:10");
				System.out.println("Go back :        11\npress 12 to exit");
				System.out.print("Enter choice : ");
				int ch = sc.nextInt();
				sc.nextLine();
				Employee obj = new EmployeeService().getEmployeeById(empId);
				switch (ch) {
				case 1:
					System.out.print("Enter Frist Name : ");
					obj.setFirstName(sc.nextLine());
					new EmployeeService().updateEmployee(obj);
					break;
				case 2:
					System.out.print("Enter Last Name : ");
					obj.setLastName(sc.nextLine());
					new EmployeeService().updateEmployee(obj);
					break;
				case 3:
					System.out.print("Enter Date Of Birth : ");
					obj.setDateOfBrith(parseDate(sc.nextLine()));
					new EmployeeService().updateEmployee(obj);
					break;
				case 4:
					System.out.print("Enter Gender : ");
					obj.setGender(sc.next());
					new EmployeeService().updateEmployee(obj);
					break;
				case 5:
					System.out.print("Enter Email : ");
					obj.setEmail(sc.next());
					new EmployeeService().updateEmployee(obj);
					break;
				case 6:
					System.out.print("Enter Phone Number : ");
					obj.setPhoneNumber(sc.nextLine());
					new EmployeeService().updateEmployee(obj);
					break;
				case 7:
					System.out.print("Enter Address : ");
					obj.setAddress(sc.nextLine());
					new EmployeeService().updateEmployee(obj);
					break;
				case 8:
					System.out.println("Enter Position : ");
					obj.setPosition(sc.nextLine());
					new EmployeeService().updateEmployee(obj);
					break;
				case 9:
					System.out.println("Enter Joinning Date : ");
					obj.setJoiningDate(parseDate(sc.nextLine()));
					new EmployeeService().updateEmployee(obj);
					break;
				case 10:
					System.out.println("Enter termination Date : ");
					obj.setTerminationDate(parseDate(sc.nextLine()));
					new EmployeeService().updateEmployee(obj);
					break;
				case 11:
					employeeService();
					break;
				default:
					System.out.println("Exiting");
					break;
				}
				System.out.println(" Want to update same employee again (y/n)");
				ch1 = sc.next().toLowerCase();
			} while (ch1.equals("y"));
			break;
		case 6:
			mainMenu();
			break;
		default:
			System.out.println("Invalid option");
			break;

		}

	}

	private void payrollService() {

		IPayrollService payrollService = new PayrollService();
		System.out.println("--- Payroll ---");
		System.out.println("1. Generate Payroll");
		System.out.println("2. Get Payroll By Id");
		System.out.println("3. Get Payrolls For Employee");
		System.out.println("4. Get Payrolls For Period");
		System.out.println("5. Back");
		System.out.print("Select option : ");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("To genarete payroll\nEnter EmployeeId");
			int employeeid = sc.nextInt();
			System.out.println("Enter Payroll Start Date yyyy-mm-dd");
			Date sdate = parseDate(sc.next());
			System.out.println("Enter Payroll End Date  yyyy-mm-dd");
			Date edate = parseDate(sc.next());
			payrollService.generatePayroll(employeeid, sdate, edate);
			break;
		case 2:
			System.out.println("Enter payroll Id");
			payrollService.getPayrollById(sc.nextInt());
			break;
		case 3:
			System.out.println("Enter Employee Id");
			System.out.println(payrollService.getPayrollsForEmployee(sc.nextInt()));
			break;
		case 4:
			System.out.println("Enter Start date and end date 'yyyy-mm-dd'");
			Date stdate = parseDate(sc.next());
			Date eddate = parseDate(sc.next());
			System.out.println(payrollService.getPayrollsForPeriod(stdate, eddate));
			break;
		case 5:
			mainMenu();
			break;
		default:
			System.out.println("Invalid option");
			break;
		}

	}

	private void financialRecordService() {
		IFinancialRecordService financialService = new FinancialRecordService();
		System.out.println("--- FinancialRecordService ---");
		System.out.println("1. AddFinancialRecord");
		System.out.println("2. GetFinancialRecordById");
		System.out.println("3. GetFinancialRecordsForDate");
		System.out.println("4. GetFinancialRecordsForEmployee");
		System.out.println("5. Back");
		System.out.print("Select option : ");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println();
			// Taking user input for FinancialRecord attributes

			System.out.print("Enter Employee ID: ");
			int employeeID = sc.nextInt();

			System.out.print("Enter Description: ");
			sc.nextLine();
			String description = sc.nextLine();

			System.out.print("Enter Amount: ");
			double amount = sc.nextDouble();

			System.out.print("Enter Record Type: ");
			sc.nextLine();
			String recordType = sc.nextLine();

			financialService.addFinancialRecord(employeeID, description, amount, recordType);
			break;
		case 2:
			System.out.println("Enter Financial record ID : ");
			System.out.println(financialService.getFinancialRecordById(sc.nextInt()));
			break;
		case 3:

			System.out.println("Enter Date (YYYY-MM-DD): ");
			Date date = parseDate(sc.next());

			System.out.println(financialService.getFinancialRecordsForDate(date));
			break;
		case 4:
			System.out.println("Enter Employee ID : ");
			System.out.println(financialService.getFinancialRecordsForEmployee(sc.nextInt()));
		case 5:
			mainMenu();
			break;

		default:
			System.out.println("wrong choice try again");
			break;
		}

	}

	private void taxService() {
		ITaxService taxService = new TaxService();
		System.out.println();
		System.out.println("--- Tax ---");
		System.out.println("1. Calculate Tax");
		System.out.println("2. Get Tax By Id");
		System.out.println("3. Get Taxes For Employee");
		System.out.println("4. Get Taxes For Year");
		System.out.println("5. Exit");
		System.out.print("Select option : ");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.print("Enter employee ID : ");
			int empId = sc.nextInt();
			System.out.println("Enter Year : ");
			int year = sc.nextInt();
			Year taxYear = Year.of(year);
			System.out.println("Tax : " + taxService.calculateTax(empId, taxYear));
			break;
		case 2:
			System.out.print("Enter Tax ID : ");
			System.out.println(taxService.getTaxById(sc.nextInt()));
			break;
		case 3:
			System.out.println("Enter Employee Id:");
			System.out.println(taxService.getTaxesForEmployee(sc.nextInt()));
			break;
		case 4:
			System.out.println("Enter the Tax Year");
			System.out.println(taxService.getTaxesForYear(Year.of(sc.nextInt())));
			break;
		case 5:
			System.exit(0);
			break;
		default:
			System.out.println("Invalid option");
			break;
		}

	}

	private Date parseDate(String date) {
		try {
			if (!"null".equals(date)) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				return dateFormat.parse(date);
			}
		} catch (ParseException e) {

			e.printStackTrace();
			System.err.println("Error parsing date. Using current date instead.");
		}
		return null;

	}

}
