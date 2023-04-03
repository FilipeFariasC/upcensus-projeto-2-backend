package br.edu.ifpb.upcensus.presentation.module.template.mapper;

import java.util.Map;

import org.mapstruct.Mapper;

import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.form.field.service.FieldService;
import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.form.field.mapper.FieldMapper;
import br.edu.ifpb.upcensus.presentation.module.template.request.TemplateRequest;
import br.edu.ifpb.upcensus.presentation.module.template.response.TemplateResponse;
@Mapper(
	config = MapStructConfig.class,
	uses = {
		FieldService.class,
		FieldMapper.class
	}
)
public interface TemplateMapper extends BaseMapper<Template, TemplateRequest, TemplateResponse> {
	Map<Field, String> fieldCodeMapToFieldMap(Map<String, String> mappings);
	Map<String, String> fieldMapToFieldResponseMap(Map<Field, String> mappings);
}
