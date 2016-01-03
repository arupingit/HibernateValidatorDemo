package com.arupingit;

import org.springframework.stereotype.Component;

import com.arupingit.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.Version;

@Component
public class CustomObjectMapper extends ObjectMapper {
 
	private static final long serialVersionUID = 1L;

	public CustomObjectMapper() {
	      SimpleModule module = new SimpleModule("HTML XSS Serializer", new Version(1, 0, 0, "FINAL",null,null));
	      module.addSerializer(new JsonHtmlXssSerializer(Employee.class));
	      this.registerModule(module);
	   }
}