package br.edu.ifpb.upcensus.business.form.shared.pipeline.validators;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.ifpb.upcensus.domain.form.field.model.Type;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;
import br.edu.ifpb.upcensus.infrastructure.util.TimeUtils;

@Component
@Order(1)
public class TypeValidator extends PropertyValidator {

	public TypeValidator() {
		super(Property.TYPE);
	}

	@Override
	public boolean isValid(Answer answer) {
		if (StringUtils.isEmpty(answer.getValue()))
			return false;
		
		Type type = answer.getType();
		
		if (!type.match(answer.getValue())) 
			return false;
		
		if (Type.DATE.equals(type)) 
			return TimeUtils.isValidDate(answer.getValue());
		
		if (Type.TIMESTAMP.equals(type)) 
			return TimeUtils.isValidTimestamp(answer.getValue());
		
		
		return true;
	}
	
	@Override
	protected String getPropertyValue(Answer answer) {
		return answer.getType().getLabel();
	}

	@Override
	public boolean isVerifiable(Answer answer) {
		return StringUtils.notBlank(answer.getValue());
	}
}
