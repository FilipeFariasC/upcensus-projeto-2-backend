package br.edu.ifpb.upcensus.infrastructure.batch.reader;

import java.io.IOException;
import java.util.Map;

import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.infrastructure.domain.job.reader.XlsxFieldMapper;

public class XlsxItemReader extends PoiItemReader<Map<String, String>> {
	
	public XlsxItemReader(MultipartFile file, InputTemplate inputTemplate, boolean ignoreHeaderRows) throws IOException {
		setResource(new InputStreamResource(file.getInputStream()));
		setLinesToSkip(ignoreHeaderRows ? 1 : 0);
		
		setRowMapper(new XlsxFieldMapper(inputTemplate));
	}
}
