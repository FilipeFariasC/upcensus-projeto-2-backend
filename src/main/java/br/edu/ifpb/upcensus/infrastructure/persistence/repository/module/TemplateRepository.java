package br.edu.ifpb.upcensus.infrastructure.persistence.repository.module;

import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.DomainRepository;

public interface TemplateRepository extends DomainRepository<Template, Long> { }
