package br.edu.ifpb.upcensus.infrastructure.domain.job.reader;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.infrastructure.util.NumberUtils;

public class XlsxFieldMapper extends FieldMappingReader<Integer> implements RowMapper<Map<String,String>>{

	public XlsxFieldMapper(final Template template) {
		super(template, NumberUtils::convertToInteger);
	}

	@Override
	public Map<String, String> mapRow(RowSet rowSet) throws Exception {
		if (!Optional.ofNullable(rowSet).map(RowSet::getCurrentRow).isPresent()) return Collections.emptyMap();
		
		final String[] rowValues = rowSet.getCurrentRow();
		return getMappings().entrySet()
				.stream()
				.collect(
					Collectors.toMap(entry -> entry.getKey().getCode(), entry-> getFieldValue(rowValues, entry.getValue()))
				);
	}

	private String getFieldValue(final String[] rowValues, final Integer columnIndex) {
		if (columnIndex >= rowValues.length) return null;
		return rowValues[columnIndex];
	}
	
}
