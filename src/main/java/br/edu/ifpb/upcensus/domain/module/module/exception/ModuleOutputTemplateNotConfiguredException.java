package br.edu.ifpb.upcensus.domain.module.module.exception;

import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.template.model.OutputTemplate;
import br.edu.ifpb.upcensus.domain.shared.exception.DomainModelException;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainException;


@DomainException(
	key = "module.output-template.not-configured"
)
public class ModuleOutputTemplateNotConfiguredException extends DomainModelException {
	
	private static final long serialVersionUID = 1L;

	public ModuleOutputTemplateNotConfiguredException(String code) {
		super(Module.class, OutputTemplate.class, code);
	}


}
