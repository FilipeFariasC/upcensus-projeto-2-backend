package br.edu.ifpb.upcensus.business.auth;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.domain.user.exception.UserAlreadyExistAuthenticationException;
import br.edu.ifpb.upcensus.domain.user.model.LocalUser;
import br.edu.ifpb.upcensus.domain.user.model.User;
import br.edu.ifpb.upcensus.domain.user.service.UserService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.infrastructure.security.springsec.response.AuthenticationResponse;
import br.edu.ifpb.upcensus.infrastructure.security.springsec.token.TokenProvider;
import br.edu.ifpb.upcensus.infrastructure.util.security.GeneralUtils;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;
import br.edu.ifpb.upcensus.presentation.user.mapper.UserMapper;
import br.edu.ifpb.upcensus.presentation.user.request.LoginRequest;
import br.edu.ifpb.upcensus.presentation.user.request.UserRequest;
import br.edu.ifpb.upcensus.presentation.user.response.UserResponse;


@RestController
@RequestMapping("/auth")
public class AuthController {
	private final AuthenticationManager authenticationManager;

	private final UserService userService;

	private final TokenProvider tokenProvider;
	
	private final ResponseService responseService;
	
	private final UserMapper userMapper;
	
	public AuthController(AuthenticationManager authenticationManager,UserService userService,TokenProvider tokenProvider, ResponseService responseService,UserMapper userMapper){
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.tokenProvider = tokenProvider;
		this.responseService = responseService;
		this.userMapper = userMapper;
	}



	@PostMapping("/signin")
	public Response<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.createToken(authentication);
		LocalUser localUser = (LocalUser) authentication.getPrincipal();
		return responseService.buildResponse(null, HttpStatus.OK);//TODO response do login com jwt
		
	}

	@GetMapping
	public String welcome(Principal principal){
		return "Olá mundo! "+principal.getName();
	}

	@PostMapping("/signup")
	public Response<?> registerUser(@Valid @RequestBody UserRequest signUpRequest) {
		User user = userMapper.requestToModel(signUpRequest);
		
		UserResponse resp = userMapper.modelToResponse(user);
		try {
			
			userService.registerNewUser(user);
			return responseService.buildResponse(resp, HttpStatus.CREATED);
		} catch (UserAlreadyExistAuthenticationException e) {
			return responseService.buildResponse(resp, HttpStatus.CONFLICT);
		}
	}
}
