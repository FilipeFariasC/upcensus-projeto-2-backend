package br.edu.ifpb.upcensus.presentation.characteristic.request;

import java.io.Serializable;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;


public class CharacteristicRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Attribute attribute;
	private String value;
	private String description;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format("{attribute: %s, value: %s, description: %s}", attribute, value,
				description);
	}
}
