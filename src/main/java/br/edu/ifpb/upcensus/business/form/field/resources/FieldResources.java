package br.edu.ifpb.upcensus.business.form.field.resources;

import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELD;
import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELDS_ABSOLUTE;
import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELD_CHARACTERISTICS;
import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELD_CHARACTERISTICS_ADD;
import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELD_CHARACTERISTICS_REMOVE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.form.field.service.FieldServiceImpl;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.form.field.service.FieldService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseServiceImpl;
import br.edu.ifpb.upcensus.presentation.characteristic.mapper.CharacteristicMapper;
import br.edu.ifpb.upcensus.presentation.characteristic.response.CharacteristicResponse;
import br.edu.ifpb.upcensus.presentation.field.mapper.FieldMapper;
import br.edu.ifpb.upcensus.presentation.field.request.FieldRequest;
import br.edu.ifpb.upcensus.presentation.field.response.FieldResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;

@RestController
@RequestMapping(FIELDS_ABSOLUTE)
public class FieldResources {
	
	private final FieldService fieldService;
	private final FieldMapper fieldMapper;
	private final CharacteristicMapper characteristicMapper;
	private final ResponseService responseService;
	
	public FieldResources(
		final FieldServiceImpl fieldService,
		final FieldMapper fieldMapper,
		final CharacteristicMapper characteristicMapper,
		final ResponseServiceImpl responseService
	) {
		super();
		this.fieldService = fieldService;
		this.fieldMapper = fieldMapper;
		this.characteristicMapper = characteristicMapper;
		this.responseService = responseService;
	}

	@GetMapping
	@ResponseStatus(OK)
	public Response<Page<FieldResponse>> findAll(Pageable pageable) {
		Page<Field> fields = fieldService.findAll(pageable);
		Page<FieldResponse> responses = fields.map(fieldMapper::modelToResponse);
		
		return responseService.buildResponse(responses, OK);
	}
	
	@GetMapping(FIELD)
	@ResponseStatus(OK)
	public Response<FieldResponse> findById(@PathVariable Long id) {
		Field field = fieldService.findById(id);
		FieldResponse response = fieldMapper.modelToResponse(field);
		
		return responseService.buildResponse(response, OK);
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Response<FieldResponse> register(@RequestBody FieldRequest request) {
		Field field = fieldMapper.requestToModel(request);
		Field registered = fieldService.register(field);
		FieldResponse response = fieldMapper.modelToResponse(registered);
		
		return responseService.buildResponse(response, CREATED, FIELD, field.getId());
	}
	
	@PutMapping(FIELD)
	@ResponseStatus(OK)
	public Response<FieldResponse> update(@PathVariable Long id, @RequestBody FieldRequest request) {
		Field field = fieldMapper.requestToModel(request);
		Field registered = fieldService.update(id, field);
		FieldResponse response = fieldMapper.modelToResponse(registered);
		
		return responseService.buildResponse(response, OK);
	}
	
	@GetMapping(FIELD_CHARACTERISTICS)
	@ResponseStatus(OK)
	public Response<List<CharacteristicResponse>> getFieldCharacteristics(
			@PathVariable Long id
		) {
		Field registered = fieldService.findById(id);
		List<CharacteristicResponse> response = characteristicMapper.modelListToResponseList(registered.getCharacteristics());
		
		return responseService.buildResponse(response, OK);
	}
	
	@PatchMapping(FIELD_CHARACTERISTICS_ADD)
	@ResponseStatus(OK)
	public Response<List<CharacteristicResponse>> addFieldCharacteristics(
			@PathVariable Long id, 
			@RequestBody List<Long> ids
		) {
		List<Characteristic> characteristics= fieldService.addFieldCharacteristics(id, ids);
		List<CharacteristicResponse> response = characteristicMapper.modelListToResponseList(characteristics);
		
		return responseService.buildResponse(response, OK);
	}
	
	@PatchMapping(FIELD_CHARACTERISTICS_REMOVE)
	@ResponseStatus(OK)
	public Response<List<CharacteristicResponse>> removeFieldCharacteristics(
			@PathVariable Long id, 
			@RequestBody List<Long> ids
		) {
		List<Characteristic> characteristics= fieldService.removeFieldCharacteristics(id, ids);
		List<CharacteristicResponse> response = characteristicMapper.modelListToResponseList(characteristics);
		
		return responseService.buildResponse(response, OK);
	}
	
}
