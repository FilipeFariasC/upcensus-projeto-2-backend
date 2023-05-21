package br.edu.ifpb.upcensus.infrastructure.persistence.repository.form;

import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.DomainRepository;

public interface FieldRepository extends DomainRepository<PlainField, Long> { }
