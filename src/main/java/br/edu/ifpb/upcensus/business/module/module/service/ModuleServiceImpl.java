package br.edu.ifpb.upcensus.business.module.module.service;

import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.module.service.ModuleService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.module.ModuleRepository;

public class ModuleServiceImpl implements ModuleService{
	
	private final ModuleRepository moduleRepository;
	
	public ModuleServiceImpl(final ModuleRepository moduleRepository) {
		super();
		this.moduleRepository = moduleRepository;
	}

	@Override
	public ModuleRepository getRepository() {
		return moduleRepository;
	}

	@Override
	public Class<Module> getDomainClass() {
		return Module.class;
	}


}
