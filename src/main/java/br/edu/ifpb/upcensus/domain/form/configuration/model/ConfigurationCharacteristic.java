package br.edu.ifpb.upcensus.domain.form.configuration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;

@Entity
@Table(
	name = "t_configuration_characteristic", 
	schema = "form", 
	uniqueConstraints = @UniqueConstraint(columnNames = {
		"id_configuration", 
		"id_field",
		"id_characteristic"
	})
)
@SequenceGenerator(name = "t_configuration_characteristic_id_seq", schema = "form", sequenceName = "t_configuration_characteristic_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Característica do Campo da Configuração")
public class ConfigurationCharacteristic extends DomainModel<Long> {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_configuration_characteristic_id_seq")
	private Long id;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "id_configuration")
    private Configuration configuration;
    
    @NotNull
    @Column(name = "id_field")
    @JoinColumn(name = "id_field")
    private Field field;
    
    @NotNull
    @Column(name = "id_characteristic")
    @JoinColumn(name = "id_characteristic")
    private Characteristic characteristic;

	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}

	public Characteristic getCharacteristic() {
		return characteristic;
	}
	public void setCharacteristic(Characteristic characteristic) {
		this.characteristic = characteristic;
	}
	
	
	@Override
	public String toString() {
		return String.format("{id: %s, configuration: %s, field: %s, characteristic: %s}", id, configuration, field,
				characteristic);
	}
	

}
