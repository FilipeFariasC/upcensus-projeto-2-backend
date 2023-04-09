package br.edu.ifpb.upcensus.presentation.form.characteristic.response;

import java.io.Serializable;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.presentation.shared.response.DomainModelResponse;

public class CharacteristicResponse extends DomainModelResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Attribute attribute;
	private String value;
	private String description;
	

	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public String toString() {
		return String.format("{id: %s, creation_time: %s, attribute: %s, value: %s, description: %s}", getId(), getCreationTime(),
				attribute, value, description);
	}
	

}
