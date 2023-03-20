package br.edu.ifpb.upcensus.presentation.characteristic.mapper;

import org.mapstruct.Mapper;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.characteristic.request.CharacteristicRequest;
import br.edu.ifpb.upcensus.presentation.characteristic.response.CharacteristicResponse;

@Mapper(
	imports = Attribute.class,
	config = MapStructConfig.class,
	uses = CharacteristicService.class
)
public interface CharacteristicMapper extends BaseMapper<Characteristic, CharacteristicRequest, CharacteristicResponse> {}
