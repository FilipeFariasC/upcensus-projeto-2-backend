package br.edu.ifpb.upcensus.presentation.module.template.request;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate.Type;
import br.edu.ifpb.upcensus.infrastructure.util.JsonUtils;

public class InputTemplateRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Type type;
	private String code;
    private String name;
	private Map<String, String> mappings;
	
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

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
	
	public Map<String, String> getMappings() {
		return mappings;
	}
	public void setMappings(Map<String, String> mappings) {
		this.mappings = mappings;
	}
	
	@Override
	public String toString() {
		return String.format("{type: \"%s\", code: \"%s\", name: \"%s\", mappings: %s}", type, code,
				name, JsonUtils.mapToString(mappings));
	}

}
