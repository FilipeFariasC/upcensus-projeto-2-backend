package br.edu.ifpb.upcensus.business.form.shared.pipeline.validators;

import java.util.regex.Pattern;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic.Attribute;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.infrastructure.util.ObjectUtils;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;

@Component
@Order(3)
public class PatternValidator extends CharacteristicValidator {

	public PatternValidator() {
		super(Attribute.PATTERN);
	}

	@Override
	public boolean isValid(Answer answer) {
		if (ObjectUtils.isNull(answer.getValue()))
			return false;

		Pattern pattern = getCharacteristic(answer).getValueAsPattern();
		String value = answer.getValue();
	
		return StringUtils.isEmpty(value) || 
			pattern
				.matcher(value)
				.find();
	}
}
