package br.edu.ifpb.upcensus.infrastructure.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;

public class ServerUtils {
	private ServerUtils() {}
	
	public static String getUri(HttpServletRequest request) {
		return request.getRequestURI();
	}
	
	public static String getUri(WebRequest request) {
		return getUri(((ServletWebRequest)request).getRequest());
	}
	
	public static String findClassName(Class<?> classObj) {
		DomainDescriptor descriptor = AnnotationUtils.findAnnotation(classObj, DomainDescriptor.class);
		
		return ObjectUtils.nonNull(descriptor) ? descriptor.name() : classObj.getSimpleName();
	}
}
