package br.edu.ifpb.upcensus.presentation.module.module.request;

import java.io.Serializable;

public class MetadataRequest implements Serializable{

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
		return String.format("{code: %s, name: %s, value: %s}", code, name, value);
	}

}
