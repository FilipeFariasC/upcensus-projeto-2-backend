package br.edu.ifpb.upcensus.infrastructure.external.google.forms.question;

public class GoogleFormsQuestionAnswer {
	
	private String questionId;
    private GoogleFormsQuestionResponse questionResponse;
    
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public GoogleFormsQuestionResponse getQuestionResponse() {
		return questionResponse;
	}
	public void setQuestionResponse(GoogleFormsQuestionResponse questionResponse) {
		this.questionResponse = questionResponse;
	}
    
    

}
