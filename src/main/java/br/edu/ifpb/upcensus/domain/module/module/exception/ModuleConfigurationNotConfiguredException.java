package br.edu.ifpb.upcensus.domain.module.module.exception;

import org.springframework.http.HttpStatus;

import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.shared.exception.DomainModelException;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainException;


@DomainException(
	key = "module.configuration.not-configured",
	status = HttpStatus.CONFLICT
)
public class ModuleConfigurationNotConfiguredException extends DomainModelException {
	
	private static final long serialVersionUID = 1L;

	public ModuleConfigurationNotConfiguredException(String code) {
		super(Module.class, code);
	}


}
