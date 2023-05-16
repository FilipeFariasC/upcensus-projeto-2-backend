package br.edu.ifpb.upcensus.business.form.shared.pipeline.validators;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic.Attribute;
import br.edu.ifpb.upcensus.domain.form.field.model.Type;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;

@Component
@Order(4)
public class MinValueValidator extends CharacteristicValidator {

	public MinValueValidator() {
		super(Attribute.MIN_VALUE);
	}
	

	@Override
	public boolean isValid(Answer answer) {
		if (ObjectUtils.isNull(answer.getValue()))
			return false;
		
		Type type = answer.getType();
		int min = getCharacteristic(answer).getValueAsInteger();
		
		if (type.isInteger())
			return answer.getValueAsInteger() >= min;
		
		if (type.isDecimal())
			return answer.getValueAsDouble() >= min;
			
		
		return false;
	}

}
