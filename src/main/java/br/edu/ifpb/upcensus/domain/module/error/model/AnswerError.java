package br.edu.ifpb.upcensus.domain.module.error.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.domain.shared.model.DomainEnum;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;

@Entity
@Table(name = "t_error", schema = "module")
@SequenceGenerator(name = "t_error_id_seq", schema = "module", sequenceName = "t_error_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Erro")
public class AnswerError extends DomainModel<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_error_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_answer")
	@NotNull
	private Answer answer;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Motive motive;

	@NotNull
	@Size(max = 512)
	private String description;

	public static AnswerError of(final Answer answer, Motive motive, String description) {
		final AnswerError error = new AnswerError();

		error.setAnswer(answer);
		error.setMotive(motive);
		error.setDescription(description);

		return error;
	}

	public static AnswerError of(final Answer answer, Motive motive) {
		return of(answer, motive, null);
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Motive getMotive() {
		return motive;
	}

	public void setMotive(Motive motive) {
		this.motive = motive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("{id: %s, answer: \"%s\", motive: \"%s\", description: %s}", id, answer, motive,
				description);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(answer, description, id, motive);
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
		AnswerError other = (AnswerError) obj;
		return Objects.equals(answer, other.answer) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(motive, other.motive);
	}

	@DomainDescriptor(name = "Motivo de erro")
	public enum Motive implements DomainEnum<Motive> {
		ATTRIBUTE("Atributo"), OPTION("Opção"), DEPENDENCY("Dependência"), EXCLUSIVE("Exclusividade"),
		VALUE_CONDITION("Condição de Valor");

		private final String label;

		Motive(final String label) {
			this.label = label;
		}

		@Override
		public String getLabel() {
			return label;
		}

		@Override
		public Motive getValue() {
			return this;
		}

	}
}
