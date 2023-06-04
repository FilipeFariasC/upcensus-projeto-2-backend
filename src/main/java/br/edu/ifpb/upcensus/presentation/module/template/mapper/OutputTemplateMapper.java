package br.edu.ifpb.upcensus.presentation.module.template.mapper;

import org.mapstruct.Mapper;

import br.edu.ifpb.upcensus.domain.module.template.model.OutputTemplate;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.module.template.request.OutputTemplateRequest;
import br.edu.ifpb.upcensus.presentation.module.template.response.OutputTemplateResponse;

@Mapper(config = MapStructConfig.class)
public interface OutputTemplateMapper extends BaseMapper<OutputTemplate, OutputTemplateRequest, OutputTemplateResponse> { }
