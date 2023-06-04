package br.edu.ifpb.upcensus.domain.module.module.exception;

import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.shared.exception.DomainModelException;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainException;


@DomainException(
	key = "module.configuration.identifier.not-required"
)
public class ModuleConfigurationIdentifierNotRequiredException extends DomainModelException {
	
	private static final long serialVersionUID = 1L;

	public ModuleConfigurationIdentifierNotRequiredException(String code) {
		super(Module.class, code);
	}


}
