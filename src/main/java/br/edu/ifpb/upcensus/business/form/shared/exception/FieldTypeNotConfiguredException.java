package br.edu.ifpb.upcensus.business.form.shared.exception;

import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.infrastructure.exception.DomainException;

@br.edu.ifpb.upcensus.infrastructure.annotation.DomainException(
	key = "field.type-not-configured",
	append = true
)
public class FieldTypeNotConfiguredException extends DomainException {

	private static final long serialVersionUID = 1L;
	
	public FieldTypeNotConfiguredException() {
		super(PlainField.class);
	}

}
