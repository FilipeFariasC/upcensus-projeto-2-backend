package br.edu.ifpb.upcensus.domain.module.module.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;

@Entity
@Table(name = "t_module", schema = "module")
@SequenceGenerator(name = "t_module_id_seq", schema = "module", sequenceName = "t_module_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Módulo")
public class Module extends DomainModel<Long> {
	
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_module_id_seq")
	private Long id;
    
    @NotNull
    @Column(name = "code", unique = true)
    @Size(min = 3, max = 128)
    private String code;
    
    @NotNull
    @Size(min = 3, max = 128)
    private String name;
    
    @NotEmpty
    @ElementCollection
    @CollectionTable(
    		name = "t_module_tags",
        	schema = "module",
        	joinColumns = @JoinColumn(name = "id_module")
    )
    @Column(name = "tag")
    private Set<String> tags;
    
    
    @JoinColumn(name = "id_configuration")
    private Configuration configuration;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        	name = "t_module_template",
        	schema = "module",
        	joinColumns = @JoinColumn(name = "id_module"),
        	inverseJoinColumns = @JoinColumn(name = "id_template")
    )
    private Set<Template> templates;
    
    
    
    
    public Set<Template> getTemplates() {
		return templates;
	}
	public void setTemplates(Set<Template> templates) {
		getTemplates().clear();
		if (CollectionUtils.notEmpty(templates)) {
			getTemplates().retainAll(templates);
			getTemplates().addAll(templates);
		}
	}
    
	@Override
	public Long getId() {
		return id;
	}
    public void setId(Long id) {
    	this.id = id;
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
	
	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	
	
}