package br.edu.ifpb.upcensus.domain.form.configuration.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic.Attribute;
import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.form.field.model.Type;
import br.edu.ifpb.upcensus.domain.form.shared.model.Field;
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
public class ConfigurationField implements Serializable, Field {
	
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
    private PlainField field;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = true)
    private Type type;
    
    @Column(name = "required", nullable = true)
    private Boolean required;

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

	public PlainField getField() {
		return field;
	}
	public void setField(PlainField field) {
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
	
	public Set<Characteristic> getAllCharacteristics() {
		
		Stream<Characteristic> configurationCharacteristics = filterCharacteristics(getCharacteristics());
		Set<Attribute> attributes = getAttributes(getCharacteristics());
		Stream<Characteristic> fieldCharacteristics = filterCharacteristics(field.getCharacteristics())
				.filter(characteristic -> !attributes.contains(characteristic.getAttribute()));
		
		return Stream
			.concat(fieldCharacteristics, configurationCharacteristics)
			.collect(Collectors.toSet());
	}
	
	private Stream<Characteristic> filterCharacteristics(Set<Characteristic> characteristics) {
		return characteristics
			.stream()
			.collect(Collectors.groupingBy(Characteristic::getAttribute))
			.values()
			.stream()
			.map(list -> list.get(0));
	}
	
	private Set<Attribute> getAttributes(Set<Characteristic> characteristics) {
		return characteristics
			.stream()
			.map(Characteristic::getAttribute)
			.collect(Collectors.toSet());
	}
	
	public boolean isRequired() {
		return Boolean.TRUE.equals(required);
	}
	
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Optional<Characteristic> getCharacteristic(Attribute attribute) {
		return getAllCharacteristics()
			.stream()
			.filter(characteristic -> characteristic.getAttribute().equals(attribute))
			.min(Comparator.comparingLong(Characteristic::getId));
	}
	
//	public Type getType() {
//		return getAllCharacteristics()
//			.stream()
//			.filter(characteristic -> characteristic.getAttribute().equals(Attribute.TYPE))
//			.findFirst()
//			.map(characteristic ->{
//				try {
//					return Enum.valueOf(Type.class, characteristic.getValue());
//				} catch (IllegalArgumentException exception) {
//					throw new FieldTypeBadConfiguredException(characteristic.getDescription());
//				}
//			})
//			.orElseThrow(()-> new FieldTypeNotConfiguredException());
//	}
	
	
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
