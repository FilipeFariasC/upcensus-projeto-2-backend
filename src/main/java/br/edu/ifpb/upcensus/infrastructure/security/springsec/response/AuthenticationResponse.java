package br.edu.ifpb.upcensus.infrastructure.security.springsec.response;

import java.util.Objects;

import br.edu.ifpb.upcensus.presentation.user.response.UserResponse;



public class AuthenticationResponse {
    private String accessToken;
    private UserResponse user;

    public AuthenticationResponse(String accessToken, UserResponse user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public UserResponse getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationResponse that = (AuthenticationResponse) o;
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
	