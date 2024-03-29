package br.edu.ifpb.upcensus.domain.form.configuration.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic.Attribute;
import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.form.field.model.Type;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;

@Entity
@Table(name = "t_configuration", schema = "form")
@SequenceGenerator(name = "t_configuration_id_seq", schema = "form", sequenceName = "t_configuration_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Configuração")
public class Configuration extends DomainModel<Long> {
	
	private static final long serialVersionUID = 1L;
		
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_configuration_id_seq")
	private Long id;
    
    @NotNull
    @Column(name = "code", unique = true)
    @Size(min = 3, max = 128)
    private String code;
    
    @NotNull
    @Size(min = 3, max = 128)
    private String name;
    
    @OneToMany(
    	fetch = FetchType.LAZY, 
    	mappedBy = "configuration", 
    	cascade = CascadeType.ALL,//{CascadeType.PERSIST, CascadeType.MERGE},
    	orphanRemoval = true
	)
    private Set<ConfigurationField> fields;
 
	@Override
	public void initialize() {
		initializeFields();
	}
	private void initializeFields() {
		if (CollectionUtils.isEmpty(getFields()))
			this.fields = new HashSet<>();
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


	public Set<ConfigurationField> getFields() {
		return fields;
	}
	public void addField(ConfigurationField field) {
		getFields().add(field);
		field.setConfiguration(this);
	}
	public void removeField(ConfigurationField field) {
		field.setConfiguration(null);	
		getFields().remove(field);
	}
	
	public void setFields(Set<ConfigurationField> fields) {
		if (CollectionUtils.isEmpty(getFields())) {
			this.setupFields(fields);
			this.fields = fields;
			return;
		}
			
		getFields().clear();
		if (ObjectUtils.nonNull(fields)) {
			setupFields(fields);
			getFields().retainAll(fields);
			getFields().addAll(fields);
		}
	}
	
	private void setupFields(Set<ConfigurationField> fields) {
		fields.forEach(field -> field.setConfiguration(this));
	}
	
	
	public Optional<Characteristic> getAttribute(PlainField field, Attribute attribute) {
		return getFields()
			.stream()
			.filter(configurationField -> configurationField.getField().equals(field))
			.findFirst()
			.flatMap(configurationField -> configurationField.getCharacteristic(attribute));
	}
	
	public Optional<Type> getType(PlainField field) {
		if (CollectionUtils.isEmpty(getFields()))
			return Optional.empty();
		
		return getFields()
			.stream()
			.filter(configurationField -> configurationField.getField().equals(field))
			.map(ConfigurationField::getType)
			.map(Optional::ofNullable)
			.findFirst()
			.flatMap(Function.identity());
	}
	
	public Optional<Boolean> getRequired(PlainField field) {
		if (CollectionUtils.isEmpty(getFields()))
			return Optional.empty();
		
		return getFields()
			.stream()
			.filter(configurationField -> configurationField.getField().equals(field))
			.map(ConfigurationField::getRequired)
			.map(Optional::ofNullable)
			.findFirst()
			.flatMap(Function.identity());
	}
	
	public boolean hasCharacteristic(PlainField field, Attribute attribute) {
		if (CollectionUtils.isEmpty(getFields()))
			return false;
		
		return getFields()
			.stream()
			.filter(obj -> obj.getField().equals(field))
			.anyMatch(obj -> obj.hasCharacteristic(attribute));
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(code, id, name);
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
		Configuration other = (Configuration) obj;
		return Objects.equals(code, other.code)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return String.format("{id: %s, code: %s, name: %s, fields: %s}", id, code, name, fields);
	}
	

}
