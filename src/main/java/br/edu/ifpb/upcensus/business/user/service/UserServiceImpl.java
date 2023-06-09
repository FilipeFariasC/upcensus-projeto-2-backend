package br.edu.ifpb.upcensus.business.user.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.edu.ifpb.upcensus.domain.user.exception.UserAlreadyExistAuthenticationException;
import br.edu.ifpb.upcensus.domain.user.model.LocalUser;
import br.edu.ifpb.upcensus.domain.user.model.User;
import br.edu.ifpb.upcensus.domain.user.role.model.Role;
import br.edu.ifpb.upcensus.domain.user.service.UserService;
import br.edu.ifpb.upcensus.infrastructure.exception.OAuth2AuthenticationProcessingException;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.DomainRepository;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.users.RoleRepository;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.users.UserRepository;
import br.edu.ifpb.upcensus.infrastructure.util.security.GeneralUtils;
import br.edu.ifpb.upcensus.presentation.user.SocialProvider;
import br.edu.ifpb.upcensus.presentation.user.info.OAuth2UserInfo;
import br.edu.ifpb.upcensus.presentation.user.info.OAuth2UserInfoFactory;
import br.edu.ifpb.upcensus.presentation.user.request.UserRequest;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;


	private final RoleRepository roleRepository;

	private  final PasswordEncoder passwordEncoder;

	public UserServiceImpl(final UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public DomainRepository<User, Long> getRepository() {

		return userRepository;
	}

	@Override
	public Class<User> getDomainClass() {

		return User.class;
	}

	@Override
	//@Transactional(value = "transactionManager")
	public User registerNewUser(final User signUpRequest) throws UserAlreadyExistAuthenticationException {
		if (signUpRequest.getId() != null && userRepository.existsById(signUpRequest.getId())) {
			throw new UserAlreadyExistAuthenticationException("User with User id " + signUpRequest.getId() + " already exist");
		} else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
		}
		register(signUpRequest);
		/*user = userRepository.save(user);
		userRepository.flush();*/
		//TODO ERRO SAVE USER
		return signUpRequest;
	}
	
	public User buildUser(final UserRequest formDTO) {
        User user = new User();
        user.setName(formDTO.getName());
        user.setEmail(formDTO.getEmail());
        user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
        final HashSet<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName(Role.ROLE_USER));
        user.setRoles(roles);
        user.setProvider(formDTO.getSocialProvider().getProviderType());
        user.setProviderUserId(formDTO.getProviderUserId());
        return user;
    }

	@Override
	public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

	@Override
	@Transactional
    public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
            throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
        } else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found");
        }
        UserRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
        User user = findUserByEmail(oAuth2UserInfo.getEmail());
        if (user != null) {
            if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
                throw new OAuth2AuthenticationProcessingException(
                        "Parece que você já se cadastrou com a conta  " + user.getProvider() + ". Por favor, utilize a conta " + user.getProvider() + "  para login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
        	User details = buildUser(userDetails);
            user = registerNewUser(details);
        }
 
        return LocalUser.create(user, attributes, idToken, userInfo);
    }
	
	
	private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        return userRepository.save(existingUser);
    }
 
    private UserRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        return UserRequest.getBuilder().addProviderUserID(oAuth2UserInfo.getId()).addDisplayName(oAuth2UserInfo.getName()).addEmail(oAuth2UserInfo.getEmail())
                .addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword("changeit").build();
    }
 
    

	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	 @Override
	    public Optional<User> findUserById(Long id) {
	        return userRepository.findById(id);
	    }

}
