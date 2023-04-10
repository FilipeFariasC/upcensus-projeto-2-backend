package br.edu.ifpb.upcensus.presentation.module.module.response;

import java.util.Set;

import br.edu.ifpb.upcensus.presentation.form.configuration.response.ConfigurationResponse;
import br.edu.ifpb.upcensus.presentation.module.template.response.TemplateResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.DomainModelResponse;

public class ModuleResponse extends DomainModelResponse{

	
	private static final long serialVersionUID = 1L;

    private String code;

    private String name;
    
    private Set<String> tags;
    
    private ConfigurationResponse configuration;
    
    private Set<TemplateResponse> templates;

    
    
    
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

	public Set<TemplateResponse> getTemplates() {
		return templates;
	}

	public void setTemplates(Set<TemplateResponse> templates) {
		this.templates = templates;
	}

	@Override
	public String toString() {
		return String.format("{id: %s, creation_time: %s, code: %s, name: %s, tags: %s, configuration: %s, templates: %s}", code, name,
				tags, configuration, templates, getId(), getCreationTime());
	}
    
    
    
    
}
