package br.edu.ifpb.upcensus.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class ObjectMapperConfiguration {
	
	@Bean
	public ObjectMapper setupObjectMapper() {
		return new ObjectMapper()
        	.registerModule(new JavaTimeModule())
        	.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
}
