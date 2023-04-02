package br.edu.ifpb.upcensus.business.form.configuration.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.shared.BaseCrudResource;
import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.domain.form.configuration.service.ConfigurationService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.presentation.form.configuration.mapper.ConfigurationMapper;
import br.edu.ifpb.upcensus.presentation.form.configuration.request.ConfigurationRequest;
import br.edu.ifpb.upcensus.presentation.form.configuration.response.ConfigurationResponse;


@RestController
@RequestMapping(ConfigurationEndpoints.CONFIGURATIONS)
public class ConfigurationResources extends BaseCrudResource<Configuration, Long, ConfigurationRequest, ConfigurationResponse>{

	private final ResponseService responseService;
	private final ConfigurationService configurationService;
	private final ConfigurationMapper configurationMapper;
	
	
	public ConfigurationResources(
		final ResponseService responseService,
		final ConfigurationService configurationService,
		final ConfigurationMapper configurationMapper
	) {
		this.responseService = responseService;
		this.configurationService = configurationService;
		this.configurationMapper = configurationMapper;
	}
	
	@Override
	protected ResponseService getResponseService() {
		return responseService;
	}

	@Override
	protected ConfigurationService getModelService() {
		return configurationService;
	}

	@Override
	protected ConfigurationMapper getDomainMapper() {
		return configurationMapper;
	}

}
