package br.edu.ifpb.upcensus.domain.form.field.model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.business.form.shared.exception.FieldTypeBadConfiguredException;
import br.edu.ifpb.upcensus.business.form.shared.exception.FieldTypeNotConfiguredException;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Type;
import br.edu.ifpb.upcensus.domain.shared.exception.InvalidDomainModelException;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;

@Entity
@Table(name = "t_field", schema = "form")
@SequenceGenerator(name = "t_field_id_seq", schema = "form", sequenceName = "t_field_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Campo")
public class Field extends DomainModel<Long> {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_field_id_seq")
	private Long id;
    
    @NotNull
    @Column(name = "code", unique = true)
    @Size(min = 3, max = 128)
    private String code;
    
    @NotNull
    @Size(min = 3, max = 128)
    private String name;

    @Size(max = 512)
    private String description;
    
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
    	name = "t_field_characteristic",
    	schema = "form",
    	joinColumns = @JoinColumn(name = "id_field"),
    	inverseJoinColumns = @JoinColumn(name = "id_characteristic")
    )
    private Set<Characteristic> characteristics;
    

	@Override
	public void initialize() throws InvalidDomainModelException {
		initializeCharacteristics();
	}
	private void initializeCharacteristics() {
		if (CollectionUtils.isEmpty(characteristics))
			this.characteristics = new HashSet<>();
	}

	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Set<Characteristic> getCharacteristics() {
		return characteristics;
	}
	
	public void setCharacteristics(Set<Characteristic> characteristics) {
		this.characteristics = characteristics;
	}
	
	public void addCharacteristic(Characteristic characteristic) {
		getCharacteristics().add(characteristic);
	}

	public void removeCharacteristic(Characteristic characteristic) {
		getCharacteristics().remove(characteristic);
	}

	public Optional<Characteristic> getCharacteristic(Attribute attribute) {
		return getCharacteristics()
			.stream()
			.filter(characteristic -> characteristic.getAttribute().equals(attribute))
			.min(Comparator.comparingLong(Characteristic::getId));
	}
	
	public Type getType() {
		return getCharacteristic(Attribute.TYPE)
			.map(characteristic ->{
				try {
					return Enum.valueOf(Type.class, characteristic.getValue());
				} catch (IllegalArgumentException exception) {
					throw new FieldTypeBadConfiguredException(characteristic.getDescription());
				}
			})
			.orElseThrow(FieldTypeNotConfiguredException::new);
	}
	
	@Override
	public String toString() {
		return String.format(
				"{id: %s, code: \"%s\", name: \"%s\", description: \"%s\", characteristics: %s, creation_time: \"%s\"}", id, code,
				name, description, characteristics, getCreationTime());
	}
	

}
