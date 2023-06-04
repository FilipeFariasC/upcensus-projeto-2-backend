package br.edu.ifpb.upcensus.domain.module.module.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic.Attribute;
import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.form.field.model.Type;
import br.edu.ifpb.upcensus.domain.module.module.exception.ModuleConfigurationIdentifierNotConfiguredException;
import br.edu.ifpb.upcensus.domain.module.module.exception.ModuleConfigurationIdentifierNotRequiredException;
import br.edu.ifpb.upcensus.domain.module.module.exception.ModuleConfigurationNotConfiguredException;
import br.edu.ifpb.upcensus.domain.module.module.exception.ModuleOutputTemplateNotConfiguredException;
import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.domain.module.template.model.OutputTemplate;
import br.edu.ifpb.upcensus.domain.shared.exception.ResourceNotFoundException;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;

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
    @JoinColumn(name = "id_configuration", referencedColumnName = "id", nullable = true)
    private Configuration configuration;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        	name = "t_module_template",
        	schema = "module",
        	joinColumns = @JoinColumn(name = "id_module"),
        	inverseJoinColumns = @JoinColumn(name = "id_template")
    )
    private Set<InputTemplate> inputTemplates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_output_template", referencedColumnName = "id")
    private OutputTemplate outputTemplate;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers;
    
    
    @Override
    public void initialize() {
    	initializeTemplates();
    	initializeTags();
    	initializeAnswers();
    }
    
    
    private void initializeTemplates() {
    	if (CollectionUtils.isEmpty(getInputTemplates()))
    		this.inputTemplates = new HashSet<>();
    }
    private void initializeTags() {
    	if (CollectionUtils.isEmpty(getTags()))
    		this.tags = new HashSet<>();
    }
    private void initializeAnswers() {
    	if (CollectionUtils.isEmpty(getAnswers()))
    		this.answers = new HashSet<>();
    }
    
    public Optional<Characteristic> getCharacteristic(PlainField field, Attribute attribute) {
    	if (ObjectUtils.isNull(getConfiguration()))
    		return Optional.empty();
    	return getConfiguration().getAttribute(field, attribute);
    }
    
    public Optional<Type> getType(PlainField field) {
    	if (ObjectUtils.isNull(getConfiguration()))
    		return Optional.empty();
    	return getConfiguration().getType(field);
    }
    
    public Optional<Boolean> getRequired(PlainField field) {
    	if (ObjectUtils.isNull(getConfiguration()))
    		return Optional.empty();
    	return getConfiguration().getRequired(field);
    }
    
    public boolean hasCharacteristic(PlainField field, Attribute attribute) {
		return getConfiguration().hasCharacteristic(field, attribute);
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
	public Set<InputTemplate> getInputTemplates() {
		return inputTemplates;
	}
	public void setInputTemplates(Set<InputTemplate> templates) {
		if (CollectionUtils.isEmpty(getInputTemplates())) {
			this.inputTemplates = templates;
			return;
		}
		getInputTemplates().clear();
		if (CollectionUtils.notEmpty(templates)) {
			getInputTemplates().retainAll(templates);
			getInputTemplates().addAll(templates);
		}
	}
	
	public OutputTemplate getOutputTemplate() {
		return outputTemplate;
	}

	public void setOutputTemplate(OutputTemplate outputTemplate) {
		this.outputTemplate = outputTemplate;
	}

	public InputTemplate getTemplateByFileType(final FileType fileType) {
		if (CollectionUtils.isEmpty(getInputTemplates()))
			throw new ResourceNotFoundException(InputTemplate.class, "file_type", fileType);
		return getInputTemplates()
			.stream()
			.filter(template -> template.isFileType(fileType) && template.hasField(getIdentifierField()))
			.findFirst()
			.orElseThrow(() -> new ResourceNotFoundException(InputTemplate.class, "file_type", fileType));
	}
	
	public boolean hasAnswers() {
		return CollectionUtils.notEmpty(getAnswers());
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
	
	public void removeAnswers(InputTemplate template) {
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
	
	
	public Optional<InputTemplate> getTemplateById(Long id) {
		if (CollectionUtils.isEmpty(getInputTemplates()))
			return Optional.empty();
		return getInputTemplates()
			.stream()
			.filter(template -> template.getId().equals(id))
			.findFirst();
	}

	public boolean hasAnswer(Answer answer) {
		if (CollectionUtils.isEmpty(getAnswers()))
			return false;
		return getAnswers()
			.stream()
			.anyMatch(answerRegistered -> answerRegistered.isAnswer(answer));
	}
	
	public Optional<Answer> getAnswer(String identifierValue, PlainField fieldIdentifier, String value) {
		if (CollectionUtils.isEmpty(getAnswers()))
			return Optional.empty();
		
		return getAnswers()
			.stream()
			.filter(answer -> answer.isAnswer(identifierValue, fieldIdentifier, value))
			.findAny();
	}
	
	public List<AnswerGroup> groupedAnswers() {
		return getAnswers()
			.stream()
			.collect(Collectors.groupingBy(Answer::getIdentifierValue, Collectors.toList()))
			.values()
			.stream()
			.map(AnswerGroup::of)
			.collect(Collectors.toList());
	}
	
	public PlainField getIdentifierField() {
		return Optional.ofNullable(getConfiguration())
			.map(Configuration::getIdentifierField)
			.orElse(null);
	}
	
	public void fill() {
		if (ObjectUtils.isNull(getConfiguration()))
			throw new ModuleConfigurationNotConfiguredException(getCode());
		if (ObjectUtils.isNull(getConfiguration().getIdentifierField()))
			throw new ModuleConfigurationIdentifierNotConfiguredException(getCode());
		if (!getConfiguration().isIdentifierFieldRequired())
			throw new ModuleConfigurationIdentifierNotRequiredException(getCode());
	}
	
	public void migrate() {
		if (ObjectUtils.isNull(getConfiguration()))
			throw new ModuleConfigurationNotConfiguredException(getCode());
		if (ObjectUtils.isNull(getConfiguration().getIdentifierField()))
			throw new ModuleConfigurationIdentifierNotConfiguredException(getCode());
		if (!getConfiguration().isIdentifierFieldRequired())
			throw new ModuleConfigurationIdentifierNotRequiredException(getCode());
		if (ObjectUtils.nonNull(getOutputTemplate()) || StringUtils.notBlank(getOutputTemplate().getLayout()))
			throw new ModuleOutputTemplateNotConfiguredException(getCode());
		
	}
	
	@Override
	public String toString() {
		return String.format(
				"{id: %s, code: %s, name: %s, tags: %s, configuration: %s, input_templates: %s, output_template: %s, answers: %s, creation_time: %s}",
				id, code, name, tags, configuration, inputTemplates, outputTemplate, answers, getCreationTime());
	}
	
	
}