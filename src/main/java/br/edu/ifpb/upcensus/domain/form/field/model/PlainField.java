package br.edu.ifpb.upcensus.domain.form.field.model;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.shared.model.Field;
import br.edu.ifpb.upcensus.domain.shared.exception.InvalidDomainModelException;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;

@Entity
@Table(name = "t_field", schema = "form")
@SequenceGenerator(name = "t_field_id_seq", schema = "form", sequenceName = "t_field_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Campo")
public class PlainField extends DomainModel<Long> implements Field {
	
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
    

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type = Type.PLAIN_TEXT;
    
    @NotNull
    @Column(nullable = false)
    private boolean required = false;
    
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
	
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return String.format(
				"{id: %s, code: \"%s\", name: \"%s\", description: \"%s\", characteristics: %s, creation_time: \"%s\"}", id, code,
				name, description, characteristics, getCreationTime());
	}
	

}
