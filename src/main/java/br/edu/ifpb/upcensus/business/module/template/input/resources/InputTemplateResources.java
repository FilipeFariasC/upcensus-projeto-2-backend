package br.edu.ifpb.upcensus.business.module.template.input.resources;

import static br.edu.ifpb.upcensus.business.form.characteristic.resources.CharacteristicsEndpoints.ATTRIBUTES;
import static br.edu.ifpb.upcensus.business.form.characteristic.resources.CharacteristicsEndpoints.ATTRIBUTES_NAME;
import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.shared.BaseCrudResource;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic.Attribute;
import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate.Type;
import br.edu.ifpb.upcensus.domain.module.template.service.InputTemplateService;
import br.edu.ifpb.upcensus.infrastructure.domain.EnumOption;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.presentation.module.template.input.mapper.InputTemplateMapper;
import br.edu.ifpb.upcensus.presentation.module.template.input.request.InputTemplateRequest;
import br.edu.ifpb.upcensus.presentation.module.template.input.response.InputTemplateResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;

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

	@GetMapping(InputTemplateEndpoints.INPUT_TEMPLATE_TYPES)
	@ResponseStatus(OK)
	public Response<List<EnumOption<Type>>> listType() {
		List<EnumOption<Type>> attributes = Arrays.stream(Type.values())
			.map(EnumOption::new)
			.collect(Collectors.toList());
		
		return responseService.buildResponse(attributes, OK);
	}
	@GetMapping(InputTemplateEndpoints.INPUT_TEMPLATE_TYPE)
	@ResponseStatus(OK)
	public Response<EnumOption<Attribute>> getTypeByName(@PathVariable String name) {
		Attribute attribute = Attribute.from(name);
		
		return responseService.buildResponse(new EnumOption<>(attribute), OK);
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
