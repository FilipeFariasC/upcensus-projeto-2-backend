package br.edu.ifpb.upcensus.domain.module.template.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.exception.ElementNotFoundException;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;
import br.edu.ifpb.upcensus.infrastructure.util.JsonUtils;


@Entity
@Table(name = "t_template", schema = "module")
@SequenceGenerator(name = "t_template_id_seq", schema = "module", sequenceName = "t_template_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Modelo de Arquivo")
public class Template extends DomainModel<Long> {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_template_id_seq")
	private Long id;
    
    @NotNull
    @Column(name = "code", unique = true)
    @Size(min = 3, max = 128)
    private String code;
    
    @NotNull
    @Size(min = 3, max = 128)
    private String name;
    
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
		name = "t_template_mapping",
		schema = "module",
		joinColumns = @JoinColumn(name = "id_template", referencedColumnName = "id")
	)
	@MapKeyJoinColumn(name = "id_field")
	@Column(name = "config")
	private Map<Field, String> mappings;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "file_type")
	private FileType fileType;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_field_identifier")
	private Field fieldIdentifier;

	public Template() { }
	
	
	
	
	@Override
	public void initialize() {
    	initializeMappings();
	}
	

    private void initializeMappings() {
    	if (CollectionUtils.isEmpty(getMappings()))
    		this.mappings = new HashMap<>();
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
	
	public Map<Field, String> getMappings() {
		return mappings;
	}
	public void setMappings(Map<Field, String> mappings) {
		this.mappings = mappings;
	}

	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	
	public Field getFieldIdentifier() {
		return fieldIdentifier;
	}
	public void setFieldIdentifier(Field fieldIdentifier) {
		this.fieldIdentifier = fieldIdentifier;
	}
	
	public Field getFieldFromCode(String code) {
		return getMappings()
			.keySet()
			.stream()
			.filter(field -> field.getCode().equals(code))
			.findFirst()
			.orElseThrow(()-> new ElementNotFoundException(Field.class, code));
	}


	@Override
	public String toString() {
		return String.format("{id: %s, code: \"%s\", name: \"%s\", mappings: %s, file_type: %s, field_identifier: \"%s\"}", id, code, name, JsonUtils.mapToString(mappings, Field::getCode),
				fileType, fieldIdentifier);
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(code, fieldIdentifier, fileType, id, mappings, name);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Template other = (Template) obj;
		return Objects.equals(code, other.code) && Objects.equals(fieldIdentifier, other.fieldIdentifier)
				&& fileType == other.fileType && Objects.equals(id, other.id)
				&& Objects.equals(mappings, other.mappings) && Objects.equals(name, other.name);
	}


	
	
	
}
