package br.edu.ifpb.upcensus.presentation.module.module.mapper;

import org.mapstruct.Mapper;

import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.form.configuration.mapper.ConfigurationMapper;
import br.edu.ifpb.upcensus.presentation.module.module.request.ModuleRequest;
import br.edu.ifpb.upcensus.presentation.module.module.response.ModuleResponse;
import br.edu.ifpb.upcensus.presentation.module.template.mapper.TemplateMapper;
import br.edu.ifpb.upcensus.domain.form.configuration.service.ConfigurationService;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.module.service.ModuleService;
import br.edu.ifpb.upcensus.domain.module.template.service.TemplateService;

@Mapper(
		config = MapStructConfig.class,
		uses = {
			ModuleService.class,
			ConfigurationService.class,
			TemplateService.class,
			ConfigurationMapper.class,
			TemplateMapper.class
		}
	)
public interface ModuleMapper extends BaseMapper<Module, ModuleRequest, ModuleResponse>{
	
	
	

	
}
	