package br.edu.ifpb.upcensus.domain.form.field.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.shared.service.DomainService;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;

public interface FieldService extends DomainService<Field, Long> {
	
	CharacteristicService getCharacteristicService();
	
	default List<Characteristic> addFieldCharacteristics(Long id, Collection<Long> characteristicsIds) {
		Field field = findById(id);
		
		if (CollectionUtils.isEmpty(characteristicsIds)) return field.getCharacteristics();
		List<Characteristic> characteristics = getCharacteristicService().findAllById(characteristicsIds);
		
		List<Characteristic> appended = CollectionUtils.append(field.getCharacteristics(), characteristics, Collectors.toList());
		field.setCharacteristics(appended);
		save(field);
		
		return field.getCharacteristics();
	}
	
	default List<Characteristic> removeFieldCharacteristics(Long id, Collection<Long> characteristicsIds) {
		Field field = findById(id);
		
		if (CollectionUtils.isEmpty(characteristicsIds) || CollectionUtils.isEmpty(field.getCharacteristics())) return field.getCharacteristics();
		
		List<Characteristic> filtered = field.getCharacteristics()
			.stream()
			.filter((n)->characteristicsIds.contains(n.getId()))
			.collect(Collectors.toList());
		field.setCharacteristics(filtered);
		save(field);
		
		return field.getCharacteristics();
	}
	
	default Field findByCode(String code) {
		return findByProperty("code", code);
	}
	default List<Field> findAllByCode(Collection<String> codes) {
		return findAllByProperty("code", codes);
	}
}
