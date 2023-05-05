package br.edu.ifpb.upcensus.business.form.shared.pipeline;

import br.edu.ifpb.upcensus.domain.module.error.model.AnswerError;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;

public interface AnswerValidator {
	
	default boolean validate(Answer answer) {
		return !isVerifiable(answer) || isValid(answer);
	}
	boolean isVerifiable(Answer answer);
	boolean isValid(Answer answer);
	
	AnswerError.Motive getMotive();
	
	String errorMessage(Answer answer);
}
