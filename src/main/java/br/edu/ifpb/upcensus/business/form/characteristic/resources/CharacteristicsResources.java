package br.edu.ifpb.upcensus.business.form.characteristic.resources;

import static br.edu.ifpb.upcensus.business.form.characteristic.resources.CharacteristicsEndpoints.ATTRIBUTES;
import static br.edu.ifpb.upcensus.business.form.characteristic.resources.CharacteristicsEndpoints.ATTRIBUTES_NAME;
import static br.edu.ifpb.upcensus.business.form.characteristic.resources.CharacteristicsEndpoints.CHARACTERISTICS;
import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.form.characteristic.service.CharacteristicServiceImpl;
import br.edu.ifpb.upcensus.business.shared.BaseCrudEndpoints;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.infrastructure.domain.EnumOption;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseServiceImpl;
import br.edu.ifpb.upcensus.presentation.characteristic.mapper.CharacteristicMapper;
import br.edu.ifpb.upcensus.presentation.characteristic.request.CharacteristicRequest;
import br.edu.ifpb.upcensus.presentation.characteristic.response.CharacteristicResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;

@RestController
@RequestMapping(CHARACTERISTICS)
public class CharacteristicsResources extends BaseCrudEndpoints<Characteristic, Long, CharacteristicRequest, CharacteristicResponse>{
	
	private final CharacteristicService characteristicService;
	private final CharacteristicMapper characteristicMapper;
	private final ResponseService responseService;
	
	public CharacteristicsResources(
		final CharacteristicServiceImpl characteristicService,
		final CharacteristicMapper characteristicMapper,
		final ResponseServiceImpl responseService
	) {
		super();
		this.characteristicService = characteristicService;
		this.characteristicMapper = characteristicMapper;
		this.responseService = responseService;
	}

	@GetMapping(ATTRIBUTES)
	@ResponseStatus(OK)
	public Response<List<EnumOption<Attribute>>> listAttribute() {
		List<EnumOption<Attribute>> attributes = Arrays.stream(Attribute.values())
			.map(EnumOption::new)
			.collect(Collectors.toList());
		
		return responseService.buildResponse(attributes, OK);
	}
	@GetMapping(ATTRIBUTES_NAME)
	@ResponseStatus(OK)
	public Response<EnumOption<Attribute>> getAttributeByName(@PathVariable String name) {
		Attribute attribute = Attribute.from(name);
		
		return responseService.buildResponse(new EnumOption<>(attribute), OK);
	}

	@Override
	protected ResponseService getResponseService() {
		return this.responseService;
	}

	@Override
	protected CharacteristicService getModelService() {
		// TODO Auto-generated method stub
		return this.characteristicService;
	}

	@Override
	protected CharacteristicMapper getDomainMapper() {
		return this.characteristicMapper;
	}
	
	
}
