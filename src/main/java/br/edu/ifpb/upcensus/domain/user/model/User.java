package br.edu.ifpb.upcensus.domain.user.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.domain.user.role.model.Role;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;


@Entity
@Table(name = "t_usersys", schema = "usersystem")
@SequenceGenerator(name = "t_usersys_id_seq", schema = "usersystem", sequenceName = "t_usersys_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Usuário")

public class User extends DomainModel<Long> implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_usersys_id_seq")
	private Long id;
	
	private String name;
	
	private String password;
	
	
	
	@Column(name = "provider_id")
    private String providerUserId;
	
	private String provider;
	
	private String email;
	
	@Column(name = "enabled", columnDefinition = "BIT", length = 1)
	private boolean enabled;
	
	@JsonIgnore
    @ManyToMany
    @JoinTable(
    		name = "t_usersys_role", 
    		schema = "usersystem",
    		joinColumns = { @JoinColumn(name = "id_usersys") }, inverseJoinColumns = { @JoinColumn(name = "id_role") })
    private Set<Role> roles;
	
	
	public User() {
        // Construtor vazio
    }

    public User(Long id, String name, String password, String providerUserId, String provider, String email,
                boolean enabled, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.providerUserId = providerUserId;
        this.provider = provider;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
    }




	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getProviderUserId() {
		return providerUserId;
	}



	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}



	public String getProvider() {
		return provider;
	}



	public void setProvider(String provider) {
		this.provider = provider;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Set<Role> getRoles() {
		return roles;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



	public void setId(Long id) {
		this.id = id;
	}



	@Override
	public Long getId() {
		return id;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", providerUserId=" + providerUserId
				+ ", provider=" + provider + ", email=" + email + ", roles=" + roles + "]";
	}




	public boolean isEnabled() {
		return enabled;
	}




	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
	



}
