package br.edu.ifpb.upcensus.domain.module.template.service;

import java.util.Collection;
import java.util.List;

import br.edu.ifpb.upcensus.domain.module.template.model.OutputTemplate;
import br.edu.ifpb.upcensus.domain.shared.service.DomainService;

public interface OutputTemplateService extends DomainService<OutputTemplate, Long> {


	default OutputTemplate findByCode(String code) {
		return findByProperty("code", code);
	}
	default List<OutputTemplate> findAllByCode(Collection<String> codes) {
		return findAllByProperty("code", codes);
	}
}
