package br.edu.ifpb.upcensus.business.form.field.resources;

import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.form.field.service.FieldServiceImpl;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.form.field.service.FieldService;
import br.edu.ifpb.upcensus.presentation.characteristic.mapper.CharacteristicMapper;
import br.edu.ifpb.upcensus.presentation.characteristic.response.CharacteristicResponse;
import br.edu.ifpb.upcensus.presentation.field.mapper.FieldMapper;
import br.edu.ifpb.upcensus.presentation.field.request.FieldRequest;
import br.edu.ifpb.upcensus.presentation.field.response.FieldResponse;

@RestController
@RequestMapping(FIELDS_ABSOLUTE)
public class FieldResources {
	
	private final FieldService fieldService;
	private final FieldMapper fieldMapper;
	private final CharacteristicMapper characteristicMapper;
	
	public FieldResources(
		final FieldServiceImpl fieldService,
		final FieldMapper fieldMapper,
		final CharacteristicMapper characteristicMapper
	) {
		super();
		this.fieldService = fieldService;
		this.fieldMapper = fieldMapper;
		this.characteristicMapper = characteristicMapper;
	}

	@GetMapping
	public ResponseEntity<Page<FieldResponse>> findAll(Pageable pageable) {
		Page<Field> fields = fieldService.findAll(pageable);
		Page<FieldResponse> responses = fields.map(fieldMapper::modelToResponse);
		
		return ResponseEntity.ok(responses);
	}
	
	@GetMapping(FIELD)
	public ResponseEntity<FieldResponse> findById(@PathVariable Long id) {
		Field field = fieldService.findById(id);
		FieldResponse response = fieldMapper.modelToResponse(field);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<FieldResponse> register(@RequestBody FieldRequest request) {
		Field field = fieldMapper.requestToModel(request);
		Field registered = fieldService.register(field);
		FieldResponse response = fieldMapper.modelToResponse(registered);
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping(FIELD)
	public ResponseEntity<FieldResponse> update(@PathVariable Long id, @RequestBody FieldRequest request) {
		Field field = fieldMapper.requestToModel(request);
		Field registered = fieldService.update(id, field);
		FieldResponse response = fieldMapper.modelToResponse(registered);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(FIELD_CHARACTERISTICS)
	public ResponseEntity<List<CharacteristicResponse>> getFieldCharacteristics(
			@PathVariable Long id
		) {
		Field registered = fieldService.findById(id);
		List<CharacteristicResponse> response = characteristicMapper.modelListToResponseList(registered.getCharacteristics());
		
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping(FIELD_CHARACTERISTICS_ADD)
	public ResponseEntity<List<CharacteristicResponse>> addFieldCharacteristics(
			@PathVariable Long id, 
			@RequestBody List<Long> ids
		) {
		List<Characteristic> characteristics= fieldService.addFieldCharacteristics(id, ids);
		List<CharacteristicResponse> response = characteristicMapper.modelListToResponseList(characteristics);
		
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping(FIELD_CHARACTERISTICS_REMOVE)
	public ResponseEntity<List<CharacteristicResponse>> removeFieldCharacteristics(
			@PathVariable Long id, 
			@RequestBody List<Long> ids
		) {
		List<Characteristic> characteristics= fieldService.removeFieldCharacteristics(id, ids);
		List<CharacteristicResponse> response = characteristicMapper.modelListToResponseList(characteristics);
		
		return ResponseEntity.ok(response);
	}
	
}
