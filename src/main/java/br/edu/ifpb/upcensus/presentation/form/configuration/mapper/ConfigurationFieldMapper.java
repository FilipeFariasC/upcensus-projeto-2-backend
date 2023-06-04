package br.edu.ifpb.upcensus.presentation.form.configuration.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.domain.form.configuration.model.ConfigurationField;
import br.edu.ifpb.upcensus.domain.form.field.service.PlainFieldService;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.form.configuration.request.ConfigurationFieldRequest;
import br.edu.ifpb.upcensus.presentation.form.configuration.response.ConfigurationConfigurationFieldResponse;
import br.edu.ifpb.upcensus.presentation.form.configuration.response.ConfigurationFieldResponse;
import br.edu.ifpb.upcensus.presentation.form.field.mapper.PlainFieldMapper;

@Mapper(
	config = MapStructConfig.class,
	uses = {
		PlainFieldService.class,
		CharacteristicService.class,
		PlainFieldMapper.class
	}
)
public interface ConfigurationFieldMapper
extends BaseMapper<ConfigurationField, ConfigurationFieldRequest, ConfigurationFieldResponse> 
{
	
	@Mapping(source = "request.fieldCode", target = "field")
	ConfigurationField requestToModel(ConfigurationFieldRequest request);

	ConfigurationConfigurationFieldResponse modelToConfigurationResponse(ConfigurationField field);
	List<ConfigurationConfigurationFieldResponse> modelListToConfigurationResponseList(List<ConfigurationField> field);
	Set<ConfigurationConfigurationFieldResponse> modelSetToConfigurationResponseSet(Set<ConfigurationField> field);
}
