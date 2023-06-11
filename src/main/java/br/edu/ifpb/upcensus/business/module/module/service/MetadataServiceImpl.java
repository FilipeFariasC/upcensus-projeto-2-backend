package br.edu.ifpb.upcensus.business.module.module.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.upcensus.business.job.JobService;
import br.edu.ifpb.upcensus.domain.module.module.model.Metadata;
import br.edu.ifpb.upcensus.domain.module.module.service.MetadataService;
import br.edu.ifpb.upcensus.domain.module.module.service.MigrationService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.module.MetadataRepository;

@Service
public class MetadataServiceImpl implements MetadataService {

	private final MetadataRepository moduleRepository;
	
	public MetadataServiceImpl(
			final MetadataRepository moduleRepository, 
			final JobService jobService,
			final MigrationService migrationService
		) {
		super();
		this.moduleRepository = moduleRepository;
	}

	@Override
	public MetadataRepository getRepository() {
		return moduleRepository;
	}

	@Override
	public Class<Metadata> getDomainClass() {
		return Metadata.class;
	}
	
}
