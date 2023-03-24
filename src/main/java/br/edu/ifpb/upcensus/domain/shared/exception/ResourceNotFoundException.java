package br.edu.ifpb.upcensus.domain.shared.exception;

import org.springframework.http.HttpStatus;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainException;
import br.edu.ifpb.upcensus.infrastructure.util.MessageKeys;

@DomainException(
	key = MessageKeys.RESOURCE_NOT_FOUND,
	status = HttpStatus.NOT_FOUND
)
public class ResourceNotFoundException extends DomainModelException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Class<? extends DomainModel<?>> domainClass, Object... params) {
		super(domainClass, params);
	}

}
