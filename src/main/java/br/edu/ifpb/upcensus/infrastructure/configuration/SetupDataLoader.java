package br.edu.ifpb.upcensus.infrastructure.configuration;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.upcensus.domain.user.model.User;
import br.edu.ifpb.upcensus.domain.user.role.model.Role;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.users.RoleRepository;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.users.UserRepository;
import br.edu.ifpb.upcensus.presentation.user.SocialProvider;
 
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
 
    private boolean alreadySetup = false;
 
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;
 
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        /*if (alreadySetup) {
            return;
        }*/
        // Create initial roles
        /*Role userRole = createRoleIfNotFound(Role.ROLE_USER);*/
        /*Role adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);*/
        /*Role modRole = createRoleIfNotFound(Role.ROLE_MODERATOR);*/
        //createUserIfNotFound("admin@upcensus.com", new HashSet<>(Arrays.asList(roleRepository.findByName(Role.ROLE_USER), roleRepository.findByName(Role.ROLE_ADMIN),roleRepository.findByName(Role.ROLE_MODERATOR))));
        //alreadySetup = true;
    }
 
    @Transactional
    private final User createUserIfNotFound(final String email, Set<Role> roles) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setName("Admin");
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("admin@"));
            user.setRoles(roles);
            user.setProvider(SocialProvider.LOCAL.getProviderType());
            user.setEnabled(true);
            user = userRepository.save(user);
        }
        return user;
    }
 
    @Transactional
    private final Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = roleRepository.save(new Role(name));
        }
        return role;
    }
}