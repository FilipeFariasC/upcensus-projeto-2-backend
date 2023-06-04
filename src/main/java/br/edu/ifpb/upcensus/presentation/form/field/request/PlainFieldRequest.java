package br.edu.ifpb.upcensus.presentation.form.field.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifpb.upcensus.domain.form.field.model.Type;

@JsonInclude(Include.NON_NULL)
public class PlainFieldRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String name;
	private String description;
	private Type type;
	private boolean required;
	private List<Long> characteristics;
	
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
	
	public List<Long> getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(List<Long> characteristics) {
		this.characteristics = characteristics;
	}
	
	@Override
	public String toString() {
		return String.format("{code: %s, name: %s, description: %s, type: %s, required: %s, characteristics: %s}", code,
				name, description, type, required, characteristics);
	}
	

}
