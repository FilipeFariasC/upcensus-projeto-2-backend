package br.edu.ifpb.upcensus.business.form.field.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.form.field.service.FieldService;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.form.FieldRepository;

@Service
public class FieldServiceImpl implements FieldService {

	private final FieldRepository fieldRepository;
	
	public FieldServiceImpl(final FieldRepository fieldRepository) {
		super();
		this.fieldRepository = fieldRepository;
	}

	@Override
	public FieldRepository getRepository() {
		return fieldRepository;
	}

	@Override
	public Class<Field> getDomainClass() {
		return Field.class;
	}

}
