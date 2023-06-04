package br.edu.ifpb.upcensus.infrastructure.builder;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.batch.item.ItemProcessor;

import br.edu.ifpb.upcensus.business.form.shared.pipeline.ValidationPipeline;
import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;

public class AnswerItemProcessor implements ItemProcessor<Map<String, String>, Set<Answer>> {
	
	private final Module module;
	private final InputTemplate template;
	private final ValidationPipeline validationPipeline;
	
	public AnswerItemProcessor(
		final Module module, 
		final InputTemplate template,
		final ValidationPipeline validationPipeline
	) {
		super();
		this.module = module;
		this.template = template;
		this.validationPipeline = validationPipeline;
	}

	@Override
	public Set<Answer> process(Map<String, String> item) throws Exception {
		Set<Answer> mapped = mapToAnswers(item);
		return validate(mapped);
	}
	
	private Set<Answer> mapToAnswers(Map<String, String> map) {
		final String codeIdentifier = module.getConfiguration().getIdentifierField().getCode();
		final String identifierValue = map.get(codeIdentifier);
		

		final Answer identifier = module.getAnswer(null, template.getFieldFromCode(codeIdentifier), identifierValue)
			.orElseGet(() -> mapToAnswer(null, codeIdentifier, identifierValue));
		
		final Stream<Answer> answers = map.entrySet()
			.stream()
			.filter(entry -> !entry.getKey().equals(codeIdentifier))
			.map(entry -> mapToAnswer(identifier, entry.getKey(), entry.getValue()));
		
		return Stream.concat(
				Stream.of(identifier), 
				answers
			)
			.filter(answer -> !(module.hasAnswer(answer)))
			.collect(Collectors.toSet());
	}
	
	private Answer mapToAnswer(Answer identifier, String key, String value) {
		final PlainField field = template.getFieldFromCode(key);
		final Answer answer = Answer.of(module, template, field, identifier, value);
		answer.register();
		return answer;
	}
	
	private Set<Answer> validate(Set<Answer> answers) {
		validationPipeline.validate(answers);
		return answers; // TODO
	}
	
	
}
