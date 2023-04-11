package br.edu.ifpb.upcensus.infrastructure.domain.job.reader;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.module.template.exception.IllegalTemplateArgumentException;
import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.infrastructure.util.NumberUtils;

public abstract class FieldMappingReader<R> {
	private final Template template;
	private final Function<String, R> fieldMapper;
	
	private final Map<Field, R> mappings;

	public FieldMappingReader(final Template template, final Function<String, R> fieldMapper) {
		super();
		this.template = template;
		this.fieldMapper = fieldMapper;
		this.mappings = template.getMappings()
			.entrySet()
			.stream()
			.collect(Collectors.toMap(
				entry -> entry.getKey(),
				entry -> {
					String index = entry.getValue();
					if (!NumberUtils.isPositiveDecimal(index)) {
						throw new IllegalTemplateArgumentException(entry.getKey().getCode(), entry.getValue());
					}
					return fieldMapper.apply(index);
				}
			));
	}
	
	public Map<Field, R> getMappings() {
		return mappings;
	}

	public Template getTemplate() {
		return template;
	}

	public Function<String, R> getFieldMapper() {
		return fieldMapper;
	}
}
