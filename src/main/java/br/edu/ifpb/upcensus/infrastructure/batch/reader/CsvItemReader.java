package br.edu.ifpb.upcensus.infrastructure.batch.reader;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.infrastructure.domain.job.reader.CsvFieldMapper;
import br.edu.ifpb.upcensus.infrastructure.util.NumberUtils;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;

public class CsvItemReader extends FlatFileItemReader<Map<String, String>> {
	
	private final InputTemplate inputTemplate;
	private final String delimiter;

	private static final String CSV_DELIMITER = ",";
	
	public CsvItemReader(MultipartFile file, InputTemplate inputTemplate, boolean ignoreHeaderRows, String delimiter) throws IOException {
		this.inputTemplate = inputTemplate;
		this.delimiter = StringUtils.isEmpty(delimiter) ? CSV_DELIMITER : delimiter;
		setResource(new InputStreamResource(file.getInputStream()));
		setLinesToSkip(ignoreHeaderRows ? 1 : 0);
		
		createLineMapper();
	}
	
	private DelimitedLineTokenizer createLineTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(getTokens(inputTemplate));
		tokenizer.setDelimiter(delimiter);
		
		return tokenizer;
	}
	
	private void createLineMapper() {
        DefaultLineMapper<Map<String, String>> lineMapper = new DefaultLineMapper<>();
        
        lineMapper.setFieldSetMapper(new CsvFieldMapper(inputTemplate));
        lineMapper.setLineTokenizer(createLineTokenizer());
        
        setLineMapper(lineMapper);
	}

	
	
	private String[] getTokens(InputTemplate template) {
		return template.getMappings()
			.entrySet()
			.stream()
			.sorted(Comparator.comparingInt(mapping -> NumberUtils.convertToInteger(mapping.getValue())))
			.map(entry -> entry.getKey().getCode())
			.toArray(String[]::new);
	}
}
