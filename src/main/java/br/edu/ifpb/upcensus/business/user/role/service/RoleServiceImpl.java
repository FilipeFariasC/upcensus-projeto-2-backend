package br.edu.ifpb.upcensus.business.user.role.service;

import br.edu.ifpb.upcensus.domain.user.role.model.Role;
import br.edu.ifpb.upcensus.domain.user.role.service.RoleService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.DomainRepository;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.users.RoleRepository;

public class RoleServiceImpl implements RoleService{
	
private final RoleRepository roleRepository;
	
	public RoleServiceImpl(final RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	@Override
	public DomainRepository<Role, Long> getRepository() {
	
		return roleRepository;
	}

	@Override
	public Class<Role> getDomainClass() {
		return Role.class;
	}

}
