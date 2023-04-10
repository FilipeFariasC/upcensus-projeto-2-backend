package br.edu.ifpb.upcensus.presentation.module.module.request;

import java.io.Serializable;
import java.util.Set;

public class ModuleRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String code;

    private String name;
    
    private Set<String> tags;
    
    private Long configuration;
    
    private Set<Long> templates;

    
    
    
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

	
	public Long getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Long configuration) {
		this.configuration = configuration;
	}

	public Set<Long> getTemplates() {
		return templates;
	}

	public void setTemplates(Set<Long> templates) {
		this.templates = templates;
	}

	@Override
	public String toString() {
		return String.format("{code: %s, name: %s, tags: %s, configuration: %s, templates: %s}", code, name,
				tags, configuration, templates);
	}
    
    

}
