package br.edu.ifpb.upcensus.business.form.configuration.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.domain.form.configuration.service.ConfigurationService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.form.ConfigurationFieldRepository;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.form.ConfigurationRepository;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	private final ConfigurationRepository configurationRepository;
	private final ConfigurationFieldRepository configurationFieldRepository;
	private final CharacteristicService characteristicService;
	
	
	public ConfigurationServiceImpl(
		final ConfigurationRepository configurationRepository,
		final ConfigurationFieldRepository configurationFieldRepository,
		final CharacteristicService characteristicService
	) {
		this.configurationRepository = configurationRepository;
		this.configurationFieldRepository = configurationFieldRepository;
		this.characteristicService = characteristicService;
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

	@Override
	public CharacteristicService getCharacteristicService() {
		return characteristicService;
	}

}
