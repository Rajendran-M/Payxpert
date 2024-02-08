show databases;
create database payxpert;
use payxpert;
/*--	1. Employee Table:
--	• EmployeeID (Primary Key): Unique identifier for each employee.
• FirstName: First name of the employee.
• LastName: Last name of the employee.
• DateOfBirth: Date of birth of the employee.
• Gender: Gender of the employee.
• Email: Email address of the employee.
• PhoneNumber: Phone number of the employee.
• Address: Residential address of the employee.
• Position: Job title or position of the employee.
• JoiningDate: Date when the employee joined the company.
• TerminationDate: Date when the employee left the company (nullable).*/
create table Employee
(EmployeeID INT PRIMARY KEY,
FirstName VARCHAR(255),
LastName varchar(255),
DateOfBirth date,
Gender varchar(20),
Email varchar(255) UNIQUE,
phoneNumber varchar(20),
Address TEXT,
position varchar(250),
JoiningDate Date NOT NULL,
TerminationDate date );
-- Sample data for Employee table
INSERT INTO Employee (EmployeeID, FirstName, LastName, DateOfBirth, Gender, Email, phoneNumber, Address, position, JoiningDate, TerminationDate)
VALUES
(101, 'John', 'Doe', '1990-05-15', 'Male', 'john.doe@example.com', '1234567890', '123 Main St, Cityville', 'Manager', '2020-01-01', NULL),
(102, 'Jane', 'Smith', '1985-08-22', 'Female', 'jane.smith@example.com', '9876543210', '456 Oak St, Townsville', 'Developer', '2019-03-15', NULL),
(103, 'Alice', 'Johnson', '1992-11-10', 'Female', 'alice.johnson@example.com', '5551234567', '789 Pine St, Villagetown', 'HR Specialist', '2021-07-10', NULL),
(104,'Mikey','Joe','2000-12-01','Male','mikey.joe@mail.com','7894561231','385 Main St,Cityville','SDE','2024-01-01',NULL);

/*• PayrollID (Primary Key): Unique identifier for each payroll record.
• EmployeeID (Foreign Key): Foreign key referencing the Employee table.
• PayPeriodStartDate: Start date of the pay period.
• PayPeriodEndDate: End date of the pay period.
• BasicSalary: Base salary for the pay period.
• OvertimePay: Additional pay for overtime hours.
• Deductions: Total deductions for the pay period.
• NetSalary: Net salary after deductions.*/

CREATE TABLE Payroll
(PayrollId INT PRIMARY KEY,
 EmployeeId INT,
PayPeriodStartDate Date,
PayPeriodEndDate Date,
BasicSalary decimal,
OvertimePay decimal,
Deductions decimal,
NetSalary decimal,
FOREIGN KEY(EmployeeId) REFERENCES Employee(EmployeeId));
-- SAMPLE DATA
INSERT INTO Payroll (PayrollId, EmployeeId, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary)
VALUES
(201, 101, '2024-01-01', '2024-01-15', 5000.00, 200.00, 300.00, 4900.00),
(202, 102, '2024-01-01', '2024-01-15', 4500.00, 150.00, 250.00, 4350.00),
(203, 103, '2024-01-01', '2024-01-15', 6000.00, 250.00, 300.00, 5950.00);


/*3. Tax Table:
• TaxID (Primary Key): Unique identifier for each tax record.
• EmployeeID (Foreign Key): Foreign key referencing the Employee table.
• TaxYear: Year to which the tax information applies.
• TaxableIncome: Income subject to taxation.
• TaxAmount: Amount of tax to be paid. */
Create Table Tax
(TaxId INT PRIMARY KEY,
EmployeeId INT,
TaxYear INT,
TaxableIncome Decimal,
TaxAmount Decimal,
FOREIGN KEY (EmployeeId) REFERENCES Employee(EmployeeId));

INSERT INTO Tax (TaxId, EmployeeId, TaxYear, TaxableIncome, TaxAmount)
VALUES
(1,101, 2024, 5000.00, 200.00),
(2,102, 2024, 4500.00, 150.00),
(3,103, 2024, 6000.00, 300.00);

/*4. FinancialRecord Table:
• RecordID (Primary Key): Unique identifier for each financial record.
• EmployeeID (Foreign Key): Foreign key referencing the Employee table.
• RecordDate: Date of the financial record.
• Description: Description or category of the financial record.
• Amount: Monetary amount of the record (income, expense, etc.).
• RecordType: Type of financial record (income, expense, tax payment, etc.).*/
Create Table FinancialRecord 
(RecordID INT PRIMARY KEY,
EmployeeID INT ,
RecordDate Date,
Description Varchar(255),
Amount decimal,
RecordType varchar(50),
FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeId) );

INSERT INTO FinancialRecord (RecordID, EmployeeID, RecordDate, Description, Amount, RecordType)
VALUES
(1,101, '2024-01-05', 'Bonus Payment', 500.00, 'Income'),
(2,102, '2024-01-08', 'Equipment Purchase', 300.00, 'Expense'),
(3,103, '2024-01-12', 'Tax Payment', 300.00, 'Tax ');
Show Tables;
select * from payroll;
-- ---trigger  for netsalary ---
ALTER table payroll
modify column payrollid int Auto_increment  ;
delimiter //
create trigger netsalary
BEFORE INSERT ON payroll
FOR EACH ROW 
BEGIN
 set new.netsalary = new.basicSalary + new.overTimePay -new.deductions;
END //
delimiter ;



