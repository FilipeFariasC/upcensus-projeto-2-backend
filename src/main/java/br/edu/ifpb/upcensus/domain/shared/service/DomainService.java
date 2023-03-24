package br.edu.ifpb.upcensus.domain.shared.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import br.edu.ifpb.upcensus.domain.shared.exception.InvalidDomainModelException;
import br.edu.ifpb.upcensus.domain.shared.exception.ResourceNotFoundException;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.DomainRepository;
import br.edu.ifpb.upcensus.infrastructure.util.BeanUtils;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;

public interface DomainService<M extends DomainModel<I>, I extends Serializable> {

	DomainRepository<M, I> getRepository();
	Class<M> getDomainClass();
	
	default M findById(I id) {
		Optional<M> entity = getRepository().findById(id);
		
		return entity.orElseThrow(()-> new ResourceNotFoundException(getDomainClass()));
	}
	
	default List<M> findAllById(Collection<I> ids) {
		if (CollectionUtils.isEmpty(ids)) return CollectionUtils.emptyList();
		List<M> entities = getRepository().findAllById(ids);
		
		if (CollectionUtils.notEmpty(entities)) return entities;
		
		throw new ResourceNotFoundException(getDomainClass(), "id", ids);
	}
	default List<M> findAllById(Iterable<I> ids) {
		if (CollectionUtils.isEmpty(ids)) return CollectionUtils.emptyList();
		List<M> entities = getRepository().findAllById(ids);
		
		if (CollectionUtils.notEmpty(entities)) return entities;
		
		throw new ResourceNotFoundException(getDomainClass(), "id", ids);
	}
	
	default M findByProperty(final String property, final Object value) {
		Optional<M> entity = getRepository()
			.findOne(Specification.where((root, query, builder) -> builder.equal(root.get(property), value)));
			
		return entity.orElseThrow(()->new ResourceNotFoundException(getDomainClass(), property, value));
	}

	default List<M> findAllByProperty(final String property, final Collection<? extends Object> value) {
		if (CollectionUtils.isEmpty(value)) return CollectionUtils.emptyList();
		
		List<M> entities = getRepository()
			.findAll(Specification.where((root, query, builder) -> builder.equal(root.get(property), value)));
		
		if (entities.isEmpty()) throw new ResourceNotFoundException(getDomainClass(), property, value);
		
		return entities;
	}
	
	default Page<M> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}
	
	default List<M> findAll(Specification<M> specification) {
		return getRepository().findAll(specification);
	}

	default List<M> findAll() {
		return getRepository().findAll();
	}
	

	default M save(M entity) {
		validate(entity);
		return getRepository().save(entity);
	}
	default M saveAndFlush(M entity) {
		validate(entity);
		return getRepository().saveAndFlush(entity);
	}
	default List<M> saveAll(Collection<M> entities) {
		validateAll(entities);
		return getRepository().saveAll(entities);
	}
	
	default M register(M entity) {
		entity.register();
		return save(entity);
	}
	default List<M> registerAll(Collection<M> entities) {
		return CollectionUtils.map(entities, this::register, Collectors.toList());
	}
	
	default M update(I id, M entity) {
		M saved = findById(id);
		M updated = BeanUtils.copyProperties(entity, saved);
		return save(updated);
	}
	
	default void validate(M entity) {
		if (ObjectUtils.isNull(entity)) {
			throw new InvalidDomainModelException(getDomainClass());
		}
		entity.validate();
	}
	default void validateAll(Collection<M> entities) {
		CollectionUtils.forEach(entities, this::validate);
	}
}
