package br.edu.ifpb.upcensus.presentation.field.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.ifpb.upcensus.presentation.characteristic.response.CharacteristicResponse;

public class FieldResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@JsonProperty("creation_time")
	private LocalDateTime creationTime;
	private String code;
	private String name;
	private String description;
	private List<CharacteristicResponse> characteristics;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}
	
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
	
	public List<CharacteristicResponse> getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(List<CharacteristicResponse> characteristics) {
		this.characteristics = characteristics;
	}
	
	
	@Override
	public String toString() {
		return String.format("{id: %s, creation_time: %s, code: %s, name: %s, description: %s, characteristics: %s}", id,
				creationTime, code, name, description, characteristics);
	}

	
}
