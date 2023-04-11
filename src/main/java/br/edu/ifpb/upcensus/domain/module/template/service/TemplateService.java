package br.edu.ifpb.upcensus.domain.module.template.service;

import java.util.Collection;
import java.util.List;

import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.domain.shared.service.DomainService;

public interface TemplateService extends DomainService<Template, Long> {


	default Template findByCode(String code) {
		return findByProperty("code", code);
	}
	default List<Template> findAllByCode(Collection<String> codes) {
		return findAllByProperty("code", codes);
	}
}
