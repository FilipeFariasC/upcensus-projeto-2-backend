package br.edu.ifpb.upcensus.domain.shared.exception;

import org.springframework.http.HttpStatus;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.domain.shared.utils.MessageKeys;

public class ResourceNotFoundException extends DomainException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Class<? extends DomainModel<?>> domainClass) {
		super(domainClass, MessageKeys.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
	}

}
