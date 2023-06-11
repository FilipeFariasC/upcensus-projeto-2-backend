package br.edu.ifpb.upcensus.presentation.module.module.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.ifpb.upcensus.domain.form.configuration.service.ConfigurationService;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.module.service.MetadataService;
import br.edu.ifpb.upcensus.domain.module.module.service.ModuleService;
import br.edu.ifpb.upcensus.domain.module.template.service.InputTemplateService;
import br.edu.ifpb.upcensus.domain.module.template.service.OutputTemplateService;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.form.configuration.mapper.ConfigurationMapper;
import br.edu.ifpb.upcensus.presentation.module.module.request.ModuleRequest;
import br.edu.ifpb.upcensus.presentation.module.module.response.ModuleResponse;
import br.edu.ifpb.upcensus.presentation.module.template.input.mapper.FileTypeConverter;
import br.edu.ifpb.upcensus.presentation.module.template.input.mapper.InputTemplateMapper;
import br.edu.ifpb.upcensus.presentation.module.template.output.mapper.OutputTemplateMapper;

@Mapper(
		config = MapStructConfig.class,
		imports = FileTypeConverter.class,
		uses = {
			ModuleService.class,
			ConfigurationService.class,
			InputTemplateService.class,
			OutputTemplateService.class,
			ConfigurationMapper.class,
			InputTemplateMapper.class,
			OutputTemplateMapper.class,
			MetadataService.class,
			MetadataMapper.class
		}
	)
public interface ModuleMapper extends BaseMapper<Module, ModuleRequest, ModuleResponse>{

	@Mapping(target = "hasAnswers", expression = "java(model.hasAnswers())")
	@Mapping(target = "fileInputTemplateTypes", expression = "java(FileTypeConverter.modelSetToResponseSet(model.obtainFileInputTemplateTypes()))")
	ModuleResponse modelToResponse(Module model);
}
	