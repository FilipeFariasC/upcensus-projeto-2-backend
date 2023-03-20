package br.edu.ifpb.upcensus.infrastructure.persistence.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;

@NoRepositoryBean
public interface DomainRepository<E extends DomainModel<I>, I extends Serializable> 
	extends JpaRepository<E, I>, JpaSpecificationExecutor<E>
{
	
}
