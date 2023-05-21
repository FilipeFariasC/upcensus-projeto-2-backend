package br.edu.ifpb.upcensus.presentation.form.field.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.form.field.service.FieldService;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.form.characteristic.mapper.CharacteristicMapper;
import br.edu.ifpb.upcensus.presentation.form.field.request.FieldRequest;
import br.edu.ifpb.upcensus.presentation.form.field.response.FieldResponse;

@Mapper(
	config = MapStructConfig.class,
	uses = {
		FieldService.class, 
		CharacteristicMapper.class,
		CharacteristicService.class
	}
)
public interface FieldMapper extends BaseMapper<PlainField, FieldRequest, FieldResponse>
{
	@Mapping(target = "characteristics", source = "request.characteristics")
	PlainField requestToModel(FieldRequest request);
	
	default String modelToCode(PlainField field) {
		return field.getCode();
	}
}
