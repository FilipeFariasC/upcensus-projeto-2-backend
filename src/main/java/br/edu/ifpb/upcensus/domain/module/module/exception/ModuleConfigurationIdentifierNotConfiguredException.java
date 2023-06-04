package br.edu.ifpb.upcensus.domain.module.module.exception;

import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.shared.exception.DomainModelException;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainException;


@DomainException(
	key = "module.configuration.identifier.not-configured"
)
public class ModuleConfigurationIdentifierNotConfiguredException extends DomainModelException {
	
	private static final long serialVersionUID = 1L;

	public ModuleConfigurationIdentifierNotConfiguredException(String code) {
		super(Module.class, code);
	}


}
