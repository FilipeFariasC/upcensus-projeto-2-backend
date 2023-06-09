package br.edu.ifpb.upcensus.domain.user.role.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.domain.user.model.User;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;



@Entity
@Table(name = "t_role", schema = "usersystem")
@SequenceGenerator(name = "t_role_id_seq", schema = "usersystem", sequenceName = "t_role_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Cargo")

public class Role extends DomainModel<Long> implements GrantedAuthority{

	private static final long serialVersionUID = 1L;

	public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_MODERATOR = "MODERATOR";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_role_id_seq")
	private Long id;
	
	private String name;
	
	
	
	@ManyToMany(mappedBy = "roles")
    private Set<User> users;
	
	public Role(String name) {
        this.name = name;
    }
	

	public Role(Long id, String name, Set<User> users) {
		this.id = id;
		this.name = name;
		this.users = users;
	}
	
	public Role() {
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public Long getId() {
		return id;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, name, users);
		return result;
	}
 
    
 
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(users, other.users);
	}


	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Role [name=").append(name).append("]").append("[id=").append(id).append("]");
        return builder.toString();
    }


	@Override
	public String getAuthority() {
		return name;
	}
	
}
