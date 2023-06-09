package br.edu.ifpb.upcensus.domain.user.exception;

import org.springframework.security.core.AuthenticationException;

@br.edu.ifpb.upcensus.infrastructure.annotation.DomainException(
		key = "user.aready-exist",
		append = true
	)
public class UserAlreadyExistAuthenticationException extends AuthenticationException{
	
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }
 
}
