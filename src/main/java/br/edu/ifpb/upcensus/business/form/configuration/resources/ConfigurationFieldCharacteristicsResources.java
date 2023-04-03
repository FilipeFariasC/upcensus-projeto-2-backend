package br.edu.ifpb.upcensus.business.form.configuration.resources;

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

import br.edu.ifpb.upcensus.business.shared.ApiEndpoints;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.form.configuration.service.ConfigurationService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.presentation.form.characteristic.mapper.CharacteristicMapper;
import br.edu.ifpb.upcensus.presentation.form.characteristic.response.CharacteristicResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;


@RestController
@RequestMapping(ConfigurationEndpoints.CONFIGURATION_FIELD_CHARACTERISTICS)
public class ConfigurationFieldCharacteristicsResources {

	private final ResponseService responseService;
	private final ConfigurationService configurationService;
	private final CharacteristicMapper characteristicMapper;
	
	
	public ConfigurationFieldCharacteristicsResources(
		final ResponseService responseService,
		final ConfigurationService configurationService,
		final CharacteristicMapper characteristicMapper
	) {
		this.responseService = responseService;
		this.configurationService = configurationService;
		this.characteristicMapper = characteristicMapper;	
	}
	

	@GetMapping
	@ResponseStatus(OK)
	public Response<Set<CharacteristicResponse>> getConfigurationFieldCharacteristics(
			@PathVariable Long id,
			@PathVariable Long idField
		) {
		Set<Characteristic> registered = configurationService.findAllCharacteristicsByConfigurationIdAndConfigurationFieldId(id, idField);
		Set<CharacteristicResponse> response = characteristicMapper.modelSetToResponseSet(registered);
		
		return responseService.buildResponse(response, OK);
	}
	
	@PatchMapping(ApiEndpoints.ADD)
	@ResponseStatus(OK)
	public Response<Set<CharacteristicResponse>> addConfigurationFieldCharacteristics(
			@PathVariable Long id,
			@PathVariable Long idField,
			@RequestBody Set<Long> characteristics
		) {
		Set<Characteristic> configurationFields= configurationService.addConfigurationFieldCharacteristics(id, idField, characteristics);
		Set<CharacteristicResponse> response = characteristicMapper.modelSetToResponseSet(configurationFields);
		
		return responseService.buildResponse(response, OK);
	}
	
	@PatchMapping(ApiEndpoints.REMOVE)
	@ResponseStatus(OK)
	public Response<Set<CharacteristicResponse>> removeCharacteristics(
			@PathVariable Long id, 
			@PathVariable Long idField,
			@RequestBody List<Long> ids
		) {
		Set<Characteristic> configurationFields= configurationService.removeConfigurationFieldCharacteristics(id, idField, ids);
		Set<CharacteristicResponse> response = characteristicMapper.modelSetToResponseSet(configurationFields);
		
		return responseService.buildResponse(response, OK);
	}

}
