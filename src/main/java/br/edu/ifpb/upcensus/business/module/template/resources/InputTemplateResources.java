package br.edu.ifpb.upcensus.business.module.template.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.shared.BaseCrudResource;
import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.domain.module.template.service.InputTemplateService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.presentation.module.template.mapper.InputTemplateMapper;
import br.edu.ifpb.upcensus.presentation.module.template.request.InputTemplateRequest;
import br.edu.ifpb.upcensus.presentation.module.template.response.InputTemplateResponse;

@RestController
@RequestMapping(InputTemplateEndpoints.TEMPLATES)
public class InputTemplateResources extends BaseCrudResource<InputTemplate, Long, InputTemplateRequest, InputTemplateResponse>{

	
	private final ResponseService responseService;
	private final InputTemplateService inputTemplateService;
	private final InputTemplateMapper inputTemplateMapper;
	
	
	public InputTemplateResources(
			final ResponseService responseService, 
			final InputTemplateService inputTemplateService,
			final InputTemplateMapper inputTemplateMapper
		) {
		super();
		this.responseService = responseService;
		this.inputTemplateService = inputTemplateService;
		this.inputTemplateMapper = inputTemplateMapper;
	}

	@Override
	protected ResponseService getResponseService() {
		return responseService;
	}

	@Override
	protected InputTemplateService getModelService() {
		return inputTemplateService;
	}

	@Override
	protected InputTemplateMapper getDomainMapper() {
		return inputTemplateMapper;
	}
	

}
