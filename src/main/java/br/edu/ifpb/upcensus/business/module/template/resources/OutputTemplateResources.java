package br.edu.ifpb.upcensus.business.module.template.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.shared.BaseCrudResource;
import br.edu.ifpb.upcensus.domain.module.template.model.OutputTemplate;
import br.edu.ifpb.upcensus.domain.module.template.service.OutputTemplateService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.presentation.module.template.mapper.OutputTemplateMapper;
import br.edu.ifpb.upcensus.presentation.module.template.request.OutputTemplateRequest;
import br.edu.ifpb.upcensus.presentation.module.template.response.OutputTemplateResponse;

@RestController
@RequestMapping(OutputTemplateEndpoints.TEMPLATES)
public class OutputTemplateResources extends BaseCrudResource<OutputTemplate, Long, OutputTemplateRequest, OutputTemplateResponse>{

	
	private final ResponseService responseService;
	private final OutputTemplateService outputTemplateService;
	private final OutputTemplateMapper outputTemplateMapper;
	
	
	public OutputTemplateResources(
			final ResponseService responseService, 
			final OutputTemplateService outputTemplateService,
			final OutputTemplateMapper outputTemplateMapper
		) {
		super();
		this.responseService = responseService;
		this.outputTemplateService = outputTemplateService;
		this.outputTemplateMapper = outputTemplateMapper;
	}

	@Override
	protected ResponseService getResponseService() {
		return responseService;
	}

	@Override
	protected OutputTemplateService getModelService() {
		return outputTemplateService;
	}

	@Override
	protected OutputTemplateMapper getDomainMapper() {
		return outputTemplateMapper;
	}
	

}
