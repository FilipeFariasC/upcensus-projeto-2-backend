package br.edu.ifpb.upcensus.domain.module.module.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Type;
import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.module.error.model.AnswerError;
import br.edu.ifpb.upcensus.domain.module.error.model.AnswerError.Motive;
import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;

@Entity
@Table(
	name = "t_answer", 
	schema = "module", 
	uniqueConstraints = @UniqueConstraint(columnNames = {
		"id_module", 
		"id_template", 
		"id_field"
	})
)
@SequenceGenerator(name = "t_answer_id_seq", schema = "module", sequenceName = "t_answer_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Resposta")
public class Answer extends DomainModel<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_answer_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_module")
	@NotNull
	private Module module;


	@ManyToOne
	@JoinColumn(name = "id_template")
	@NotNull
	private Template template;

	@ManyToOne
	@JoinColumn(name = "id_field")
	@NotNull
	private Field field;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String identifier;

	@NotNull
	@Size(max = 2048)
	private String value;
	
    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AnswerError> errors;

	public static Answer of(final Module module, final Template template, final Field field, final String identifier, final String value) {
		final Answer answer = new Answer();
		
		answer.setModule(module);
		answer.setTemplate(template);
		answer.setField(field);
		answer.setIdentifier(identifier);
		answer.setValue(value);
		
		return answer;
	}
	@Override
	public void initialize() {
		super.initialize();
		initializeErrors();
	}
	
	private void initializeErrors() {
		if (CollectionUtils.isEmpty(errors))
			this.errors = new ArrayList<>();
	}
	
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}

	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}

	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public List<AnswerError> getErrors() {
		return errors;
	}

	public void setErrors(List<AnswerError> errors) {
		this.errors = errors;
	}
	
	public void addError(AnswerError error) {
		initializeErrors();
		error.register();
		getErrors().add(error);
	}
	public void addError(Motive motive, String description) {
		addError(AnswerError.of(this, motive, description));
	}
	public void addError(Motive motive) {
		addError(AnswerError.of(this, motive, null));
	}
	

	public Type getType() {
		Optional<Type> opt = getModule().getType(getField());
		
		return opt.orElseGet(getField()::getType);
	}
	
	public Optional<Characteristic> getCharacteristic(Attribute attribute) {
		Optional<Characteristic> opt = getModule().getCharacteristic(field, attribute);
		if (opt.isPresent())
			return opt;
		return field.getCharacteristic(attribute);
	}
	
	
	private <T> T getMappedValue(Function<String, T> mapper) {
		try {
			return mapper.apply(getValue());
		} catch (Exception exception) {
			throw new IllegalStateException(exception);
		}
	}
	
	/*
	 * 
	 */
	
	public LocalDate getValueAsDate() {
		return getMappedValue(LocalDate::parse);
	}
	
	public LocalDateTime getValueAsTimestamp() {
		return getMappedValue(LocalDateTime::parse);
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
	
	

	@Override
	public String toString() {
		return String.format("{id: %s, field: %s, identifier: \"%s\", value: \"%s\"}", id, field.getCode(), identifier, value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(field, id, identifier, module, template, value);
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
		Answer other = (Answer) obj;
		return Objects.equals(field, other.field) && Objects.equals(id, other.id)
				&& Objects.equals(identifier, other.identifier) && Objects.equals(module, other.module)
				&& Objects.equals(template, other.template) && Objects.equals(value, other.value);
	}
	
	

}
