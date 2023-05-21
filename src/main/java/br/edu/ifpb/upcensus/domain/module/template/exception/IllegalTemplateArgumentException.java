package br.edu.ifpb.upcensus.domain.module.template.exception;

import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.infrastructure.exception.DomainException;
@br.edu.ifpb.upcensus.infrastructure.annotation.DomainException(
	key = "template.illegal-argument",
	append = true
)
public class IllegalTemplateArgumentException extends DomainException {

	private static final long serialVersionUID = 1L;
	
	public IllegalTemplateArgumentException(String fieldCode, String value, String templateCode) {
		super(Template.class, fieldCode, value, templateCode);
	}

}
