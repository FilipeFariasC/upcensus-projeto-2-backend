package br.edu.ifpb.upcensus.presentation.module.module.request;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModuleRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String code;

    private String name;
    
    private Set<String> tags;
    
    private String configuration;

    @JsonProperty("input_templates")
    private Set<String> inputTemplates;
    @JsonProperty("output_template")
    private String outputTemplate;

    private Set<String> metadata;
    
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

	
	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public Set<String> getInputTemplates() {
		return inputTemplates;
	}

	public void setInputTemplates(Set<String> inputTemplates) {
		this.inputTemplates = inputTemplates;
	}
	
	public String getOutputTemplate() {
		return outputTemplate;
	}

	public void setOutputTemplate(String outputTemplate) {
		this.outputTemplate = outputTemplate;
	}
	
	public Set<String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Set<String> metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return String.format(
				"{code: %s, name: %s, tags: %s, configuration: %s, inputTemplates: %s, outputTemplate: %s, metadata: %s}", code, name,
				tags, configuration, inputTemplates, outputTemplate, metadata);
	}

}
