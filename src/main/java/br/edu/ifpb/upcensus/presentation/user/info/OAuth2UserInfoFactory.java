package br.edu.ifpb.upcensus.presentation.user.info;

import java.util.Map;

import br.edu.ifpb.upcensus.infrastructure.exception.OAuth2AuthenticationProcessingException;
import br.edu.ifpb.upcensus.presentation.user.SocialProvider;

 
public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(SocialProvider.GOOGLE.getProviderType())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}