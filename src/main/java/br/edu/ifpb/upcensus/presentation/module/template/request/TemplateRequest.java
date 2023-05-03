package br.edu.ifpb.upcensus.presentation.module.template.request;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.util.JsonUtils;

public class TemplateRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("file_type")
	private FileType fileType;
	private String code;
    private String name;
    @JsonProperty("field_identifier")
    private String fieldIdentifier;
	private Map<String, String> mappings;
	
	
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
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
	
	public String getFieldIdentifier() {
		return fieldIdentifier;
	}
	public void setFieldIdentifier(String fieldIdentifier) {
		this.fieldIdentifier = fieldIdentifier;
	}
	
	public Map<String, String> getMappings() {
		return mappings;
	}
	public void setMappings(Map<String, String> mappings) {
		this.mappings = mappings;
	}
	
	@Override
	public String toString() {
		return String.format("{fileType: \"%s\", code: \"%s\", name: \"%s\", fieldIdentifier: \"%s\", mappings: %s}", fileType, code,
				name, fieldIdentifier, JsonUtils.mapToString(mappings));
	}

}
