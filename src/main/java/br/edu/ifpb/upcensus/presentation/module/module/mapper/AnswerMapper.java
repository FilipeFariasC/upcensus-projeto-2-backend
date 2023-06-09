package br.edu.ifpb.upcensus.presentation.module.module.mapper;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.domain.module.module.service.ModuleService;
import br.edu.ifpb.upcensus.domain.module.template.service.InputTemplateService;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;
import br.edu.ifpb.upcensus.presentation.module.module.response.AnswerResponse;
import br.edu.ifpb.upcensus.presentation.module.template.input.mapper.InputTemplateMapper;

@Mapper(
		config = MapStructConfig.class,
		uses = {
			ModuleService.class,
			InputTemplateService.class,
			ModuleMapper.class,
			InputTemplateMapper.class
		}
	)
public interface AnswerMapper {
	
	default AnswerResponse modelsToResponse(Set<Answer> answers) {
		if (CollectionUtils.isEmpty(answers))
			return null;

		AnswerResponse response = new AnswerResponse();
		response.setAnswers(modelsToMap(answers));
		
		return response;
	}
	
	default Map<String, Map<String, String>> modelsToMap(Set<Answer> answers) {
		if (CollectionUtils.isEmpty(answers))
			return null;
		return answers
			.stream()
			.collect(
				Collectors.groupingBy(Answer::getIdentifierValue, Collectors.toMap(answer -> answer.getField().getCode(), Answer::getValue))
			);
	}
}
	