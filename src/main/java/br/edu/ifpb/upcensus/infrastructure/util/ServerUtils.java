package br.edu.ifpb.upcensus.infrastructure.util;

import java.net.URI;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;

public class ServerUtils {
	private ServerUtils() {}
	
	private static final String LOCATION_HEADER = "Location";
	
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
	
	public static URI setupUri(String path, String value) {
		return setupUri(null, path, value);
	}

	public static URI setupUri(HttpServletResponse response, String path, Object... values) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path(path)
				.buildAndExpand(values)
				.toUri();
		
		if (Objects.nonNull(response)) {
			response.setHeader(LOCATION_HEADER, uri.toASCIIString());
		}
		
		return uri;
	}
	
}
