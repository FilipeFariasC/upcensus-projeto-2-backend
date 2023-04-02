package br.edu.ifpb.upcensus.presentation.configuration.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.domain.form.configuration.model.ConfigurationField;
import br.edu.ifpb.upcensus.domain.form.field.service.FieldService;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.configuration.request.ConfigurationFieldRequest;
import br.edu.ifpb.upcensus.presentation.configuration.response.ConfigurationConfigurationFieldResponse;
import br.edu.ifpb.upcensus.presentation.configuration.response.ConfigurationFieldResponse;

@Mapper(
	config = MapStructConfig.class,
	uses = {
		FieldService.class,
		CharacteristicService.class
	}
)
public interface ConfigurationFieldMapper
extends BaseMapper<ConfigurationField, ConfigurationFieldRequest, ConfigurationFieldResponse> 
{
	@Mapping(target = "field", source = "request.fieldCode")
	ConfigurationField requestToModel(ConfigurationFieldRequest request);

	ConfigurationConfigurationFieldResponse modelToConfigurationResponse(ConfigurationField field);
	List<ConfigurationConfigurationFieldResponse> modelListToConfigurationResponseList(List<ConfigurationField> field);
	Set<ConfigurationConfigurationFieldResponse> modelSetToConfigurationResponseSet(Set<ConfigurationField> field);
}
