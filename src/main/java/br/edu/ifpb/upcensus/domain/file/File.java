package br.edu.ifpb.upcensus.domain.file;

import br.edu.ifpb.upcensus.domain.shared.model.DomainModel;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;

@DomainDescriptor(name="Arquivo")
public class File extends DomainModel<Long>{

	private static final long serialVersionUID = 1L;
	
	public Long id;

	@Override
	public Long getId() {
		return id;
	}
	

}
