package br.edu.ifpb.upcensus.presentation.user.request;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.presentation.user.PasswordMatchable;
import br.edu.ifpb.upcensus.presentation.user.SocialProvider;


public class UserRequest implements PasswordMatchable{

	private Long userID;

	private String providerUserId;

	@NotEmpty
	private String name;



	@NotEmpty
	private String email;

	private SocialProvider socialProvider;

	@Size(min = 6, message = "{Size.userDto.password}")
	private String password;

	@NotEmpty
	private String matchingPassword;



	@Override
	public String toString() {
		return "SignUpRequest [userID=" + userID + ", providerUserId=" + providerUserId + ", name=" + name + ", email="
				+ email + ", socialProvider=" + socialProvider + ", password=" + password + ", matchingPassword="
				+ matchingPassword + "]";
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SocialProvider getSocialProvider() {
		return socialProvider;
	}

	public void setSocialProvider(SocialProvider socialProvider) {
		this.socialProvider = socialProvider;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserRequest(String providerUserId, String displayName, String email, String password, SocialProvider socialProvider) {
		this.providerUserId = providerUserId;
		this.name = displayName;
		this.email = email;
		this.password = password;
		this.socialProvider = socialProvider;
	}
	public UserRequest() {
		
	}

	public static Builder getBuilder() {
		return new Builder();
	}



	@Override
	public int hashCode() {
		return Objects.hash(email, matchingPassword, name, password, providerUserId, socialProvider, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRequest other = (UserRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(matchingPassword, other.matchingPassword)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(providerUserId, other.providerUserId) && socialProvider == other.socialProvider
				&& Objects.equals(userID, other.userID);
	}



	public static class Builder {
		private String providerUserID;
		private String displayName;
		private String email;
		private String password;
		private SocialProvider socialProvider;

		public Builder addProviderUserID(final String userID) {
			this.providerUserID = userID;
			return this;
		}

		public Builder addDisplayName(final String displayName) {
			this.displayName = displayName;
			return this;
		}

		public Builder addEmail(final String email) {
			this.email = email;
			return this;
		}

		public Builder addPassword(final String password) {
			this.password = password;
			return this;
		}

		public Builder addSocialProvider(final SocialProvider socialProvider) {
			this.socialProvider = socialProvider;
			return this;
		}

		public UserRequest build() {
			return new UserRequest(providerUserID, displayName, email, password, socialProvider);
		}
	}
}


