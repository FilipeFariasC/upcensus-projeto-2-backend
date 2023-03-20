package br.edu.ifpb.upcensus.presentation.field.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.form.field.service.FieldService;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.characteristic.mapper.CharacteristicMapper;
import br.edu.ifpb.upcensus.presentation.field.request.FieldRequest;
import br.edu.ifpb.upcensus.presentation.field.response.FieldResponse;

@Mapper(
	config = MapStructConfig.class,
	uses = {
		FieldService.class, 
		CharacteristicMapper.class,
		CharacteristicService.class
	}
)
public interface FieldMapper extends BaseMapper<Field, FieldRequest, FieldResponse>
{
	@Override
	@Mapping(target = "characteristics", source = "request.characteristics")
	Field requestToModel(FieldRequest request);
	
}
