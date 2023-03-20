package br.edu.ifpb.upcensus.business.form.characteristic.resources;

import static br.edu.ifpb.upcensus.business.form.characteristic.resources.CharacteristicsEndpoints.*;

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

import br.edu.ifpb.upcensus.business.form.characteristic.service.CharacteristicServiceImpl;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.presentation.characteristic.mapper.CharacteristicMapper;
import br.edu.ifpb.upcensus.presentation.characteristic.request.CharacteristicRequest;
import br.edu.ifpb.upcensus.presentation.characteristic.response.CharacteristicResponse;

@RestController
@RequestMapping(CHARACTERISTICS)
public class CharacteristicsResources {
	
	private final CharacteristicService characteristicService;
	private final CharacteristicMapper characteristicMapper;
	
	public CharacteristicsResources(
		final CharacteristicServiceImpl characteristicService,
		final CharacteristicMapper characteristicMapper
	) {
		super();
		this.characteristicService = characteristicService;
		this.characteristicMapper = characteristicMapper;
	}

	@GetMapping
	public ResponseEntity<Page<CharacteristicResponse>> findAll(Pageable pageable) {
		Page<Characteristic> characteristics = characteristicService.findAll(pageable);
		Page<CharacteristicResponse> responses = characteristics.map(characteristicMapper::modelToResponse);
		
		return ResponseEntity.ok(responses);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CharacteristicResponse> findById(@PathVariable Long id) {
		Characteristic characteristic = characteristicService.findById(id);
		CharacteristicResponse response = characteristicMapper.modelToResponse(characteristic);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<CharacteristicResponse> register(@RequestBody CharacteristicRequest request) {
		Characteristic characteristic = characteristicMapper.requestToModel(request);
		Characteristic registered = characteristicService.register(characteristic);
		CharacteristicResponse response = characteristicMapper.modelToResponse(registered);
		
		return ResponseEntity.ok(response);
	}
	@PutMapping("/{id}")
	public ResponseEntity<CharacteristicResponse> register(@PathVariable Long id, @RequestBody CharacteristicRequest request) {
		Characteristic characteristic = characteristicMapper.requestToModel(request);
		Characteristic registered = characteristicService.update(id, characteristic);
		CharacteristicResponse response = characteristicMapper.modelToResponse(registered);
		
		return ResponseEntity.ok(response);
	}
	
}
