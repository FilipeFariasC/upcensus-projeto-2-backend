package br.edu.ifpb.upcensus.infrastructure.external.google.forms.question;

public class GoogleFormsQuestionResponse {

	private String textResponse;
	private Double numberResponse;
	private Boolean booleanResponse;

	public String getTextResponse() {
		return textResponse;
	}

	public void setTextResponse(String textResponse) {
		this.textResponse = textResponse;
	}

	public Double getNumberResponse() {
		return numberResponse;
	}

	public void setNumberResponse(Double numberResponse) {
		this.numberResponse = numberResponse;
	}

	public Boolean getBooleanResponse() {
		return booleanResponse;
	}

	public void setBooleanResponse(Boolean booleanResponse) {
		this.booleanResponse = booleanResponse;
	}

}
