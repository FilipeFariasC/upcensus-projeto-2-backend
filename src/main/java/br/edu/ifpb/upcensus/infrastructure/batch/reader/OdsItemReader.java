package br.edu.ifpb.upcensus.infrastructure.batch.reader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.web.multipart.MultipartFile;

import com.github.miachm.sods.Range;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;

import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.infrastructure.batch.reader.converter.OdsColumnConverter;
import br.edu.ifpb.upcensus.infrastructure.util.NumberUtils;

public class OdsItemReader implements ItemReader<Map<String, String>> {
	
	private int idx = 0;
	
	private final SpreadSheet spread;
	private final Map<PlainField, Integer> columnMappings;
	private final int startIndex;
	
	public OdsItemReader(MultipartFile file, InputTemplate inputTemplate, boolean ignoreHeaderRow) throws IOException {
		super();
		spread = new SpreadSheet(file.getInputStream());
		this.columnMappings = inputTemplate.obtainIndexMappings();
		this.startIndex = NumberUtils.booleanToInt(ignoreHeaderRow);
	}


	@Override
	public Map<String,String> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Map<String, String> next = null;
		
		Sheet sheets = obtainSheet();
        if (idx < sheets.getMaxRows()) {
            next = obtainRecord(idx++);
        } else {
            idx = startIndex;
        }
		return next;
	}
	
	private Map<String, String> obtainRecord(int index) {
		Map<String, String> record = new HashMap<>();
		
		Sheet sheet = obtainSheet();
		
		for (Map.Entry<PlainField, Integer> entry : columnMappings.entrySet()) {
			if(entry.getValue() > sheet.getMaxColumns()) continue;

			Range range = sheet.getRange(index, entry.getValue() - 1);
			String value = OdsColumnConverter.convertColumn(entry.getKey(), range.getValue());

			System.out.println(entry.getKey().getCode());
			System.out.println(value);
			Optional.ofNullable(value).map(Object::getClass).ifPresent(System.out::println);;
			
			record.put(entry.getKey().getCode(), value);
		}
		
		return record; 
	}
	
	private Sheet obtainSheet() {
		return spread.getSheet(0);
	}
	
}
