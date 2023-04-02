package br.edu.ifpb.upcensus.business.form.configuration.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.domain.form.configuration.service.ConfigurationService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.form.ConfigurationFieldRepository;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.form.ConfigurationRepository;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	private final ConfigurationRepository configurationRepository;
	private final ConfigurationFieldRepository configurationFieldRepository;
	
	
	public ConfigurationServiceImpl(
		final ConfigurationRepository configurationRepository,
		final ConfigurationFieldRepository configurationFieldRepository
	) {
		this.configurationRepository = configurationRepository;
		this.configurationFieldRepository = configurationFieldRepository;
	}
	
	
	
	@Override
	public ConfigurationRepository getRepository() {
		return configurationRepository;
	}

	@Override
	public Class<Configuration> getDomainClass() {
		return Configuration.class;
	}



	@Override
	public ConfigurationFieldRepository getConfigurationFieldRepository() {
		return configurationFieldRepository;
	}

}
