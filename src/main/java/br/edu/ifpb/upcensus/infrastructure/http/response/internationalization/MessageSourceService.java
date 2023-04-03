package br.edu.ifpb.upcensus.infrastructure.http.response.internationalization;

import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;

public interface MessageSourceService {
	
	String getMessage(String key, Object... params);
	@Nullable
    default String getMessage(String key) {
    	return getMessage(key, new Object[] {});
    }

     String getMessage(FieldError fieldError);

}
