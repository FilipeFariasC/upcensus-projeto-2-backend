package br.edu.ifpb.upcensus.domain.shared.exception;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;

public class DomainModelException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final Class<? extends DomainModel<?>> domainClass;
	
	public DomainModelException(Class<? extends DomainModel<?>> domainClass) {
		super();
		this.domainClass = domainClass;
	}

	public Class<? extends DomainModel<?>> getDomainClass() {
		return domainClass;
	}

}
