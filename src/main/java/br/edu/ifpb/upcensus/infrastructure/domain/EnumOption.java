package br.edu.ifpb.upcensus.infrastructure.domain;

import java.io.Serializable;

import br.edu.ifpb.upcensus.domain.shared.model.DomainEnum;

public class EnumOption<E extends Enum<E> & DomainEnum<E>> implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	private E value;
	
	private String label;
	
	public EnumOption() {}
	
	public EnumOption(E domainEnum) {
		this.value = domainEnum.getValue();
		this.label = domainEnum.getLabel();
	}
	

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return String.format("{value: %s, label: %s}", value, label);
	}
	
}
