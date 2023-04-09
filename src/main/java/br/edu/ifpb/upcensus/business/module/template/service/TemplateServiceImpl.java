package br.edu.ifpb.upcensus.business.module.template.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.domain.module.template.service.TemplateService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.form.TemplateRepository;

@Service
public class TemplateServiceImpl implements TemplateService {

	
	private final TemplateRepository templateRepository;
	
	public TemplateServiceImpl(final TemplateRepository templateRepository) {
		super();
		this.templateRepository = templateRepository;
	}

	@Override
	public TemplateRepository getRepository() {
		return templateRepository;
	}

	@Override
	public Class<Template> getDomainClass() {
		return Template.class;
	}

}
