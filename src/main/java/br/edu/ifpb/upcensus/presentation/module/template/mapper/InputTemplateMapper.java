package br.edu.ifpb.upcensus.presentation.module.template.mapper;

import java.util.Map;

import org.mapstruct.Mapper;

import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.form.field.service.FieldService;
import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.form.field.mapper.FieldMapper;
import br.edu.ifpb.upcensus.presentation.module.template.request.InputTemplateRequest;
import br.edu.ifpb.upcensus.presentation.module.template.response.InputTemplateResponse;
@Mapper(
	config = MapStructConfig.class,
	uses = {
		FieldService.class,
		FieldMapper.class
	}
)
public interface InputTemplateMapper extends BaseMapper<InputTemplate, InputTemplateRequest, InputTemplateResponse> {
	Map<PlainField, String> fieldCodeMapToFieldMap(Map<String, String> mappings);
	Map<String, String> fieldMapToFieldResponseMap(Map<PlainField, String> mappings);
}
