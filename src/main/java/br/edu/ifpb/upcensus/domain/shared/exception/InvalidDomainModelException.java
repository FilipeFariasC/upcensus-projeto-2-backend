package br.edu.ifpb.upcensus.domain.shared.exception;

import org.springframework.http.HttpStatus;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.domain.shared.utils.MessageKeys;

public class InvalidDomainModelException extends DomainException {

	private static final long serialVersionUID = 1L;

	public InvalidDomainModelException(Class<? extends DomainModel<?>> domainClass) {
		super(domainClass, MessageKeys.RESOURCE_INVALID, HttpStatus.BAD_REQUEST);
	}

}
