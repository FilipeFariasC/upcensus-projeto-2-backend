package br.edu.ifpb.upcensus.business.module.module.exception;

import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainException;

@DomainException(
	key = "module.migration.not-configured",
	append = true
)
public class ModuleMigrationNotConfigured extends br.edu.ifpb.upcensus.infrastructure.exception.DomainException {

	private static final long serialVersionUID = 1L;

	public ModuleMigrationNotConfigured(Object... params) {
		super(Module.class, params);
	}
}
