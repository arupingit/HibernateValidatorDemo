package com.arupingit.model;

public class EmployeeResponse {

	private Employee[] employeeList;
	private String status;
	private String errorDetails;
	
	public Employee[] getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(Employee[] employeeList) {
		this.employeeList = employeeList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	
}
