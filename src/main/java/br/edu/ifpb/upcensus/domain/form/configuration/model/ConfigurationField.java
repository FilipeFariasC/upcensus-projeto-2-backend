package br.edu.ifpb.upcensus.domain.form.configuration.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;

@Entity
@Table(
	name = "t_configuration_field", 
	schema = "form", 
	uniqueConstraints = @UniqueConstraint(columnNames = {
		"id_configuration", 
		"id_field"
	})
)
@SequenceGenerator(name = "t_configuration_field_id_seq", schema = "form", sequenceName = "t_configuration_field_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Campo da Configuração")
public class ConfigurationField implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_configuration_field_id_seq")
	private Long id;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "id_configuration")
    private Configuration configuration;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "id_field")
    private Field field;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
    	name = "t_configuration_field_characteristic",
    	schema = "form",
    	joinColumns = @JoinColumn(name = "id_configuration_field"),
    	inverseJoinColumns = @JoinColumn(name = "id_characteristic")
    )
    private Set<Characteristic> characteristics;
    
    
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

	public Set<Characteristic> getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(Set<Characteristic> characteristics) {
		if (CollectionUtils.isEmpty(getCharacteristics())) {
			this.characteristics = characteristics;
			return;
		}
		getCharacteristics().clear();
		if (CollectionUtils.notEmpty(characteristics)) {
			getCharacteristics().retainAll(characteristics);
			getCharacteristics().addAll(characteristics);
		}
	}
	
	public void addCharacteristic(Characteristic characteristic) {
		getCharacteristics().add(characteristic);
	}
	public void removeCharacteristic(Characteristic characteristic) {
		getCharacteristics().remove(characteristic);
	}
	
	@Override
	public String toString() {
		return String.format("{id: %s, field: %s, characteristics: %s}", id, field,
				characteristics);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(configuration, characteristics, field, id);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigurationField other = (ConfigurationField) obj;
		return Objects.equals(characteristics, other.characteristics)
				&& Objects.equals(configuration, other.configuration) && Objects.equals(field, other.field)
				&& Objects.equals(id, other.id);
	}
	
	
}
