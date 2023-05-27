package br.edu.ifpb.upcensus.business.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.domain.user.exception.UserAlreadyExistAuthenticationException;
import br.edu.ifpb.upcensus.domain.user.model.LocalUser;
import br.edu.ifpb.upcensus.domain.user.service.UserService;
import br.edu.ifpb.upcensus.infrastructure.security.springsec.response.JwtAuthenticationResponse;
import br.edu.ifpb.upcensus.infrastructure.security.springsec.token.TokenProvider;
import br.edu.ifpb.upcensus.infrastructure.util.security.GeneralUtils;
import br.edu.ifpb.upcensus.presentation.user.request.LoginRequest;
import br.edu.ifpb.upcensus.presentation.user.request.SignUpRequest;
import br.edu.ifpb.upcensus.presentation.user.response.ApiResponse;

 
@RestController
@RequestMapping("/api/auth")
public class AuthController {
 
    @Autowired
    AuthenticationManager authenticationManager;
 
    @Autowired
    UserService userService;
 
    @Autowired
    TokenProvider tokenProvider;
 
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        LocalUser localUser = (LocalUser) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(localUser)));
    }
 
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            userService.registerNewUser(signUpRequest);
        } catch (UserAlreadyExistAuthenticationException e) {
            return new ResponseEntity<>(new ApiResponse(false, "Email já registrado anteriormente."), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "Registrado com sucesso."));
    }
}
