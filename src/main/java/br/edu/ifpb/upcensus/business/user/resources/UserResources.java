package br.edu.ifpb.upcensus.business.user.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.domain.user.model.LocalUser;
import br.edu.ifpb.upcensus.domain.user.model.User;
import br.edu.ifpb.upcensus.infrastructure.annotation.user.CurrentUser;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;
import br.edu.ifpb.upcensus.presentation.user.mapper.UserMapper;
import br.edu.ifpb.upcensus.presentation.user.response.UserResponse;
 

 
@RestController
@RequestMapping("/api")
public class UserResources {
	
private final UserMapper userMapper;
private final ResponseService responseService;
	public UserResources( ResponseService responseService,UserMapper userMapper){
	
		this.responseService = responseService;
		this.userMapper = userMapper;
	}

 
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public Response<?> getCurrentUser(@CurrentUser LocalUser user) {
    	
    	UserResponse resp = userMapper.modelToResponse(user.getUser());
        return responseService.buildResponse(resp, HttpStatus.OK);
    }
 
    @GetMapping("/all")
    public ResponseEntity<?> getContent() {
        return ResponseEntity.ok("Public content goes here");
    }
 
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserContent() {
        return ResponseEntity.ok("User content goes here");
    }
 
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminContent() {
        return ResponseEntity.ok("Admin content goes here");
    }
 
    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> getModeratorContent() {
        return ResponseEntity.ok("Moderator content goes here");
    }
}