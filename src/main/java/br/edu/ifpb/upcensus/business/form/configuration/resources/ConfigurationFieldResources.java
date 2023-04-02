package br.edu.ifpb.upcensus.business.form.configuration.resources;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.shared.ApiEndpoints;
import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.domain.form.configuration.model.ConfigurationField;
import br.edu.ifpb.upcensus.domain.form.configuration.service.ConfigurationService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.presentation.configuration.mapper.ConfigurationFieldMapper;
import br.edu.ifpb.upcensus.presentation.configuration.request.ConfigurationFieldRequest;
import br.edu.ifpb.upcensus.presentation.configuration.response.ConfigurationConfigurationFieldResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;


@RestController
@RequestMapping(ConfigurationEndpoints.CONFIGURATION_FIELDS)
public class ConfigurationFieldResources {

	private final ResponseService responseService;
	private final ConfigurationService configurationService;
	private final ConfigurationFieldMapper configurationFieldMapper;
	
	
	public ConfigurationFieldResources(
		final ResponseService responseService,
		final ConfigurationService configurationService,
		final ConfigurationFieldMapper configurationFieldMapper
	) {
		this.responseService = responseService;
		this.configurationService = configurationService;
		this.configurationFieldMapper = configurationFieldMapper;
	}
	

	@GetMapping
	@ResponseStatus(OK)
	public Response<Set<ConfigurationConfigurationFieldResponse>> getFieldConfigurationFields(
			@PathVariable Long id
		) {
		Configuration registered = configurationService.findById(id);
		Set<ConfigurationConfigurationFieldResponse> response = configurationFieldMapper.modelSetToConfigurationResponseSet(registered.getFields());
		
		return responseService.buildResponse(response, OK);
	}
	
	@PatchMapping(ApiEndpoints.ADD)
	@ResponseStatus(OK)
	public Response<Set<ConfigurationConfigurationFieldResponse>> addConfigurationFields(
			@PathVariable Long id, 
			@RequestBody Set<ConfigurationFieldRequest> fieldRequests
		) {
		Set<ConfigurationField> fields = configurationFieldMapper.requestSetToModelSet(fieldRequests);
		Set<ConfigurationField> configurationFields= configurationService.addConfigurationFields(id, fields);
		Set<ConfigurationConfigurationFieldResponse> response = configurationFieldMapper.modelSetToConfigurationResponseSet(configurationFields);
		
		return responseService.buildResponse(response, OK);
	}
	
	@PatchMapping(ApiEndpoints.REMOVE)
	@ResponseStatus(OK)
	public Response<Set<ConfigurationConfigurationFieldResponse>> removeConfigurationFields(
			@PathVariable Long id, 
			@RequestBody List<Long> ids
		) {
		Set<ConfigurationField> configurationFields= configurationService.removeConfigurationFields(id, ids);
		Set<ConfigurationConfigurationFieldResponse> response = configurationFieldMapper.modelSetToConfigurationResponseSet(configurationFields);
		
		return responseService.buildResponse(response, OK);
	}

}
