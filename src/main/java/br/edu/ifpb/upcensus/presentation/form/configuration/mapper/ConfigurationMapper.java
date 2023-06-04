package br.edu.ifpb.upcensus.presentation.form.configuration.mapper;

import org.mapstruct.Mapper;

import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.domain.form.configuration.service.ConfigurationService;
import br.edu.ifpb.upcensus.domain.form.field.service.PlainFieldService;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.form.configuration.request.ConfigurationRequest;
import br.edu.ifpb.upcensus.presentation.form.configuration.response.ConfigurationResponse;
import br.edu.ifpb.upcensus.presentation.form.field.mapper.PlainFieldMapper;

@Mapper(
	config = MapStructConfig.class,
	uses = {
		ConfigurationService.class,
		ConfigurationFieldMapper.class,
		PlainFieldService.class,
		PlainFieldMapper.class
	}
)
public interface ConfigurationMapper 
extends BaseMapper<Configuration, ConfigurationRequest, ConfigurationResponse> 
{
}
