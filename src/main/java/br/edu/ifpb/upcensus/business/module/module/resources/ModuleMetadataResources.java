package br.edu.ifpb.upcensus.business.module.module.resources;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.shared.ApiEndpoints;
import br.edu.ifpb.upcensus.business.shared.BaseCrudResource;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.module.service.ModuleService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.presentation.file.request.FileRequest;
import br.edu.ifpb.upcensus.presentation.module.module.mapper.AnswerMapper;
import br.edu.ifpb.upcensus.presentation.module.module.mapper.ModuleMapper;
import br.edu.ifpb.upcensus.presentation.module.module.request.AnswerRequest;
import br.edu.ifpb.upcensus.presentation.module.module.request.ModuleRequest;
import br.edu.ifpb.upcensus.presentation.module.module.response.AnswerResponse;
import br.edu.ifpb.upcensus.presentation.module.module.response.ModuleResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;

@RestController
@RequestMapping(ModuleEndpoints.MODULE_METADATA)
public class ModuleMetadataResources extends BaseCrudResource<Module, Long, ModuleRequest, ModuleResponse>{

	private final ModuleService moduleService;
	private final ModuleMapper moduleMapper;
	private final ResponseService responseService;
	private final AnswerMapper answerMapper;

	public ModuleMetadataResources(
			final ModuleService moduleService,
			final ModuleMapper moduleMapper,
			final ResponseService responseService,
			final AnswerMapper answerMapper
	) {
		
		this.moduleMapper = moduleMapper;
		this.moduleService = moduleService;
		this.responseService = responseService;
		this.answerMapper = answerMapper;
	}
	
	@GetMapping(ApiEndpoints.ID + "/answers")
	@ResponseStatus(HttpStatus.OK)
	public Response<Map<String, Map<String, String>>> getAnswersModule(@PathVariable Long id) {
		Module module = moduleService.findById(id);
		Map<String, Map<String, String>> answers = answerMapper.modelsToMap(module.getAnswers());
		
		return responseService.buildResponse(answers, HttpStatus.OK);
	}
	
	@PostMapping(ApiEndpoints.ID+"/upload")
	@ResponseStatus(CREATED)
	public Response<?> uploadFileToModule(@PathVariable Long id, FileRequest request){
		moduleService.uploadFile(id, request.getFile(), request.isIgnoreHeaderRow(), request.getFileType(), request.getDelimiter());
		
		return null;
	}
	
	@PostMapping(ApiEndpoints.ID+"/migrate")
	@ResponseStatus(CREATED)
	public Response<?> migrateModule(@PathVariable Long id){
		moduleService.migrate(id);
		
		return null;
	}

	
	@PostMapping("/responses")
	@ResponseStatus(CREATED)
	public Response<AnswerResponse> uploadFileToModule(@PathVariable Long id, @PathVariable Long idTemplate, @RequestBody AnswerRequest request) {
		moduleService.registerAnswers(id, idTemplate, request.getAnswers());
		Set<Answer> answers = moduleService.findById(id).getAnswers(idTemplate);
		return responseService.buildResponse(answerMapper.modelsToResponse(answers), HttpStatus.OK);
	}

	@Override
	protected ResponseService getResponseService() {
		return this.responseService;
	}

	@Override
	protected ModuleService getModelService() {
		return this.moduleService;
	}

	@Override
	protected ModuleMapper getDomainMapper() {
		return this.moduleMapper;
	}

}
