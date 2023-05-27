package br.edu.ifpb.upcensus.presentation.user.request;

import java.util.Objects;

import javax.validation.constraints.NotBlank;


public class LoginRequest {
    @NotBlank
    private String email;
 
    @NotBlank
    private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [email=" + email + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginRequest other = (LoginRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}
    
    
}
