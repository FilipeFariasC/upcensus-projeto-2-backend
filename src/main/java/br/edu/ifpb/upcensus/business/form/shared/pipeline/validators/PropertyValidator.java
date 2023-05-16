package br.edu.ifpb.upcensus.business.form.shared.pipeline.validators;

import java.text.MessageFormat;

import br.edu.ifpb.upcensus.business.form.shared.pipeline.AnswerValidator;
import br.edu.ifpb.upcensus.domain.module.error.model.AnswerError.Motive;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.domain.shared.model.DomainEnum;

public abstract class PropertyValidator implements AnswerValidator {

	private final Property property;
	
	public PropertyValidator(Property property) {
		super();
		this.property = property;
	}
	
	protected Property getProperty() {
		return property;
	}
	
	
	@Override
	public Motive getMotive() {
		return Motive.VALUE_CONDITION;
	}
	
	@Override
	public String errorMessage(Answer answer) {
		return MessageFormat.format("Campo {0}: {1} -> {2}. Valor inserido: {3}",
				answer.getField().getCode(), 
				getProperty().getLabel(),
				getPropertyValue(answer),
				answer.getValue()
			);
	}
	
	protected abstract String getPropertyValue(Answer answer);
	
	public static enum Property implements DomainEnum<Property> {
		REQUIRED("Obrigatoriedade"),
		TYPE("Tipo");
		
		private final String label;
		
		private Property(String label) {
			this.label = label;
		}

		@Override
		public String getLabel() {
			return label;
		}

		@Override
		public Property getValue() {
			return this;
		}
		
		
	}
	
}
