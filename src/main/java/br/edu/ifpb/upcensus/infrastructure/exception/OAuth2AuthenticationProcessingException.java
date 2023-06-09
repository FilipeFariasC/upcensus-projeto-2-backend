package br.edu.ifpb.upcensus.infrastructure.exception;

import org.springframework.security.core.AuthenticationException;


@br.edu.ifpb.upcensus.infrastructure.annotation.DomainException(
		key = "oauth.authentication-processing",
		append = true
	)
public class OAuth2AuthenticationProcessingException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
        super(msg, t);
    }
 
    public OAuth2AuthenticationProcessingException(String msg) {
        super(msg);
    }
}
