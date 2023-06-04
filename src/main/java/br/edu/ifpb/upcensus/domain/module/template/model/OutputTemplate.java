package br.edu.ifpb.upcensus.domain.module.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;


@Entity
@Table(name = "t_output_template", schema = "module")
@SequenceGenerator(name = "t_output_template_id_seq", schema = "module", sequenceName = "t_output_template_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Modelo de Arquivo de Saída")
public class OutputTemplate extends DomainModel<Long> {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_output_template_id_seq")
	private Long id;
    
    @NotNull
    @Column(name = "code", unique = true)
    @Size(min = 3, max = 128)
    private String code;
    
    @NotNull
    @Size(min = 3, max = 128)
    private String name;
    
    @NotNull
    @NotEmpty
    @NotBlank
    private String layout;

	public OutputTemplate() { }
	
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

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	@Override
	public String toString() {
		return String.format("{id: %s, code: %s, name: %s, layout: %s, creation_time: %s}", id, code, name, layout,
				getCreationTime());
	}
	
	
	
}
