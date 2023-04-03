package br.edu.ifpb.upcensus.domain.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;


@Entity
@Table(name = "t_file", schema = "form")
@SequenceGenerator(name = "t_file_id_seq", schema = "form", sequenceName = "t_file_id_seq", allocationSize = 1)
@DomainDescriptor(name = "Arquivo")
public class File extends DomainModel<Long>{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_file_id_seq")
	private Long id;
    
    @NotNull
    @Column(name = "code", unique = true)
    @Size(min = 3, max = 128)
    private String code;
    
    
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
