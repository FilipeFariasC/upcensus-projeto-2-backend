package br.edu.ifpb.upcensus.business.module.template.input.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.domain.module.template.service.InputTemplateService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.module.InputTemplateRepository;

@Service
public class InputTemplateServiceImpl implements InputTemplateService {

	
	private final InputTemplateRepository templateRepository;
	
	public InputTemplateServiceImpl(final InputTemplateRepository templateRepository) {
		super();
		this.templateRepository = templateRepository;
	}

	@Override
	public InputTemplateRepository getRepository() {
		return templateRepository;
	}

	@Override
	public Class<InputTemplate> getDomainClass() {
		return InputTemplate.class;
	}

}
