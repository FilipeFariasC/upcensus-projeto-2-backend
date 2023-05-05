package br.edu.ifpb.upcensus.domain.module.module.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Type;
import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.domain.shared.exception.ResourceNotFoundException;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;
import br.edu.ifpb.upcensus.infrastructure.util.JsonUtils;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;

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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_configuration", referencedColumnName = "id")
    @NotNull
    private Configuration configuration;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        	name = "t_module_template",
        	schema = "module",
        	joinColumns = @JoinColumn(name = "id_module"),
        	inverseJoinColumns = @JoinColumn(name = "id_template")
    )
    private Set<Template> templates;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers;
    
    
    @Override
    public void initialize() {
    	initializeTemplates();
    	initializeTags();
    	initializeAnswers();
    }
    
    private void initializeTemplates() {
    	if (CollectionUtils.isEmpty(getTemplates()))
    		this.templates = new HashSet<>();
    }
    private void initializeTags() {
    	if (CollectionUtils.isEmpty(getTags()))
    		this.tags = new HashSet<>();
    }
    private void initializeAnswers() {
    	if (CollectionUtils.isEmpty(getAnswers()))
    		this.answers = new HashSet<>();
    }
    
    public Optional<Characteristic> getCharacteristic(Field field, Attribute attribute) {
    	return getConfiguration().getAttribute(field, attribute);
    }
    
    public Optional<Type> getType(Field field) {
    	return getConfiguration().getType(field);
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

    public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	public Set<Template> getTemplates() {
		return templates;
	}
	public void setTemplates(Set<Template> templates) {
		if (CollectionUtils.isEmpty(getTemplates())) {
			this.templates = templates;
			return;
		}
		getTemplates().clear();
		if (CollectionUtils.notEmpty(templates)) {
			getTemplates().retainAll(templates);
			getTemplates().addAll(templates);
		}
	}
	
	public Template getTemplateByFileType(final FileType fileType) {
		if (CollectionUtils.isEmpty(getTemplates()))
			throw new ResourceNotFoundException(Template.class, "file_type", fileType);
		return getTemplates()
			.stream()
			.filter(template -> template.getFileType().equals(fileType))
			.findFirst()
			.orElseThrow(() -> new ResourceNotFoundException(Template.class, "file_type", fileType));
	}
	
	
	public Set<Answer> getAnswers() {
		return answers;
	}
	public Set<Answer> getAnswers(Long idTemplate) {
		if (CollectionUtils.isEmpty(getAnswers()))
			return Collections.emptySet();
		
		return getAnswers()
			.stream()
			.filter(answer-> answer.getTemplate().getId().equals(idTemplate))
			.collect(Collectors.toSet());
	}
	
	public void clearAnswers() {
		setAnswers(new HashSet<>());
	}
	
	public void removeAnswers(Template template) {
		Set<Answer> remove = getAnswers()
			.stream()
			.filter(answer -> answer.getTemplate().equals(template))
			.collect(Collectors.toSet());
		
		getAnswers().removeAll(remove);
	}
	public void setAnswers(Set<Answer> answers) {
		if (ObjectUtils.isNull(getAnswers())) {
			setupAddAnswers(answers);
			this.answers = answers;
			return;
		}
		getAnswers().clear();
		if (ObjectUtils.nonNull(answers)) {
			setupAddAnswers(answers);
			getAnswers().retainAll(answers);
			getAnswers().addAll(answers);
		}
	}
	
	private void setupAddAnswers(Set<Answer> answers) {
		CollectionUtils.forEach(answers, answer->answer.setModule(this));
	}
	private void setupRemoveAnswers(Set<Answer> answers) {
		CollectionUtils.forEach(answers, answer->answer.setModule(null));
	}
	
	public void addAllAnswers(Set<Answer> answers) {
		setupAddAnswers(answers);
		getAnswers().addAll(answers);
	}
	public void removeAllAnswers(Set<Answer> answers) {
		setupRemoveAnswers(answers);
		getAnswers().removeAll(answers);
	}
	
	
	public Optional<Template> getTemplateById(Long id) {
		if (CollectionUtils.isEmpty(getTemplates()))
			return Optional.empty();
		return getTemplates()
			.stream()
			.filter(template -> template.getId().equals(id))
			.findFirst();
	}
	
	
	
	@Override
	public String toString() {
		return String.format("{id: %s, code: \"%s\", name: \"%s\", tags: %s, configuration: %s, templates: %s, answer: \"%s\"}", id,
				code, name, JsonUtils.stringListToJsonString(tags), configuration, templates, answers);
	}
	
	
}