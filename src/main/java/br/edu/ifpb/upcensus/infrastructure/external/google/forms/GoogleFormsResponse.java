package br.edu.ifpb.upcensus.infrastructure.external.google.forms;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.upcensus.infrastructure.external.google.forms.question.GoogleFormsQuestionAnswer;
import br.edu.ifpb.upcensus.presentation.shared.response.DomainModelResponse;

public class GoogleFormsResponse extends DomainModelResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<GoogleFormsQuestionAnswer> answers;

	public List<GoogleFormsQuestionAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<GoogleFormsQuestionAnswer> answers) {
		this.answers = answers;
	}
	
	

}
