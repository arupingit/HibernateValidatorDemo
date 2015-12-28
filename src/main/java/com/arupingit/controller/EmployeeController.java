package com.arupingit.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arupingit.exception.EmployeeException;
import com.arupingit.exception.HandleException;
import com.arupingit.model.Employee;
import com.arupingit.model.EmployeeResponse;
import com.arupingit.service.EmployeeService;
import com.arupingit.util.EmployeeConstants;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;

	@RequestMapping(value = "/", method=RequestMethod.GET, produces="application/json")
	public EmployeeResponse getEmployees(){
		List<Employee> employees = empService.getAllEmployee();  
		EmployeeResponse response = new EmployeeResponse();
		response.setEmployeeList(employees.toArray(new Employee[employees.size()]));
		response.setStatus(EmployeeConstants.SUCCESS);
		return response;
	}
	
	@RequestMapping(value = {"/{id}"}, method=RequestMethod.GET, produces="application/json")
	public EmployeeResponse getEmployee(@PathVariable int id){
		EmployeeResponse response = new EmployeeResponse();
		Employee[] employees = new Employee[1];
		employees[0] = empService.getEmployee(id);
		response.setEmployeeList(employees);
		response.setStatus(EmployeeConstants.SUCCESS);
		return response;
	}
		
	@RequestMapping(value = "/", method=RequestMethod.POST,
			consumes="application/json", produces="application/json" ,
			headers = "content-type=application/x-www-form-urlencoded")
	public EmployeeResponse setEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult){
		HandleException.handleModelBindingException(bindingResult);
		EmployeeResponse response = new EmployeeResponse();		
		if(empService.saveEmployee(employee)){
			response.setStatus(EmployeeConstants.SUCCESS);
		}
		else{
			response.setStatus(EmployeeConstants.FAILURE);
		}
		return response;
	}
	
	@RequestMapping(value = "/", method=RequestMethod.PUT,
			consumes="application/json", produces="application/json" ,
			headers = "content-type=application/x-www-form-urlencoded")
	public EmployeeResponse updateEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult){
		HandleException.handleModelBindingException(bindingResult);
		EmployeeResponse response = new EmployeeResponse();		
		if(empService.updateEmployee(employee)){
			response.setStatus(EmployeeConstants.SUCCESS);
		}
		else{
			response.setStatus(EmployeeConstants.FAILURE);
		}
		return response;
	}
	
	@RequestMapping(value = {"/{id}"}, method=RequestMethod.DELETE, produces="application/json")
	public EmployeeResponse deleteEmployee(@PathVariable int id, BindingResult bindingResult){
		HandleException.handleModelBindingException(bindingResult);
		EmployeeResponse response = new EmployeeResponse();		
		if(empService.deleteEmployee(id)){
			response.setStatus(EmployeeConstants.SUCCESS);
		}
		else{
			response.setStatus(EmployeeConstants.FAILURE);
		}
		return response;
	}
		
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmployeeException.class)
	public EmployeeResponse exceptionHandler(HttpServletRequest request, Exception exception){
		System.out.println("error in calling : "+request.getRequestURI());
		exception.printStackTrace();
		EmployeeResponse response = new EmployeeResponse();
		response.setErrorDetails(exception.getMessage());
		response.setStatus(EmployeeConstants.ERROR);
		return response;
	}
	
}
