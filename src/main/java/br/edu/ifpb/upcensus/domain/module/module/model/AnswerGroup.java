package br.edu.ifpb.upcensus.domain.module.module.model;

import java.util.List;
import java.util.Optional;

import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;

@DomainDescriptor(name = "Grupo de Respostas")
public class AnswerGroup {
	private final List<Answer> answers;

	public AnswerGroup(List<Answer> answers) {
		super();
		this.answers = answers;
	}
	
	public static AnswerGroup of(final List<Answer> answers) {
		return new AnswerGroup(answers);
	}
	
	public Optional<Answer> findAnswer(String fieldCode) {
		if (CollectionUtils.isEmpty(answers))
			return Optional.empty();
		
		return getAnswers()
			.stream()
			.filter(answer -> answer.isFieldCode(fieldCode))
			.findFirst();
	}
	
	public Answer getAnswer(String fieldCode) {
		return findAnswer(fieldCode).orElse(null);
	}
	
	public Optional<String> findAnswerValue(String fieldCode) {
		return findAnswer(fieldCode)
			.map(Answer::getValue);
	}
	
	public String getAnswerValue(String fieldCode) {
		return findAnswerValue(fieldCode)
			.orElse(null);
	}


	public List<Answer> getAnswers() {
		return answers;
	}
	
}
