package br.edu.ifpb.upcensus.business.module.module.resources;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.domain.module.module.service.ModuleService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.presentation.module.module.mapper.AnswerMapper;
import br.edu.ifpb.upcensus.presentation.module.module.mapper.ModuleMapper;
import br.edu.ifpb.upcensus.presentation.module.module.request.AnswerRequest;
import br.edu.ifpb.upcensus.presentation.module.module.response.AnswerResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;

@RestController
@RequestMapping(ModuleEndpoints.MODULE_TEMPLATE)
public class AnswerResources {

	private final ModuleService moduleService;
	private final AnswerMapper answerMapper;
	private final ResponseService responseService;

	public AnswerResources(
			final ModuleService moduleService,
			final AnswerMapper answerMapper,
			final ResponseService responseService
	) {
		
		this.moduleService = moduleService;
		this.answerMapper = answerMapper;
		this.responseService = responseService;
	}
	
	@PostMapping("/responses")
	@ResponseStatus(CREATED)
	public Response<AnswerResponse> uploadFileToModule(@PathVariable Long id, @PathVariable Long idTemplate, @RequestBody AnswerRequest request) {
		moduleService.registerAnswers(id, idTemplate, request.getAnswers());
		Set<Answer> answers = moduleService.findById(id).getAnswers(idTemplate);
		return responseService.buildResponse(answerMapper.modelsToResponse(answers), HttpStatus.OK);
	}


}
