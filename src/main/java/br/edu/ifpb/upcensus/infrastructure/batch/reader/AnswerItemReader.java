package br.edu.ifpb.upcensus.infrastructure.batch.reader;

import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class AnswerItemReader implements ItemReader<Map<String, String>> {
	private int idx = 0;
	private final List<Map<String, String>> answers;
	
	public AnswerItemReader(List<Map<String, String>> answers) {
		super();
		this.answers = answers;
	}


	@Override
	public Map<String,String> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Map<String, String> next = null;
		 
        if (idx < answers.size()) {
            next = answers.get(idx++);
        } else {
            idx = 0;
        }
		return next;
	}

}
