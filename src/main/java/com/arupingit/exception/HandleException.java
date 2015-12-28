package com.arupingit.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.arupingit.util.EmployeeConstants;

public class HandleException {
	
	public static void handleModelBindingException(BindingResult result){
		if(result.hasErrors()){
	        for(ObjectError objectError : result.getAllErrors()){
	            System.out.println("Error :: " +objectError);
	        }
	        throw new EmployeeException(EmployeeConstants.INVALID_INPUT);
		}
	}
}
