package br.edu.ifpb.upcensus.domain.form.characteristic.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.persistence.Column;
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

import com.fasterxml.jackson.annotation.JsonCreator;

import br.edu.ifpb.upcensus.domain.shared.model.DomainEnum;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.exception.ElementNotFoundException;

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
    @Column(nullable = false)
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

	private <T> T getMappedValue(Function<String, T> mapper) {
		try {
			return mapper.apply(getValue());
		} catch (Exception exception) {
			throw new IllegalStateException(exception);
		}
	}
	
	public Boolean getValueAsBoolean() {
		return getMappedValue(Boolean::valueOf);
	}
	public Integer getValueAsInteger() {
		return getMappedValue(Integer::valueOf);
	}
	public Double getValueAsDouble() {
		return getMappedValue(Double::valueOf);
	}
	public BigDecimal getValueAsBigDecimal() {
		return getMappedValue(BigDecimal::new);
	}
	public Pattern getValueAsPattern() {
		return getMappedValue(Pattern::compile);
	}
	
	public boolean isAttribute(Attribute attribute) {
		return getAttribute().equals(attribute);
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
		return String.format("{id: %s, attribute: %s, value: %s, description: %s, creation_time: %s}", id,
				attribute, value, description, getCreationTime());
	}
	
	@DomainDescriptor(name = "Atributo")
	public enum Attribute implements DomainEnum<Attribute> {
		MIN_LENGTH("Tamanho mínimo"),
		MAX_LENGTH("Tamanho máximo"),
		MIN_VALUE("Valor mínimo"),
		MAX_VALUE("Valor máximo"),
		PATTERN("Padrão");
		
		private final String label;
		
		Attribute(String label) {
			this.label = label;
		}

		@Override
		public String getLabel() {
			return label;
		}

		@Override
		public Attribute getValue() {
			return this;
		}
		
		@JsonCreator
		public static Attribute from(String attribute) {
			return Stream.of(Attribute.values())
				.filter(attr-> attr.name().equals(attribute))
				.findFirst()
				.orElseThrow(()-> new ElementNotFoundException(Attribute.class, attribute));
		}

	}
	
	

}
