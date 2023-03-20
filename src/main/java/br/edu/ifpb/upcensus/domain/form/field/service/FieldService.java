package br.edu.ifpb.upcensus.domain.form.field.service;

import java.util.Collection;
import java.util.List;

import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.shared.service.DomainService;

public interface FieldService extends DomainService<Field, Long> {
	default Field findByCode(String code) {
		return findByProperty("code", code);
	}
	default List<Field> findAllByCode(Collection<String> codes) {
		return findAllByProperty("code", codes);
	}
}
