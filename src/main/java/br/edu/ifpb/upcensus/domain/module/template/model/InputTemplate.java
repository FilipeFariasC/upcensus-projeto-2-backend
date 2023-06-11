package br.edu.ifpb.upcensus.domain.module.template.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.shared.model.DomainEnum;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.exception.ElementNotFoundException;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;
import br.edu.ifpb.upcensus.infrastructure.util.JsonUtils;
import br.edu.ifpb.upcensus.infrastructure.util.NumberUtils;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;


@Entity
@Table(name = "t_input_template", schema = "module")
@SequenceGenerator(name = "t_input_template_id_seq", schema = "module", sequenceName = "t_input_template_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Modelo de Arquivo de Entrada")
public class InputTemplate extends DomainModel<Long> {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_input_template_id_seq")
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
		name = "t_input_template_mapping",
		schema = "module",
		joinColumns = @JoinColumn(name = "id_input_template", referencedColumnName = "id")
	)
	@MapKeyJoinColumn(name = "id_field")
	@Column(name = "config")
	private Map<PlainField, String> mappings;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "type")
	private Type type;

	public InputTemplate() { }
	
	
	
	
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
	
	public Map<PlainField, String> getMappings() {
		return mappings;
	}
	public void setMappings(Map<PlainField, String> mappings) {
		this.mappings = mappings;
	}

	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public PlainField getFieldFromCode(String code) {
		return getMappings()
			.keySet()
			.stream()
			.filter(field -> field.getCode().equals(code))
			.findFirst()
			.orElseThrow(()-> new ElementNotFoundException(PlainField.class, code));
	}
	
	public boolean isFileType(final FileType fileType) {
		if (ObjectUtils.isNull(fileType) || !getType().isFileType())
			return false;
		return Optional.of(getType())
			.map(Type::getFileType)
			.map(typeFile -> typeFile.equals(fileType))
			.orElse(false);
	}
	
	public boolean hasField(final PlainField field) {
		if (ObjectUtils.isNull(field))
			return false;
		return getMappings()
			.keySet()
			.stream()
			.anyMatch(mappingField -> mappingField.equals(field));
	}

	@Override
	public String toString() {
		return String.format("{id: %s, code: \"%s\", name: \"%s\", mappings: %s, type: %s}", id, code, name, JsonUtils.mapToString(mappings, PlainField::getCode),
				type);
	}
	
	public boolean isFileTemplate() {
		return type.isFileType();
	}
	
	public Map<PlainField, Integer> obtainIndexMappings() {
		return getMappings()
			.entrySet()
			.stream()
			.collect(
				Collectors.toMap(
					entry -> entry.getKey(),
					entry -> NumberUtils.convertToInteger(entry.getValue())
				)
			);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(code, type, id, mappings, name);
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
		InputTemplate other = (InputTemplate) obj;
		return Objects.equals(code, other.code)
				&& type == other.type && Objects.equals(id, other.id)
				&& Objects.equals(mappings, other.mappings) && Objects.equals(name, other.name);
	}


	public static enum Type implements DomainEnum<Type>{
		CSV(FileType.CSV), 
		FORM,
		GOOGLE,
		JSON(FileType.JSON),  
		ODS(FileType.ODS), 
		TEXT(FileType.TEXT),
		XLS(FileType.XLS), 
		XLSX(FileType.XLSX), 
		YAML(FileType.YAML), ;
		
		private final FileType fileType;
		
		Type(FileType fileType) {this.fileType = fileType;}
		Type () {this(null);}
		
		
		public FileType getFileType() {
			return fileType;
		}
		
		public boolean isFileType() {
			return ObjectUtils.nonNull(fileType);
		}
		@Override
		public String getLabel() {
			return isFileType() ? getFileType().getLabel() : name();
		}
		@Override
		public Type getValue() {
			return this;
		}
	}
	
	
}
