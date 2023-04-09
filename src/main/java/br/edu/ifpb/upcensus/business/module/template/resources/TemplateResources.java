package br.edu.ifpb.upcensus.business.module.template.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.shared.BaseCrudResource;
import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.domain.module.template.service.TemplateService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.presentation.module.template.mapper.TemplateMapper;
import br.edu.ifpb.upcensus.presentation.module.template.request.TemplateRequest;
import br.edu.ifpb.upcensus.presentation.module.template.response.TemplateResponse;

@RestController
@RequestMapping(TemplateEndpoints.TEMPLATES)
public class TemplateResources extends BaseCrudResource<Template, Long, TemplateRequest, TemplateResponse>{

	
	private final ResponseService responseService;
	private final TemplateService templateService;
	private final TemplateMapper templateMapper;
	
	
	public TemplateResources(
			final ResponseService responseService, 
			final TemplateService templateService,
			final TemplateMapper templateMapper
		) {
		super();
		this.responseService = responseService;
		this.templateService = templateService;
		this.templateMapper = templateMapper;
	}

	@Override
	protected ResponseService getResponseService() {
		return responseService;
	}

	@Override
	protected TemplateService getModelService() {
		return templateService;
	}

	@Override
	protected TemplateMapper getDomainMapper() {
		return templateMapper;
	}

}
