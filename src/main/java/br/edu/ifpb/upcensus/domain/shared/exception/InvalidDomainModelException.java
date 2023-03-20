package br.edu.ifpb.upcensus.domain.shared.exception;

import org.springframework.http.HttpStatus;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainException;
import br.edu.ifpb.upcensus.infrastructure.util.MessageKeys;

@DomainException(
	key = MessageKeys.RESOURCE_INVALID,
	status = HttpStatus.BAD_REQUEST
)
public class InvalidDomainModelException extends DomainModelException {

	private static final long serialVersionUID = 1L;

	public InvalidDomainModelException(Class<? extends DomainModel<?>> domainClass) {
		super(domainClass);
	}

}
