package br.edu.ifpb.upcensus.presentation.module.template.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.ifpb.upcensus.infrastructure.util.FileType;
import br.edu.ifpb.upcensus.presentation.form.field.response.FieldResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.DomainModelResponse;

public class TemplateResponse extends DomainModelResponse {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("file_type")
	private FileType fileType;
	private Map<String, String> mappings;
	
	
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	public Map<String, String> getMappings() {
		return mappings;
	}
	public void setMappings(Map<String, String> mappings) {
		this.mappings = mappings;
	}
	
	
	@Override
	public String toString() {
		return String.format("{id: %s, creation_time: %s, fileType: %s, mappings: %s}", fileType, mappings,
				getId(), getCreationTime());
	}
	
	
	

}
