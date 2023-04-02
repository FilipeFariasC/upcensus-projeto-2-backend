package br.edu.ifpb.upcensus.presentation.configuration.request;

import java.io.Serializable;
import java.util.Set;

public class ConfigurationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private Set<ConfigurationFieldRequest> fields;
	
	
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
	
	public Set<ConfigurationFieldRequest> getFields() {
		return fields;
	}
	public void setFields(Set<ConfigurationFieldRequest> fields) {
		this.fields = fields;
	}
	
	@Override
	public String toString() {
		return String.format("{code: %s, name: %s, fields: %s}", code, name, fields);
	}
	
	
	
}
