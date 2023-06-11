package br.edu.ifpb.upcensus.domain.shared.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;

public interface DomainCodeableService <M extends DomainModel<I>, I extends Serializable> extends DomainService<M, I> {
	
	default M findByCode(String code) {
		return findByProperty("code", code);
	}
	default List<M> findAllByCode(Collection<String> codes) {
		return findAllByProperty("code", codes);
	}
}
