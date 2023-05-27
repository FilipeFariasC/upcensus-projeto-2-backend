package br.edu.ifpb.upcensus.infrastructure.security.springsec.response;

import br.edu.ifpb.upcensus.presentation.user.info.UserInfo;
import java.util.Objects;



public class JwtAuthenticationResponse {
    private String accessToken;
    private UserInfo user;

    public JwtAuthenticationResponse(String accessToken, UserInfo user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public UserInfo getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtAuthenticationResponse that = (JwtAuthenticationResponse) o;
        return Objects.equals(accessToken, that.accessToken) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, user);
    }

    @Override
    public String toString() {
        return "JwtAuthenticationResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", user=" + user +
                '}';
    }
}
	