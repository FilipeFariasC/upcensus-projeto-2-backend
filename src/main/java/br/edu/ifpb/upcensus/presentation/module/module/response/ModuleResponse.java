package br.edu.ifpb.upcensus.presentation.module.module.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.ifpb.upcensus.presentation.form.configuration.response.ConfigurationResponse;
import br.edu.ifpb.upcensus.presentation.module.template.input.response.InputTemplateResponse;
import br.edu.ifpb.upcensus.presentation.module.template.output.response.OutputTemplateResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.DomainModelResponse;

public class ModuleResponse extends DomainModelResponse {

	
	private static final long serialVersionUID = 1L;

    private String code;

    private String name;
    
    private Set<String> tags;
    
    private ConfigurationResponse configuration;
    @JsonProperty("input_templates")
    private Set<InputTemplateResponse> inputTemplates;
    @JsonProperty("output_template")
    private OutputTemplateResponse outputTemplate;
    @JsonProperty("has_answers")
    private boolean hasAnswers;
    @JsonProperty("file_input_template_types")
    private Set<FileTypeResponse> fileInputTemplateTypes;
    private Set<MetadataResponse> metadata;

    
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

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}


	public ConfigurationResponse getConfiguration() {
		return configuration;
	}

	public void setConfiguration(ConfigurationResponse configuration) {
		this.configuration = configuration;
	}

	public Set<InputTemplateResponse> getInputTemplates() {
		return inputTemplates;
	}

	public void setInputTemplates(Set<InputTemplateResponse> inputTemplates) {
		this.inputTemplates = inputTemplates;
	}

	public OutputTemplateResponse getOutputTemplate() {
		return outputTemplate;
	}

	public void setOutputTemplate(OutputTemplateResponse outputTemplate) {
		this.outputTemplate = outputTemplate;
	}

	public boolean isHasAnswers() {
		return hasAnswers;
	}

	public void setHasAnswers(boolean hasAnswers) {
		this.hasAnswers = hasAnswers;
	}
	

	public Set<FileTypeResponse> getFileInputTemplateTypes() {
		return fileInputTemplateTypes;
	}

	public void setFileInputTemplateTypes(Set<FileTypeResponse> fileInputTemplateTypes) {
		this.fileInputTemplateTypes = fileInputTemplateTypes;
	}
	
	public Set<MetadataResponse> getMetadata() {
		return metadata;
	}

	public void setMetadata(Set<MetadataResponse> metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return String.format(
				"{code: %s, name: %s, tags: %s, configuration: %s, input_templates: %s, output_template: %s, has_answers: %s, file_input_template_types: %s, metadata: %s}",
				code, name, tags, configuration, inputTemplates, outputTemplate, hasAnswers, fileInputTemplateTypes, metadata);
	}
    
}
