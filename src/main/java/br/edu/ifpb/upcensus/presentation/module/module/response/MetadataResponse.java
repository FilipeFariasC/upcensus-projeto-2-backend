package br.edu.ifpb.upcensus.presentation.module.module.response;

import br.edu.ifpb.upcensus.presentation.shared.response.DomainModelResponse;

public class MetadataResponse extends DomainModelResponse {

	
	private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private String value;

    
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("{id: %s, code: %s, name: %s, value: %s, creation_time: %s}", getId(), code, name, value, getCreationTime());
	}
    
}
