package br.edu.ifpb.upcensus.business.form.shared.pipeline.validators;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;

@Component
@Order(5)
public class MinLengthValidator extends CharacteristicValidator {

	public MinLengthValidator() {
		super(Attribute.MIN_LENGTH);
	}
	

	@Override
	public boolean isValid(Answer answer) {
		if (ObjectUtils.isNull(answer.getValue()))
			return false;

		int min = getCharacteristic(answer).getValueAsInteger();
		
		return answer.getValue().length() >= min;
	}
}
