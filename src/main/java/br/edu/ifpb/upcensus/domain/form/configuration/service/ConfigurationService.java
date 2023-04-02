package br.edu.ifpb.upcensus.domain.form.configuration.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.domain.form.configuration.model.ConfigurationField;
import br.edu.ifpb.upcensus.domain.shared.service.DomainService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.form.ConfigurationFieldRepository;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;

public interface ConfigurationService extends DomainService<Configuration, Long> {
	
	ConfigurationFieldRepository getConfigurationFieldRepository();
	
	default Set<ConfigurationField> addConfigurationFields(Long id, Collection<ConfigurationField> fields) {
		Configuration configuration = findById(id);
		
		if (CollectionUtils.isEmpty(fields)) return configuration.getFields();
		CollectionUtils.forEach(fields, configuration::addField);
		
		return save(configuration).getFields();
	}
	
	default Set<ConfigurationField> removeConfigurationFields(Long id, Collection<Long> fieldIds) {
		Configuration configuration = findById(id);
		
		if (CollectionUtils.isEmpty(fieldIds) || CollectionUtils.isEmpty(configuration.getFields())) return configuration.getFields();
		
		Set<ConfigurationField> fields = CollectionUtils.toCollection(
			getConfigurationFieldRepository().findAllById(fieldIds),
			Collectors.toSet()
		);
		configuration.setFields(fields);
		
		return saveAndFlush(configuration).getFields();
	}
}
