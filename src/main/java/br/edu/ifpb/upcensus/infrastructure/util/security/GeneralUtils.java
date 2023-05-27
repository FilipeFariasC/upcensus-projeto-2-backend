package br.edu.ifpb.upcensus.infrastructure.util.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.edu.ifpb.upcensus.domain.user.model.LocalUser;
import br.edu.ifpb.upcensus.domain.user.model.User;
import br.edu.ifpb.upcensus.domain.user.role.model.Role;
import br.edu.ifpb.upcensus.presentation.user.SocialProvider;
import br.edu.ifpb.upcensus.presentation.user.info.UserInfo;

public class GeneralUtils {
	
	public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
 
    public static SocialProvider toSocialProvider(String providerId) {
        for (SocialProvider socialProvider : SocialProvider.values()) {
            if (socialProvider.getProviderType().equals(providerId)) {
                return socialProvider;
            }
        }
        return SocialProvider.LOCAL;
    }
 
    public static UserInfo buildUserInfo(LocalUser localUser) {
        List<String> roles = localUser.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        User user = localUser.getUser();
        return new UserInfo(user.getId().toString(), user.getName(), user.getEmail(), roles);
    }

}
