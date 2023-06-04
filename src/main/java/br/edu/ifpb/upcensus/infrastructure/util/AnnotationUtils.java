package br.edu.ifpb.upcensus.infrastructure.util;

import java.lang.annotation.Annotation;
import java.util.Optional;

import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainException;

public class AnnotationUtils {
	private AnnotationUtils() {}
	
	public static <T extends Annotation> T getAnnotation(Class<?> classObj, Class<T> annotation) {
		if (ObjectUtils.isNull(classObj) || ObjectUtils.isNull(annotation))
			return null;
		return org.springframework.core.annotation.AnnotationUtils.findAnnotation(classObj, annotation);
	}
	
	public static DomainDescriptor getDomainDescriptor(Class<?> classObj) {
		return getAnnotation(classObj, DomainDescriptor.class);
	}
	
	public static DomainException getDomainException(Class<?> classObj) {
		return getAnnotation(classObj, DomainException.class);
	}
	
	public static String getClassName(Class<?> classObj) {
		if (ObjectUtils.isNull(classObj))
			return null;
		
		return Optional.ofNullable(getDomainDescriptor(classObj))
			.map(DomainDescriptor::name)
			.orElseGet(classObj::getSimpleName);
	}
	
	public static boolean isDomainException(Class<? extends Throwable> classObj) {
		if (ObjectUtils.isNull(classObj))
			return false;
		return ObjectUtils.nonNull(getAnnotation(classObj, DomainException.class));
	}
}
