package br.edu.ifpb.upcensus.business.form.shared.exception;

import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.infrastructure.exception.DomainException;

@br.edu.ifpb.upcensus.infrastructure.annotation.DomainException(
	key = "field.type-bad-configured",
	append = true
)
public class FieldTypeBadConfiguredException extends DomainException {

	private static final long serialVersionUID = 1L;
	
	public FieldTypeBadConfiguredException(String value) {
		super(Field.class, value);
	}

}
