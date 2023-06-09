package br.edu.ifpb.upcensus.domain.user.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import br.edu.ifpb.upcensus.domain.shared.service.DomainService;
import br.edu.ifpb.upcensus.domain.user.exception.UserAlreadyExistAuthenticationException;
import br.edu.ifpb.upcensus.domain.user.model.LocalUser;
import br.edu.ifpb.upcensus.domain.user.model.User;
import br.edu.ifpb.upcensus.presentation.user.request.UserRequest;

public interface UserService extends DomainService<User, Long>{
	
	 public User registerNewUser(User user) throws UserAlreadyExistAuthenticationException;
	 
	    User findUserByEmail(String email);
	 
	    
	    Optional<User> findUserById(Long id);
	    LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);


}
