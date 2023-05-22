package br.edu.ifpb.upcensus.business.module.template.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.domain.module.template.model.OutputTemplate;
import br.edu.ifpb.upcensus.domain.module.template.service.OutputTemplateService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.module.InputTemplateRepository;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.module.OutputTemplateRepository;

@Service
public class OutputTemplateServiceImpl implements OutputTemplateService {

	
	private final OutputTemplateRepository outputTemplateRepository;
	
	public OutputTemplateServiceImpl(final OutputTemplateRepository outputTemplateRepository) {
		super();
		this.outputTemplateRepository = outputTemplateRepository;
	}

	@Override
	public OutputTemplateRepository getRepository() {
		return outputTemplateRepository;
	}

	@Override
	public Class<OutputTemplate> getDomainClass() {
		return OutputTemplate.class;
	}

}
