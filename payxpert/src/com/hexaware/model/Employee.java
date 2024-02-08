package com.hexaware.model;

import java.time.LocalDate;
import java.time.Period;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
private int employeeID ;
private String firstName;
private String lastName;
private Date dateOfBirth;
private String gender;
private String email;
private String phoneNumber;
private String address;
private String position;
private Date joiningDate;
private Date terminationDate;

public void calculateAge() {
	
	if(dateOfBirth !=null) {
		  LocalDate birthDate = dateOfBirth.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
          LocalDate currentDate = LocalDate.now();

          Period age = Period.between(birthDate, currentDate);
          int years = age.getYears();
          int months = age.getMonths();
          int days = age.getDays();

          System.out.println("Age: " + years + " years, " + months + " months, " + days + " days");
      } else {
          System.out.println("Date of birth is not set.");
      }
	}
	

public Employee() {
	
}
public Employee(int employeeID, String firstName, String lastName, Date dateOfBirth, String gender, String email,
		String phoneNumber, String address, String position, Date joiningDate, Date terminationDate) {
	super();
	this.employeeID = employeeID;
	this.firstName = firstName;
	this.lastName = lastName;
	this.dateOfBirth = dateOfBirth;
	this.gender = gender;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.address = address;
	this.position = position;
	this.joiningDate = joiningDate;
	this.terminationDate = terminationDate;
}

public Employee(int employeeID, String firstName, String lastName, String dateOfBirth, String gender, String email,
        String phoneNumber, String address, String position, String joiningDate, String terminationDate) {
this.employeeID = employeeID;
this.firstName = firstName;
this.lastName = lastName;

try {
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
this.dateOfBirth = dateFormat.parse(dateOfBirth);
this.joiningDate = dateFormat.parse(joiningDate);

if (!"null".equals(terminationDate)) {
    this.terminationDate = dateFormat.parse(terminationDate);
}else {
	 this.terminationDate = null;
}
} catch (ParseException e) {
e.printStackTrace(); // Handle the exception appropriately
}

this.gender = gender;
this.email = email;
this.phoneNumber = phoneNumber;
this.address = address;
this.position = position;
}

public int getEmployeeID() {
	return employeeID;
}
public void setEmployeeID(int employeeID) {
	this.employeeID = employeeID;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public Date getDateOfBrith() {
	return  dateOfBirth;
}
public void setDateOfBrith(Date dateOfBrith) {
	this.dateOfBirth = dateOfBrith;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPosition() {
	return position;
}
public void setPosition(String position) {
	this.position = position;
}
public  Date getJoiningDate() {
	return joiningDate;
}
public void setJoiningDate(Date joiningDate) {
	this.joiningDate = joiningDate;
}
public Date getTerminationDate() {
	return terminationDate;
}
public void setTerminationDate(Date terminationDate) {
	this.terminationDate = terminationDate;
}
@Override
public String toString() {
	return "\nEmployee [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName
			+ ", dateOfBrith=" + dateOfBirth + ", gender=" + gender + ", email=" + email + ", phoneNumber="
			+ phoneNumber + ", address=" + address + ", position=" + position + ", joiningDate=" + joiningDate
			+ ", terminationDate=" + terminationDate + "]\n";
}


}
