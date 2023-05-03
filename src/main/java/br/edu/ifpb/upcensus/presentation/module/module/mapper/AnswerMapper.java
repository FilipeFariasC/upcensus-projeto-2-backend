package br.edu.ifpb.upcensus.presentation.module.module.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.module.service.ModuleService;
import br.edu.ifpb.upcensus.domain.module.template.service.TemplateService;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;
import br.edu.ifpb.upcensus.presentation.module.module.response.AnswerResponse;
import br.edu.ifpb.upcensus.presentation.module.template.mapper.TemplateMapper;

@Mapper(
		config = MapStructConfig.class,
		uses = {
			ModuleService.class,
			TemplateService.class,
			ModuleMapper.class,
			TemplateMapper.class
		}
	)
public interface AnswerMapper {
	
	default AnswerResponse modelsToResponse(Set<Answer> answers) {
		if (CollectionUtils.isEmpty(answers))
			return null;
		Map<String, List<Answer>> ans = answers
			.stream()
			.collect(
				Collectors.groupingBy(Answer::getIdentifier)
			);
		
		AnswerResponse response = new AnswerResponse();
		response.setAnswers(ans
			.entrySet()
			.stream()
			.collect(Collectors.toMap(Map.Entry::getKey, value ->{
				return  value.getValue()
					.stream()
					.collect(Collectors.toMap(answer -> answer.getField().getCode(), Answer::getValue));
			})));
		
		return response;
	}
	
}
	