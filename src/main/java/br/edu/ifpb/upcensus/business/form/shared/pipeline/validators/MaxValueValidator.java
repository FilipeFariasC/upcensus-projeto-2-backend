package br.edu.ifpb.upcensus.business.form.shared.pipeline.validators;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Type;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;

@Component
@Order(5)
public class MaxValueValidator extends CharacteristicValidator {

	public MaxValueValidator() {
		super(Attribute.MAX_VALUE);
	}
	

	@Override
	public boolean isValid(Answer answer) {
		if (ObjectUtils.isNull(answer.getValue()))
			return false;
		
		Type type = answer.getType();
		int min = getCharacteristic(answer).getValueAsInteger();
		
		if (type.isInteger())
			return answer.getValueAsInteger() <= min;
		
		if (type.isDecimal())
			return answer.getValueAsDouble() <= min;
			
		
		return false;
	}
}
