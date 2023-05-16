package br.edu.ifpb.upcensus.domain.form.field.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.shared.service.DomainService;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;

public interface FieldService extends DomainService<PlainField, Long> {
	
	CharacteristicService getCharacteristicService();
	
	default Set<Characteristic> addFieldCharacteristics(Long id, Collection<Long> characteristicsIds) {
		PlainField field = findById(id);
		
		if (CollectionUtils.isEmpty(characteristicsIds)) return field.getCharacteristics();
		List<Characteristic> characteristics = getCharacteristicService().findAllById(characteristicsIds);
		
		Set<Characteristic> appended = CollectionUtils.append(field.getCharacteristics(), characteristics, Collectors.toSet());
		field.setCharacteristics(appended);
		save(field);
		
		return field.getCharacteristics();
	}
	
	default Set<Characteristic> removeFieldCharacteristics(Long id, Collection<Long> characteristicsIds) {
		PlainField field = findById(id);
		
		if (CollectionUtils.isEmpty(characteristicsIds) || CollectionUtils.isEmpty(field.getCharacteristics())) return field.getCharacteristics();
		
		Set<Characteristic> filtered = CollectionUtils.filter(
			field.getCharacteristics(),
			(n)->!characteristicsIds.contains(n.getId()),
			Collectors.toSet());
		field.setCharacteristics(filtered);
		save(field);
		
		return field.getCharacteristics();
	}
	
	default PlainField findByCode(String code) {
		return findByProperty("code", code);
	}
	default List<PlainField> findAllByCode(Collection<String> codes) {
		return findAllByProperty("code", codes);
	}
}
