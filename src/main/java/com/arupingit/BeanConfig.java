package com.arupingit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.arupingit.filter.CustomFilter;

import javax.servlet.Filter;

@Configuration
public class BeanConfig {

	@Autowired
	CustomObjectMapper customObjectMapper;
	
	@Bean
	public Filter registerFilterBean(){
		return new CustomFilter();
	}
	
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.configure(customObjectMapper);
		return builder;
	}
}
