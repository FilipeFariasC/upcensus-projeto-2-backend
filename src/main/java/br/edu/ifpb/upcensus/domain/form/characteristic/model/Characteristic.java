package br.edu.ifpb.upcensus.domain.form.characteristic.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;

@Entity
@Table(name = "t_characteristic", schema = "form")
@SequenceGenerator(name = "t_characteristic_id_seq", schema = "form", sequenceName = "t_characteristic_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Característica")
public class Characteristic extends DomainModel<Long> {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_characteristic_id_seq")
	private Long id;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Attribute attribute;
    
    @NotNull
    @NotBlank
    @Size(max = 128)
    private String value;
    
    @Size(max = 512)
    private String description;
    

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}


	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, attribute, value, description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Characteristic other = (Characteristic) obj;
		return attribute == other.attribute && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return String.format("{id: %s, attribute: %s, value: %s, description: %s, getCreationTime(): %s}", id,
				attribute, value, description, getCreationTime());
	}
	
	
	

}
