package br.edu.ifpb.upcensus.infrastructure.http.response.internationalization;

import org.springframework.validation.FieldError;

public interface MessageSourceService {
	
	String getMessage(String key, Object... params);
    default String getMessage(String key) {
    	return getMessage(key, null);
    }

     String getMessage(FieldError fieldError);

}
