package br.edu.ifpb.upcensus.presentation.configuration.response;

import java.io.Serializable;
import java.util.Set;

import br.edu.ifpb.upcensus.presentation.characteristic.response.CharacteristicResponse;
import br.edu.ifpb.upcensus.presentation.field.response.FieldResponse;

public class ConfigurationConfigurationFieldResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private FieldResponse field;
	private Set<CharacteristicResponse> characteristics;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public FieldResponse getField() {
		return field;
	}
	public void setField(FieldResponse field) {
		this.field = field;
	}
	public Set<CharacteristicResponse> getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(Set<CharacteristicResponse> characteristics) {
		this.characteristics = characteristics;
	}
	
	@Override
	public String toString() {
		return String.format("{id: %s,  field: %s, characteristics: %s}", id, field,
				characteristics);
	}
	
	
}
