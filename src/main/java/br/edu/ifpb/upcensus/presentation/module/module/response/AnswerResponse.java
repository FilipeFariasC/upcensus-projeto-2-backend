package br.edu.ifpb.upcensus.presentation.module.module.response;

import java.io.Serializable;
import java.util.Map;

public class AnswerResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<String, Map<String, String>> answers;

	public Map<String, Map<String, String>> getAnswers() {
		return answers;
	}
	public void setAnswers(Map<String, Map<String, String>> answers) {
		this.answers = answers;
	}
	
	
	@Override
	public String toString() {
		return String.format("{answers: %s}", answers);
	}
    

}
