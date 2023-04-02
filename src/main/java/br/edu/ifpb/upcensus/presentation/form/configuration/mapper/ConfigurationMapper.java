package br.edu.ifpb.upcensus.presentation.form.configuration.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.domain.form.configuration.service.ConfigurationService;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.form.configuration.request.ConfigurationRequest;
import br.edu.ifpb.upcensus.presentation.form.configuration.response.ConfigurationResponse;

@Mapper(
	config = MapStructConfig.class,
	uses = {
		ConfigurationService.class,
		ConfigurationFieldMapper.class
	}
)
public interface ConfigurationMapper 
extends BaseMapper<Configuration, ConfigurationRequest, ConfigurationResponse> 
{
	@AfterMapping
	default void setConfiguration(@MappingTarget Configuration configuration) {
//		System.out.println(configuration);
//		configuration.getFields().forEach(field-> field.setConfiguration(configuration));
	}
	
}
