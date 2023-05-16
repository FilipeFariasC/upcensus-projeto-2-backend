package br.edu.ifpb.upcensus.business.form.field.resources;

import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELDS_ABSOLUTE;
import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELD_CHARACTERISTICS;
import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELD_CHARACTERISTICS_ADD;
import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.FIELD_CHARACTERISTICS_REMOVE;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.form.field.service.FieldServiceImpl;
import br.edu.ifpb.upcensus.business.shared.BaseCrudResource;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.field.model.PlainField;
import br.edu.ifpb.upcensus.domain.form.field.service.FieldService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseServiceImpl;
import br.edu.ifpb.upcensus.presentation.form.characteristic.mapper.CharacteristicMapper;
import br.edu.ifpb.upcensus.presentation.form.characteristic.response.CharacteristicResponse;
import br.edu.ifpb.upcensus.presentation.form.field.mapper.FieldMapper;
import br.edu.ifpb.upcensus.presentation.form.field.request.FieldRequest;
import br.edu.ifpb.upcensus.presentation.form.field.response.FieldResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;

@RestController
@RequestMapping(FIELDS_ABSOLUTE)
public class FieldResources extends BaseCrudResource<PlainField, Long, FieldRequest, FieldResponse>{
	
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
	protected FieldService getModelService() {
		return this.fieldService;
	}

	@Override
	protected FieldMapper getDomainMapper() {
		return this.fieldMapper;
	}
	
	
	
}
