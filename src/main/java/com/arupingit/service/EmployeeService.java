package com.arupingit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.arupingit.exception.EmployeeException;
import com.arupingit.model.Employee;
import com.arupingit.util.EmployeeConstants;

@Repository
public class EmployeeService {
	private Map<Integer,Employee> localEmployeeDb;
	
	EmployeeService(){
		localEmployeeDb = new HashMap<>();
		Employee emp = new Employee();
		emp.setEmpId(0);
		emp.setName("demo");
		localEmployeeDb.put(0,emp);
	}
	
	public Employee getEmployee(int empid){		
		Employee employee = localEmployeeDb.get(empid);
		if(employee!=null){
			return employee;
		}
		throw new EmployeeException(EmployeeConstants.EMPLOYEE_NOT_FOUND);
	}
	
	public List<Employee> getAllEmployee(){
		return new ArrayList<>(localEmployeeDb.values());
	}
	
	public boolean saveEmployee(Employee employee){
		if(localEmployeeDb.get(employee.getEmpId())!=null){
			throw new EmployeeException(EmployeeConstants.EMPLOYEE_ALREADY_PRESENT);
		}
		localEmployeeDb.put(employee.getEmpId(),employee);
		return true;
	}
	
	public boolean updateEmployee(Employee employee){
		getEmployee(employee.getEmpId());
		if(localEmployeeDb.put(employee.getEmpId(),employee)!= null){
			return true;
		}
		return false;
	}
	
	public boolean deleteEmployee(int empId){
		if(localEmployeeDb.remove(empId) != null){
			return true;
		}
		throw new EmployeeException(EmployeeConstants.EMPLOYEE_NOT_FOUND);		
	}
}
