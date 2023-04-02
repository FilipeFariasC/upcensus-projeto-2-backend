package br.edu.ifpb.upcensus.infrastructure.persistence.repository.form;

import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.DomainRepository;

public interface ConfigurationRepository extends DomainRepository<Configuration, Long> { }
