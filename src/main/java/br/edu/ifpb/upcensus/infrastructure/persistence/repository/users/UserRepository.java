package br.edu.ifpb.upcensus.infrastructure.persistence.repository.users;

import br.edu.ifpb.upcensus.domain.user.model.User;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.DomainRepository;

public interface UserRepository extends DomainRepository<User, Long>{

	User findByEmail(String email);
	 
    boolean existsByEmail(String email);
}
