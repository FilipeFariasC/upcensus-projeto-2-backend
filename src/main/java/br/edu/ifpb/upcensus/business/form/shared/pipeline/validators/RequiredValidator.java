package br.edu.ifpb.upcensus.business.form.shared.pipeline.validators;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;

@Component
@Order(2)
public class RequiredValidator extends CharacteristicValidator {

	public RequiredValidator() {
		super(Attribute.REQUIRED);
	}
	
	@Override
	public boolean isVerifiable(Answer answer) {
		return super.isVerifiable(answer) && Boolean.valueOf(getCharacteristic(answer).getValue());
	}

	@Override
	public boolean isValid(Answer answer) {
		return StringUtils.notBlank(answer.getValue());
	}
}
