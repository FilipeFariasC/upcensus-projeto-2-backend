package br.edu.ifpb.upcensus.presentation.form.configuration.response;

import java.util.Set;

import br.edu.ifpb.upcensus.presentation.shared.response.DomainModelResponse;

public class ConfigurationResponse extends DomainModelResponse {

	private static final long serialVersionUID = 1L;

	private String code;
	private String name;
	private Set<ConfigurationConfigurationFieldResponse> fields;
	
	
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
	public Set<ConfigurationConfigurationFieldResponse> getFields() {
		return fields;
	}
	public void setFields(Set<ConfigurationConfigurationFieldResponse> fields) {
		this.fields = fields;
	}
	@Override
	public String toString() {
		return String.format("{id: %s, creation_time: %s, code: %s, name: %s, fields: %s}",
				getId(), getCreationTime(), code, name, fields);
	}
	

}
