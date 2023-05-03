package br.edu.ifpb.upcensus.domain.module.module.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;

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
