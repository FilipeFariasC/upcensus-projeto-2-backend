package br.edu.ifpb.upcensus.domain.shared.exception;

import java.util.Arrays;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;

public class DomainModelException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Class<? extends DomainModel<?>> domainClass;
	private final Object[] exceptionParams;
	
	public DomainModelException(
		final Class<? extends DomainModel<?>> domainClass,
		final Object... exceptionParams
	) {
		super();
		this.domainClass = domainClass;
		this.exceptionParams = exceptionParams;
	}

	public Class<? extends DomainModel<?>> getDomainClass() {
		return domainClass;
	}

	public Object[] getExceptionParams() {
		return exceptionParams;
	}

	@Override
	public String toString() {
		return String.format("{domainClass: %s, exceptionParams: %s}", domainClass, Arrays.toString(exceptionParams));
	}



}
