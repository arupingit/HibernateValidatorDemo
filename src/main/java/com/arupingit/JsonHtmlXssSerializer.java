package com.arupingit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.owasp.esapi.Encoder;
import org.owasp.esapi.reference.DefaultEncoder;

import com.arupingit.model.Employee;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class JsonHtmlXssSerializer extends StdSerializer{

	
	protected JsonHtmlXssSerializer(Class<Employee> type) {
		super(type);
	}

	public void serialize(Object value, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
	      if (value != null) {
	    	  Employee employee = (Employee) value; 
	    	  employee.setName(encodeHtml(employee.getName()));
	          jsonGenerator.writeString(employee.toString());
	       }		
	}
	
	protected String encodeHtml(String html) {
		List<String> codecs = new ArrayList<>(); 
			codecs.add("HTMLEntityCodec");
	        codecs.add("PercentCodec");
	        codecs.add("JavaScriptCodec");
		 Encoder encoder = new DefaultEncoder(codecs);
		 return encoder.encodeForHTML(html);
	}
	
}
