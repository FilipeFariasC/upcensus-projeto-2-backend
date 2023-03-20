package br.edu.ifpb.upcensus.business.form.field.resources;

import static br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.form.field.service.FieldServiceImpl;
import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.form.field.service.FieldService;
import br.edu.ifpb.upcensus.presentation.field.mapper.FieldMapper;
import br.edu.ifpb.upcensus.presentation.field.request.FieldRequest;
import br.edu.ifpb.upcensus.presentation.field.response.FieldResponse;

@RestController
@RequestMapping(FIELDS)
public class FieldResources {
	
	private final FieldService fieldService;
	private final FieldMapper fieldMapper;
	
	public FieldResources(
		final FieldServiceImpl fieldService,
		final FieldMapper fieldMapper
	) {
		super();
		this.fieldService = fieldService;
		this.fieldMapper = fieldMapper;
	}

	@GetMapping
	public ResponseEntity<Page<FieldResponse>> findAll(Pageable pageable) {
		Page<Field> fields = fieldService.findAll(pageable);
		Page<FieldResponse> responses = fields.map(fieldMapper::modelToResponse);
		
		return ResponseEntity.ok(responses);
	}
	
	@GetMapping("/{id}")
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
	@PutMapping("/{id}")
	public ResponseEntity<FieldResponse> register(@PathVariable Long id, @RequestBody FieldRequest request) {
		Field field = fieldMapper.requestToModel(request);
		Field registered = fieldService.update(id, field);
		FieldResponse response = fieldMapper.modelToResponse(registered);
		
		return ResponseEntity.ok(response);
	}
	
}
