package br.edu.ifpb.upcensus.domain.module.template.service;

import java.util.Collection;
import java.util.List;

import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.domain.shared.service.DomainService;

public interface InputTemplateService extends DomainService<InputTemplate, Long> {


	default InputTemplate findByCode(String code) {
		return findByProperty("code", code);
	}
	default List<InputTemplate> findAllByCode(Collection<String> codes) {
		return findAllByProperty("code", codes);
	}
}
