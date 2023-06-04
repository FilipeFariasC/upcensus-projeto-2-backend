package br.edu.ifpb.upcensus.presentation.module.module.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.ifpb.upcensus.presentation.form.configuration.response.ConfigurationResponse;
import br.edu.ifpb.upcensus.presentation.module.template.response.InputTemplateResponse;
import br.edu.ifpb.upcensus.presentation.module.template.response.OutputTemplateResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.DomainModelResponse;

public class ModuleResponse extends DomainModelResponse{

	
	private static final long serialVersionUID = 1L;

    private String code;

    private String name;
    
    private Set<String> tags;
    
    private ConfigurationResponse configuration;
    @JsonProperty("input_templates")
    private Set<InputTemplateResponse> inputTemplates;
    @JsonProperty("output_template")
    private OutputTemplateResponse outputTemplate;
    private boolean hasAnswers;

    
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

	@Override
	public String toString() {
		return String.format(
				"{id: %s, creation_time: %s, code: %s, name: %s, tags: %s, configuration: %s, inputTemplates: %s, outputTemplate: %s, hasAnswers: %s}",
				getId(), getCreationTime(), code, name, tags, configuration, inputTemplates, outputTemplate, hasAnswers);
	}
    
}
