package br.edu.ifpb.upcensus.presentation.form.field.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.form.field.service.PlainFieldService;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.form.characteristic.mapper.CharacteristicMapper;
import br.edu.ifpb.upcensus.presentation.form.field.request.PlainFieldRequest;
import br.edu.ifpb.upcensus.presentation.form.field.response.PlainFieldResponse;

@Mapper(
	config = MapStructConfig.class,
	uses = {
		PlainFieldService.class, 
		CharacteristicMapper.class,
		CharacteristicService.class
	}
)
public interface PlainFieldMapper extends BaseMapper<PlainField, PlainFieldRequest, PlainFieldResponse>
{
	@Mapping(target = "characteristics", source = "request.characteristics")
	PlainField requestToModel(PlainFieldRequest request);
	
	default String modelToCode(PlainField field) {
		return field.getCode();
	}
}
