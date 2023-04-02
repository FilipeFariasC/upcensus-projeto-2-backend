package br.edu.ifpb.upcensus.presentation.configuration.request;

import java.io.Serializable;
import java.util.Set;

public class ConfigurationFieldRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fieldCode;
	private Set<Long> characteristics;
	
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	
	public Set<Long> getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(Set<Long> characteristics) {
		this.characteristics = characteristics;
	}
	
	@Override
	public String toString() {
		return String.format("{fieldCode: %s, characteristics: %s}", fieldCode, characteristics);
	}
	
	
}
