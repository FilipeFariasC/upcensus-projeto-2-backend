package br.edu.ifpb.upcensus.domain.module.template.model;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.util.FileType;


@Entity
@Table(name = "t_template", schema = "module")
@SequenceGenerator(name = "t_template_id_seq", schema = "module", sequenceName = "t_template_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Modelo de Arquivo")
public class Template extends DomainModel<Long> {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_template_id_seq")
	private Long id;
	@ElementCollection
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

	public Template() { }
	
	
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Template(FileType fileType) {
		this.fileType = fileType;
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
	
}
