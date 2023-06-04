package br.edu.ifpb.upcensus.business.module.module.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.business.job.JobService;
import br.edu.ifpb.upcensus.business.module.module.exception.ModuleMigrationNotConfigured;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.module.service.MigrationService;
import br.edu.ifpb.upcensus.domain.module.module.service.ModuleService;
import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.exception.ElementNotFoundException;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.module.ModuleRepository;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;

@Service
public class ModuleServiceImpl implements ModuleService {

	private final ModuleRepository moduleRepository;
	private final JobService jobService;
	private final MigrationService migrationService;
	
	public ModuleServiceImpl(
			final ModuleRepository moduleRepository, 
			final JobService jobService,
			final MigrationService migrationService
		) {
		super();
		this.moduleRepository = moduleRepository;
		this.jobService = jobService;
		this.migrationService = migrationService;
	}

	@Override
	public ModuleRepository getRepository() {
		return moduleRepository;
	}

	@Override
	public Class<Module> getDomainClass() {
		return Module.class;
	}

	@PostConstruct
	private void setup() {
		this.jobService.setModuleService(this);
	}
	
	@Override
	public void uploadFile(Long id, MultipartFile file, boolean ignoreHeaderRow, FileType fileType, String delimiter) {
		Module module = findById(id);
		jobService.runFileJob(file, saveAndFlush(module), ignoreHeaderRow, fileType, delimiter);
	}

	@Override
	public void registerAnswers(Long id, Long idTemplate, List<Map<String, String>> answers) {
		Module module = findById(id);
		Optional<InputTemplate> opt = module.getTemplateById(idTemplate);
		
		InputTemplate template = opt.orElseThrow(()->new ElementNotFoundException(InputTemplate.class, idTemplate));
		
		jobService.runAnswerJob(
			module, 
			template, 
			answers
		);
	}

	@Override
	public void migrate(Long id) {
		Module module = findById(id);
		migrationService.migrate(module);
	}
}
