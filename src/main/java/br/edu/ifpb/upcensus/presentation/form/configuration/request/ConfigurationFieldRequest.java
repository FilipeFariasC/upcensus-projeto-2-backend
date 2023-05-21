package br.edu.ifpb.upcensus.presentation.form.configuration.request;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifpb.upcensus.domain.form.field.model.Type;

@JsonInclude(Include.NON_NULL)
public class ConfigurationFieldRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fieldCode;
	private Type type;
	private boolean required;
	private Set<Long> characteristics;
	
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public boolean getRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public Set<Long> getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(Set<Long> characteristics) {
		this.characteristics = characteristics;
	}
	@Override
	public String toString() {
		return String.format("{fieldCode: %s, type: %s, required: %s, characteristics: %s}", fieldCode, type, required,
				characteristics);
	}
	
	
	
	
}
