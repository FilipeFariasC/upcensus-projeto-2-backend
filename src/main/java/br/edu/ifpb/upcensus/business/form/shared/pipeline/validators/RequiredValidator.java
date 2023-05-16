package br.edu.ifpb.upcensus.business.form.shared.pipeline.validators;

import java.util.Objects;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;

@Component
@Order(2)
public class RequiredValidator extends PropertyValidator {

	public RequiredValidator() {
		super(Property.REQUIRED);
	}
	
	@Override
	public boolean isVerifiable(Answer answer) {
		return answer.isRequired();
	}

	@Override
	public boolean isValid(Answer answer) {
		return StringUtils.notBlank(answer.getValue());
	}

	@Override
	protected String getPropertyValue(Answer answer) {
		return Objects.toString(answer.isRequired());
	}
}
