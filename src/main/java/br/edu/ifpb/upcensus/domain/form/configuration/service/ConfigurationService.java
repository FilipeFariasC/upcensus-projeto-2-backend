package br.edu.ifpb.upcensus.domain.form.configuration.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.domain.form.configuration.model.ConfigurationField;
import br.edu.ifpb.upcensus.domain.shared.service.DomainCodeableService;
import br.edu.ifpb.upcensus.domain.shared.service.DomainService;
import br.edu.ifpb.upcensus.infrastructure.exception.ElementNotFoundException;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.form.ConfigurationFieldRepository;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;

public interface ConfigurationService extends DomainCodeableService<Configuration, Long> {

	ConfigurationFieldRepository getConfigurationFieldRepository();
	CharacteristicService getCharacteristicService();
	
	default Set<ConfigurationField> findAllConfigurationFieldByConfigurationId(Long id) {
		return findById(id).getFields();
	}
	default Set<Characteristic> findAllCharacteristicsByConfigurationIdAndConfigurationFieldId(Long id, Long idField) {
		return getConfigurationFieldRepository()
				.getAllCharacteristicByIdAndConfigurationId(idField, id);
	}
	
	
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
	default Set<Characteristic> addConfigurationFieldCharacteristics(Long id, Long idField, Collection<Long> characteristicIds) {
		Configuration configuration = findById(id);
		
		ConfigurationField field = configuration.getFields()
			.stream()
			.filter(f -> f.getId().equals(idField))
			.findFirst()
			.orElseThrow(() -> new ElementNotFoundException(ConfigurationField.class, idField));
		
		if (CollectionUtils.isEmpty(characteristicIds)) return field.getCharacteristics();
		
		Set<Characteristic> characteristics = CollectionUtils.toCollection(getCharacteristicService().findAllById(characteristicIds), Collectors.toSet());
		
		CollectionUtils.forEach(characteristics, field::addCharacteristic);
		
		return getConfigurationFieldRepository().saveAndFlush(field).getCharacteristics();
	}
	
	default Set<Characteristic> removeConfigurationFieldCharacteristics(Long id, Long idField, Collection<Long> characteristicIds) {
		Configuration configuration = findById(id);
		
		ConfigurationField field = configuration.getFields()
			.stream()
			.filter(f -> f.getId().equals(idField))
			.findFirst()
			.orElseThrow(() -> new ElementNotFoundException(ConfigurationField.class, idField));
		if (CollectionUtils.isEmpty(characteristicIds)) return field.getCharacteristics();
		
		Set<Characteristic> characteristics = CollectionUtils.toCollection(getCharacteristicService().findAllById(characteristicIds), Collectors.toSet());
		
		CollectionUtils.forEach(characteristics, field::removeCharacteristic);
		
		return getConfigurationFieldRepository().saveAndFlush(field).getCharacteristics();
	}
}
