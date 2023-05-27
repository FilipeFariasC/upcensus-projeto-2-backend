package br.edu.ifpb.upcensus.business.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.upcensus.domain.user.model.LocalUser;
import br.edu.ifpb.upcensus.domain.user.model.User;
import br.edu.ifpb.upcensus.domain.user.service.UserService;
import br.edu.ifpb.upcensus.infrastructure.exception.ResourceNotFoundException;
import br.edu.ifpb.upcensus.infrastructure.util.security.GeneralUtils;

@Service("localUserDetailService")
public class LocalUserDetailService implements UserDetailsService{
	 @Autowired
	    private UserService userService;
	 
	/* public LocalUserDetailService(UserService userService) {
		this.userService = userService;
	}*/
	 
	    @Override
	    @Transactional
	    public LocalUser loadUserByUsername(final String email) throws UsernameNotFoundException {
	        User user = userService.findUserByEmail(email);
	        if (user == null) {
	            throw new UsernameNotFoundException("User " + email + " was not found in the database");
	        }
	        return createLocalUser(user);
	    }
	 
	    @Transactional
	    public LocalUser loadUserById(Long id) {
	        User user = userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	        return createLocalUser(user);
	    }
	 
	    /**
	     * @param user
	     * @return
	     */
	    private LocalUser createLocalUser(User user) {
	        return new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()), user);
	    }
}
