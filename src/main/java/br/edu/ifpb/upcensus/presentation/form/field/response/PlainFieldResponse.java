package br.edu.ifpb.upcensus.presentation.form.field.response;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.upcensus.domain.form.field.model.Type;
import br.edu.ifpb.upcensus.presentation.form.characteristic.response.CharacteristicResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.DomainModelResponse;

public class PlainFieldResponse extends DomainModelResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private String description;
	private Type type;
	private boolean required;
	private List<CharacteristicResponse> characteristics;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	
	public List<CharacteristicResponse> getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(List<CharacteristicResponse> characteristics) {
		this.characteristics = characteristics;
	}
	
	
	@Override
	public String toString() {
		return String.format(
				"{id: %s, code: %s, name: %s, description: %s, type: %s, required: %s, characteristics: %s, creation_time: %s}", 
				getId(), code, name, description, type, required, characteristics, getCreationTime());
	}

	
}
