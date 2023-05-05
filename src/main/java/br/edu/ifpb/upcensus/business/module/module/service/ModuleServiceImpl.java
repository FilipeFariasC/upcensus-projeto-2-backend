package br.edu.ifpb.upcensus.business.module.module.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import br.edu.ifpb.upcensus.business.job.JobService;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.module.service.ModuleService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.module.ModuleRepository;

@Service
public class ModuleServiceImpl implements ModuleService {

	private final ModuleRepository moduleRepository;
	private final JobService jobService;
	
	public ModuleServiceImpl(final ModuleRepository moduleRepository, final JobService jobService) {
		super();
		this.moduleRepository = moduleRepository;
		this.jobService = jobService;
	}

	@Override
	public ModuleRepository getRepository() {
		return moduleRepository;
	}

	@Override
	public Class<Module> getDomainClass() {
		return Module.class;
	}

	@Override
	public JobService getJobService() {
		return jobService;
	}

	@PostConstruct
	private void setup() {
		this.jobService.setModuleService(this);
	}

}
