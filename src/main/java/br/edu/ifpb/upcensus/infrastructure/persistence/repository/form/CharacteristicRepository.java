package br.edu.ifpb.upcensus.infrastructure.persistence.repository.form;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.DomainRepository;

public interface CharacteristicRepository extends DomainRepository<Characteristic, Long> { }
