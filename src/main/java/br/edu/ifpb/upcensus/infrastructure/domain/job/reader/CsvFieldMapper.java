package br.edu.ifpb.upcensus.infrastructure.domain.job.reader;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.infrastructure.util.NumberUtils;

public class CsvFieldMapper extends FieldMappingReader<Integer> implements FieldSetMapper<Map<String,String>>{

	public CsvFieldMapper(final InputTemplate template) {
		super(template, NumberUtils::convertToInteger);
	}

	@Override
	public Map<String, String> mapFieldSet(FieldSet fieldSet) throws BindException {
		return getMappings().entrySet()
			.stream()
			.collect(
				Collectors.toMap(entry -> entry.getKey().getCode(), entry -> getFieldValue(fieldSet, entry.getValue()))
			);
	}
	
	private String getFieldValue(final FieldSet fieldSet, final Integer index) {
		if (index > fieldSet.getFieldCount()) return "";
		
		return fieldSet.readString(index-1);
	}

}
