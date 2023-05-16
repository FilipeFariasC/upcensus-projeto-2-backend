package br.edu.ifpb.upcensus.business.form.field.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.upcensus.business.form.characteristic.service.CharacteristicServiceImpl;
import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.form.field.service.FieldService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.form.FieldRepository;

@Service
public class FieldServiceImpl implements FieldService {

	private final FieldRepository fieldRepository;
	private final CharacteristicService characteristicService;
	
	public FieldServiceImpl(
		final FieldRepository fieldRepository,
		final CharacteristicServiceImpl characteristicService
		) {
		super();
		this.fieldRepository = fieldRepository;
		this.characteristicService = characteristicService;
	}

	@Override
	public FieldRepository getRepository() {
		return fieldRepository;
	}

	@Override
	public Class<PlainField> getDomainClass() {
		return PlainField.class;
	}

	@Override
	public CharacteristicService getCharacteristicService() {
		return characteristicService;
	}

}
