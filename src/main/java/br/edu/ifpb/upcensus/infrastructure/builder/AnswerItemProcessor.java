package br.edu.ifpb.upcensus.infrastructure.builder;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;

import br.edu.ifpb.upcensus.business.form.shared.pipeline.ValidationPipeline;
import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.template.model.Template;

public class AnswerItemProcessor implements ItemProcessor<Map<String, String>, Set<Answer>> {
	
	private final Module module;
	private final Template template;
	private final ValidationPipeline validationPipeline;
	
	public AnswerItemProcessor(
		final Module module, 
		final Template template,
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
		Set<Answer> validated = validate(mapped);
		return validated;
	}
	
	private Set<Answer> mapToAnswers(Map<String, String> map) {
		final String codeIdentifier = template.getFieldIdentifier().getCode();

		final String identifier = map.get(codeIdentifier);
		
		return map.entrySet()
			.stream()
			.map(entry -> mapToAnswer(identifier, entry.getKey(), entry.getValue()))
			.collect(Collectors.toSet());
	}
	
	private Answer mapToAnswer(String identifier, String key, String value) {
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
