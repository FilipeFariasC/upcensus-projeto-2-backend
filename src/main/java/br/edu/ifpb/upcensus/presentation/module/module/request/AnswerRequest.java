package br.edu.ifpb.upcensus.presentation.module.module.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AnswerRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String module;
	
	private String template;
	
	private List<Map<String, String>> answers;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public List<Map<String, String>> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Map<String, String>> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return String.format("{module: \"%s\", template: \"%s\", answers: %s}", module, template, answers);
	}
    

}
