package br.edu.ifpb.upcensus.presentation.module.template.output.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.ifpb.upcensus.presentation.shared.response.DomainModelResponse;

public class OutputTemplateRequest extends DomainModelResponse{

	private static final long serialVersionUID = 1L;
	
	private String code;
    private String name;
    private String layout;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	@Override
	public String toString() {
		return String.format("{id: %s, creation_time: %s, code: %s, name: %s, layout: %s}",
				getId(), getCreationTime(), code, name, layout);
	}
	
	

}
