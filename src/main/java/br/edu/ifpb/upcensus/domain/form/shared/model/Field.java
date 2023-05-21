package br.edu.ifpb.upcensus.domain.form.shared.model;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic.Attribute;
import br.edu.ifpb.upcensus.domain.form.field.model.Type;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;

public interface Field {
	
	Type getType();
	
	boolean isRequired();
	
	Set<Characteristic> getCharacteristics();
	
	default void addCharacteristic(Characteristic characteristic) {
		getCharacteristics().add(characteristic);
	}

	default void removeCharacteristic(Characteristic characteristic) {
		getCharacteristics().remove(characteristic);
	}
	
	
	
	default Optional<Characteristic> getCharacteristic(Attribute attribute) {
		if (CollectionUtils.isEmpty(getCharacteristics()))
			return Optional.empty();
		
		return getCharacteristics()
			.stream()
			.filter(characteristic -> characteristic.isAttribute(attribute))
			.max(Comparator.comparingLong(Characteristic::getId));
	}

	default boolean hasCharacteristic(Attribute attribute) {
		if (CollectionUtils.isEmpty(getCharacteristics()))
			return false;
		
		return getCharacteristics()
			.stream()
			.anyMatch(characteristic -> characteristic.isAttribute(attribute));
	}
	
	
}
