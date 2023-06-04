package br.edu.ifpb.upcensus.presentation.form.configuration.response;

import java.io.Serializable;
import java.util.Set;

import br.edu.ifpb.upcensus.domain.form.field.model.Type;
import br.edu.ifpb.upcensus.presentation.form.characteristic.response.CharacteristicResponse;
import br.edu.ifpb.upcensus.presentation.form.field.response.PlainFieldResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.DomainModelResponse;

public class ConfigurationFieldResponse extends DomainModelResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ConfigurationResponse configuration;
	private PlainFieldResponse field;
	private Type type;
	private Boolean required;
	private Set<CharacteristicResponse> characteristics;
	
	public ConfigurationResponse getConfiguration() {
		return configuration;
	}
	public void setConfiguration(ConfigurationResponse configuration) {
		this.configuration = configuration;
	}
	public PlainFieldResponse getField() {
		return field;
	}
	public void setField(PlainFieldResponse field) {
		this.field = field;
	}

	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
	
	public Set<CharacteristicResponse> getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(Set<CharacteristicResponse> characteristics) {
		this.characteristics = characteristics;
	}
	
	@Override
	public String toString() {
		return String.format("{id: %s, configuration: %s, field: %s, type: %s, required: %s, characteristics: %s, creation_time: %s}", 
			getId(), configuration, field, type, required, characteristics, getCreationTime());
	}
	
	
}
