package br.edu.ifpb.upcensus.business.form.shared.pipeline;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;

@Component
public class ValidationPipeline {
	
    private final List<AnswerValidator> validators;

    public ValidationPipeline(final List<AnswerValidator> validators) {
        this.validators = validators;
    }


    public void validate(Set<Answer> answers) {
    	CollectionUtils.forEach(answers, this::validate);
    }

    public void validate(Answer answer) {
    	CollectionUtils.forEach(validators, validator -> validateAndSetError(answer, validator));
    }
    
    private void validateAndSetError(Answer answer, AnswerValidator validator) {
    	boolean isValid = validator.validate(answer);
    	if (!isValid) 
    		answer.addError(validator.getMotive(), validator.errorMessage(answer));
    	
    }

}