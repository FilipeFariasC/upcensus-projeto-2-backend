package br.edu.ifpb.upcensus.domain.form.characteristic.model;

import java.util.stream.Stream;

import br.edu.ifpb.upcensus.domain.shared.model.DomainEnum;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.exception.ElementNotFoundException;

@DomainDescriptor(name = "Atributo")
public enum Attribute implements DomainEnum<Attribute> {
	TYPE("Tipo"),
	REQUIRED("Obrigatoriedade"),
	MIN_LENGTH("Tamanho mínimo"),
	MAX_LENGTH("Tamanho máximo"),
	MIN_VALUE("Valor mínimo"),
	MAX_VALUE("Valor máximo"),
	PATTERN("Padrão");
	
	private final String label;
	
	Attribute(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public Attribute getValue() {
		return this;
	}
	
	public static Attribute from(String attribute) {
		return Stream.of(Attribute.values())
			.filter(attr-> attr.name().equals(attribute))
			.findFirst()
			.orElseThrow(()-> new ElementNotFoundException(Attribute.class, attribute));
	}

}
