package br.edu.ifpb.upcensus.business.form.field.resources;

import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELDS_ABSOLUTE;
import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELD_CHARACTERISTICS;
import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELD_CHARACTERISTICS_ADD;
import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELD_CHARACTERISTICS_REMOVE;
import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELD_TYPE;
import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELD_TYPES;
import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.form.field.service.PlainFieldServiceImpl;
import br.edu.ifpb.upcensus.business.shared.BaseCrudResource;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.form.field.model.Type;
import br.edu.ifpb.upcensus.domain.form.field.service.PlainFieldService;
import br.edu.ifpb.upcensus.infrastructure.domain.EnumOption;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseServiceImpl;
import br.edu.ifpb.upcensus.presentation.form.characteristic.mapper.CharacteristicMapper;
import br.edu.ifpb.upcensus.presentation.form.characteristic.response.CharacteristicResponse;
import br.edu.ifpb.upcensus.presentation.form.field.mapper.PlainFieldMapper;
import br.edu.ifpb.upcensus.presentation.form.field.request.PlainFieldRequest;
import br.edu.ifpb.upcensus.presentation.form.field.response.PlainFieldResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;

@RestController
@RequestMapping(FIELDS_ABSOLUTE)
public class FieldResources extends BaseCrudResource<PlainField, Long, PlainFieldRequest, PlainFieldResponse>{
	
	private final PlainFieldService fieldService;
	private final PlainFieldMapper fieldMapper;
	private final CharacteristicMapper characteristicMapper;
	private final ResponseService responseService;
	
	public FieldResources(
		final PlainFieldServiceImpl fieldService,
		final PlainFieldMapper fieldMapper,
		final CharacteristicMapper characteristicMapper,
		final ResponseServiceImpl responseService
	) {
		super();
		this.fieldService = fieldService;
		this.fieldMapper = fieldMapper;
		this.characteristicMapper = characteristicMapper;
		this.responseService = responseService;
	}
	


	@GetMapping(FIELD_TYPES)
	@ResponseStatus(OK)
	public Response<List<EnumOption<Type>>> listTypes() {
		List<EnumOption<Type>> attributes = Arrays.stream(Type.values())
			.map(EnumOption::new)
			.collect(Collectors.toList());
		
		return responseService.buildResponse(attributes, OK);
	}
	
	@GetMapping(FIELD_TYPE)
	@ResponseStatus(OK)
	public Response<EnumOption<Type>> getTypeByName(@PathVariable("type") String name) {
		Type type = Type.from(name);
		
		return responseService.buildResponse(new EnumOption<>(type), OK);
	}
	
	@GetMapping(FIELD_CHARACTERISTICS)
	@ResponseStatus(OK)
	public Response<Set<CharacteristicResponse>> getFieldCharacteristics(
			@PathVariable Long id
		) {
		PlainField registered = fieldService.findById(id);
		Set<CharacteristicResponse> response = characteristicMapper.modelSetToResponseSet(registered.getCharacteristics());
		
		return responseService.buildResponse(response, OK);
	}
	
	@PatchMapping(FIELD_CHARACTERISTICS_ADD)
	@ResponseStatus(OK)
	public Response<Set<CharacteristicResponse>> addFieldCharacteristics(
			@PathVariable Long id, 
			@RequestBody List<Long> ids
		) {
		Set<Characteristic> characteristics= fieldService.addFieldCharacteristics(id, ids);
		Set<CharacteristicResponse> response = characteristicMapper.modelSetToResponseSet(characteristics);
		
		return responseService.buildResponse(response, OK);
	}
	
	@PatchMapping(FIELD_CHARACTERISTICS_REMOVE)
	@ResponseStatus(OK)
	public Response<Set<CharacteristicResponse>> removeFieldCharacteristics(
			@PathVariable Long id, 
			@RequestBody List<Long> ids
		) {
		Set<Characteristic> characteristics= fieldService.removeFieldCharacteristics(id, ids);
		Set<CharacteristicResponse> response = characteristicMapper.modelSetToResponseSet(characteristics);
		
		return responseService.buildResponse(response, OK);
	}

	@Override
	protected ResponseService getResponseService() {
		return this.responseService;
	}

	@Override
	protected PlainFieldService getModelService() {
		return this.fieldService;
	}

	@Override
	protected PlainFieldMapper getDomainMapper() {
		return this.fieldMapper;
	}
	
	
	
}
