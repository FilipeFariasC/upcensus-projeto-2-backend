package br.edu.ifpb.upcensus.infrastructure.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import br.edu.ifpb.upcensus.infrastructure.annotation.DomainException;
import br.edu.ifpb.upcensus.infrastructure.util.MessageKeys;

@DomainException(
	key = MessageKeys.ELEMENT_NOT_FOUND,
	status = HttpStatus.BAD_REQUEST,
	append = true
)
public class ElementNotFoundException extends br.edu.ifpb.upcensus.infrastructure.exception.DomainException {

	private static final long serialVersionUID = 1L;
	
	public ElementNotFoundException(
		final Class<?> classObject,
		final Object... params
	) {
		super(classObject, params);
	}

	@Override
	public String getMessage() {
		return String.format("Elemento: %s; Valor: %s", getClassName(), Arrays.toString(getParams()));
	}

}
