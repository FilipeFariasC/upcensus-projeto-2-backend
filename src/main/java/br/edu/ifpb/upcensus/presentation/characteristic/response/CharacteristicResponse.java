package br.edu.ifpb.upcensus.presentation.characteristic.response;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;

public class CharacteristicResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Attribute attribute;
	private String value;
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
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
		return String.format("{id: %s, attribute: %s, value: %s, description: %s}", id, attribute, value, description);
	}
	

}
