package br.edu.ifpb.upcensus.infrastructure.builder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ItemReader;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.infrastructure.batch.reader.AnswerItemReader;
import br.edu.ifpb.upcensus.infrastructure.batch.reader.CsvItemReader;
import br.edu.ifpb.upcensus.infrastructure.batch.reader.XlsxItemReader;

public class ItemReaderBuilder {
	
	
	public CsvItemReader buildCsvReader(MultipartFile file, InputTemplate template, boolean ignoreHeaderRows, String delimiter) throws IOException {	
		return new CsvItemReader(file, template, ignoreHeaderRows, delimiter);
	}
	
	public ItemReader<Map<String, String>> buildXlsxReader(MultipartFile file, InputTemplate template, boolean ignoreHeaderRows) throws IOException {
		return new XlsxItemReader(file, template, ignoreHeaderRows);
	}
	
	public ItemReader<Map<String, String>> buildAnswerReader(List<Map<String, String>> answers) throws IOException {
		return new AnswerItemReader(answers);
	}
	
}
