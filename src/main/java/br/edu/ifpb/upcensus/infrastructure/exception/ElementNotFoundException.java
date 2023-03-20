package br.edu.ifpb.upcensus.infrastructure.exception;

import org.springframework.http.HttpStatus;

import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainException;
import br.edu.ifpb.upcensus.infrastructure.util.MessageKeys;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;

@DomainException(
	key = MessageKeys.ELEMENT_NOT_FOUND,
	status = HttpStatus.BAD_REQUEST,
	append = true
)
public class ElementNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String className;
	private final Object value;
	
	public ElementNotFoundException(
		final Class<?> elementObj,
		final Object value
	) {
		super();
		this.className = setupClassName(elementObj);
		this.value = value;
	}
	
	private String setupClassName(Class<?> classObj) {
		DomainDescriptor descriptor = classObj.getAnnotation(DomainDescriptor.class);
		
		if (ObjectUtils.nonNull(descriptor)) return descriptor.name();
		
		return classObj.getSimpleName();
	}

	@Override
	public String getMessage() {
		return String.format("Element: %s;Value: %s", className, value);
	}

}
