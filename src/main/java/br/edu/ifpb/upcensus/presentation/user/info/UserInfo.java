package br.edu.ifpb.upcensus.presentation.user.info;

import java.util.List;
import java.util.Objects;

public class UserInfo {
    private String id;
    private String displayName;
    private String email;
    private List<String> roles;
    
    
    
    
    
	public UserInfo(String id, String displayName, String email, List<String> roles) {
		this.id = id;
		this.displayName = displayName;
		this.email = email;
		this.roles = roles;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", displayName=" + displayName + ", email=" + email + ", roles=" + roles + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(displayName, email, id, roles);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		return Objects.equals(displayName, other.displayName) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(roles, other.roles);
	}
    
    
    
}