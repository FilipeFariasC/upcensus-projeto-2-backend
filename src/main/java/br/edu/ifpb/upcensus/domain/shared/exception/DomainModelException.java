package br.edu.ifpb.upcensus.domain.shared.exception;

import java.util.Arrays;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.exception.DomainException;

public class DomainModelException extends DomainException {

	private static final long serialVersionUID = 1L;
	
	public DomainModelException(
		final Class<? extends DomainModel<?>> domainClass,
		final Object... exceptionParams
	) {
		super(domainClass, exceptionParams);
	}

	@Override
	public String toString() {
		return String.format("{domain_class: %s, exception_params: %s}", getClassObject(), Arrays.toString(getParams()));
	}



}
