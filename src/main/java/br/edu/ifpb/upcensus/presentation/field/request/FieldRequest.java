package br.edu.ifpb.upcensus.presentation.field.request;

import java.io.Serializable;
import java.util.List;

public class FieldRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String name;
	private String description;
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
	
	public List<Long> getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(List<Long> characteristics) {
		this.characteristics = characteristics;
	}
	@Override
	public String toString() {
		return String.format("{code: %s, name: %s, description: %s, characteristics: %s}", code, name, description,
				characteristics);
	}
	
	

}
