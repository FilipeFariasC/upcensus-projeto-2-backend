package br.edu.ifpb.upcensus.domain.shared.model;

public interface DomainEnum<E extends Enum<E>> {
	String getLabel();
	E getValue();
}
