package br.edu.ifpb.upcensus.infrastructure.persistence.repository.users;

import br.edu.ifpb.upcensus.domain.user.role.model.Role;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.DomainRepository;

public interface RoleRepository extends DomainRepository<Role, Long>{

	Role findByName(String name);
}
