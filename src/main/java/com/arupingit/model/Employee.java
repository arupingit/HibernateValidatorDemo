package com.arupingit.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Employee implements Comparable{

	@Min(value=0,message="Employee Id cannot be less than 0")
	private int empId;
	
	@NotNull(message="Employee name cannot be Empty ")
	@Size(min=1,max=20,message="Employee name should be less than 20 characters")
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", empId=" + empId + "]";
	}
	
	@Override
	public int compareTo(Object obj) {
		Employee emp = (Employee) obj;
		if(this.empId == emp.getEmpId()){
			return 0;
		}
		else if(this.empId > emp.getEmpId()){
			return 1;
		}
		else{
			return -1;
		}		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (empId != other.empId)
			return false;
		return true;
	}
	
}
