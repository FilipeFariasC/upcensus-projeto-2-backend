package br.edu.ifpb.upcensus.domain.shared.exception;

import org.springframework.http.HttpStatus;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;

public abstract class DomainException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private final Class<? extends DomainModel<?>> domainClass;
	private final String messageKey;
	private final HttpStatus code;
	
	public DomainException(Class<? extends DomainModel<?>> domainClass, String messageKey, HttpStatus code) {
		super();
		this.domainClass = domainClass;
		this.messageKey = messageKey;
		this.code = code;
	}
	
	
	public Class<? extends DomainModel<?>> getDomainClass() {
		return domainClass;
	}
	public String getMessageKey() {
		return messageKey;
	}
	public HttpStatus getCode() {
		return code;
	}
	
}
