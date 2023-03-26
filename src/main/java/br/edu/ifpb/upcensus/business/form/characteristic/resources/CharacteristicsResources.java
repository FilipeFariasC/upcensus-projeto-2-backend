package br.edu.ifpb.upcensus.business.form.characteristic.resources;

import static br.edu.ifpb.upcensus.business.form.characteristic.resources.CharacteristicsEndpoints.CHARACTERISTIC;
import static br.edu.ifpb.upcensus.business.form.characteristic.resources.CharacteristicsEndpoints.CHARACTERISTICS;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.form.characteristic.service.CharacteristicServiceImpl;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.characteristic.service.CharacteristicService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseServiceImpl;
import br.edu.ifpb.upcensus.presentation.characteristic.mapper.CharacteristicMapper;
import br.edu.ifpb.upcensus.presentation.characteristic.request.CharacteristicRequest;
import br.edu.ifpb.upcensus.presentation.characteristic.response.CharacteristicResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;

@RestController
@RequestMapping(CHARACTERISTICS)
public class CharacteristicsResources {
	
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

	@GetMapping
	@ResponseStatus(OK)
	public Response<Page<CharacteristicResponse>> findAll(Pageable pageable) {
		Page<Characteristic> characteristics = characteristicService.findAll(pageable);
		Page<CharacteristicResponse> responses = characteristics.map(characteristicMapper::modelToResponse);
		
		return responseService.buildResponse(responses, OK);
	}
	
	@GetMapping(CHARACTERISTIC)
	@ResponseStatus(OK)
	public Response<CharacteristicResponse> findById(@PathVariable Long id) {
		Characteristic characteristic = characteristicService.findById(id);
		CharacteristicResponse response = characteristicMapper.modelToResponse(characteristic);
		
		return responseService.buildResponse(response, OK);
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Response<CharacteristicResponse> register(@RequestBody CharacteristicRequest request) {
		Characteristic characteristic = characteristicMapper.requestToModel(request);
		Characteristic registered = characteristicService.register(characteristic);
		CharacteristicResponse response = characteristicMapper.modelToResponse(registered);
		
		return responseService.buildResponse(response, CREATED, CHARACTERISTIC, characteristic.getId());
	}
	
	@PutMapping(CHARACTERISTIC)
	@ResponseStatus(OK)
	public Response<CharacteristicResponse> update(@PathVariable Long id, @RequestBody CharacteristicRequest request) {
		Characteristic characteristic = characteristicMapper.requestToModel(request);
		Characteristic registered = characteristicService.update(id, characteristic);
		CharacteristicResponse response = characteristicMapper.modelToResponse(registered);
		
		return responseService.buildResponse(response, OK);
	}
	
	@DeleteMapping(CHARACTERISTIC)
	@ResponseStatus(NO_CONTENT)
	public Response<?> delete(@PathVariable Long id) {
		this.characteristicService.deleteById(id);
		
		return null;
	}
	
}
