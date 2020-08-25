package com.openlabs.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Configuration
public class AppConfig {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
				.setDefaultPropertyInclusion(Include.NON_NULL)
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				;
		return mapper;
	}
	
}
