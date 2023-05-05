package br.edu.ifpb.upcensus.infrastructure.builder;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.infrastructure.domain.job.reader.CsvFieldMapper;
import br.edu.ifpb.upcensus.infrastructure.domain.job.reader.XlsxFieldMapper;
import br.edu.ifpb.upcensus.infrastructure.util.NumberUtils;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;

public class ItemReaderBuilder {
	
	private static final String CSV_DELIMITER = ",";
	
	public ItemReader<Map<String, String>> buildCsvReader(MultipartFile file, Template template, boolean hasHeaderRows, String csvDelimiter) throws IOException {
		if (StringUtils.isEmpty(csvDelimiter))
			csvDelimiter = CSV_DELIMITER;
		
		FlatFileItemReader<Map<String, String>> reader = new FlatFileItemReader<>();
		reader.setResource(new InputStreamResource(file.getInputStream()));
		reader.setLinesToSkip(hasHeaderRows ? 1 : 0);
		
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(getTokens(template));
		tokenizer.setDelimiter(csvDelimiter);
		
        DefaultLineMapper<Map<String, String>> lineMapper = new DefaultLineMapper<>();
        
        lineMapper.setFieldSetMapper(new CsvFieldMapper(template));
        lineMapper.setLineTokenizer(tokenizer);
		
		reader.setLineMapper(lineMapper);
		
		return reader;
	}
	
	public ItemReader<Map<String, String>> buildXlsxReader(MultipartFile file, Template template, boolean hasHeaderRows) throws IOException {
		PoiItemReader<Map<String, String>> reader = new PoiItemReader<>();
		reader.setResource(new InputStreamResource(file.getInputStream()));
		reader.setLinesToSkip(hasHeaderRows ? 1 : 0);
		reader.setRowMapper(new XlsxFieldMapper(template));
		
		return reader;
	}
	
	public ItemReader<Map<String, String>> buildAnswerReader(List<Map<String, String>> answers) throws IOException {
		return new AnswerItemReader(answers);
	}
	
	
	
	private String[] getTokens(Template template) {
		
		return template.getMappings()
			.entrySet()
			.stream()
			.sorted(Comparator.comparingInt(mapping -> NumberUtils.convertToInteger(mapping.getValue())))
			.map(entry -> entry.getKey().getCode())
			.toArray(String[]::new);
	}
}
