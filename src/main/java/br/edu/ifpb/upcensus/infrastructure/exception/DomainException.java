package br.edu.ifpb.upcensus.infrastructure.exception;

import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;

public abstract class DomainException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Class<?> classObject;
	private final String className;
	private final Object[] params;
	
	public DomainException(
		final Class<?> classObject,
		final Object... params
	) {
		super();
		this.classObject = classObject;
		this.params = params;
		
		DomainDescriptor descriptor = classObject.getAnnotation(DomainDescriptor.class);
		
		if (ObjectUtils.nonNull(descriptor)) {
			this.className = descriptor.name();
		} else {
			this.className = classObject.getSimpleName();
		}
	}

	public Class<?> getClassObject() {
		return classObject;
	}

	public String getClassName() {
		return className;
	}

	public Object[] getParams() {
		return params;
	}

}
