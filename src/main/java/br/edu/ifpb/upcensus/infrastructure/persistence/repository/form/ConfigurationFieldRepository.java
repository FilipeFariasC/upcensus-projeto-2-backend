package br.edu.ifpb.upcensus.infrastructure.persistence.repository.form;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.configuration.model.ConfigurationField;
import br.edu.ifpb.upcensus.infrastructure.persistence.repository.DomainRepository;

public interface ConfigurationFieldRepository extends DomainRepository<ConfigurationField, Long> {
	
	@Query("SELECT field.characteristics "
			+ "FROM ConfigurationField field "
			+ "WHERE field.id = :id and field.configuration.id = :idConfiguration")
	Set<Characteristic> getAllCharacteristicByIdAndConfigurationId(@Param("id") Long id, @Param("idConfiguration") Long idConfiguration);
}