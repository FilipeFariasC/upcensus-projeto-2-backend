package br.edu.ifpb.upcensus.business.form.shared.pipeline.validators;

import java.text.MessageFormat;

import br.edu.ifpb.upcensus.business.form.shared.pipeline.AnswerValidator;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic;
import br.edu.ifpb.upcensus.domain.module.error.model.AnswerError.Motive;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;

public abstract class CharacteristicValidator implements AnswerValidator {
	
	private final Attribute attribute;

	public CharacteristicValidator(Attribute attribute) {
		super();
		this.attribute = attribute;
	}

	public Attribute getAttribute() {
		return attribute;
	}
	
	@Override
    public boolean isVerifiable(Answer answer) {
        return answer.getCharacteristic(attribute).isPresent();
    }
	
	
	
	@Override
	public Motive getMotive() {
		return Motive.ATTRIBUTE;
	}
	
	@Override
	public String errorMessage(Answer answer) {
		return buildMessage(
			getCharacteristic(answer).getValue(),
			answer
		);
	}

	protected Characteristic getCharacteristic(Answer answer) {
		return answer.getCharacteristic(getAttribute())
			.get();
	}
	
	protected String buildMessage(String characteristic, Answer answer) {
		return MessageFormat.format("Caracteristica: {0} -> {1}. Valor inserido: {2}",
			attribute.getLabel(), 
			characteristic,
			answer.getValue()
		);
	}
	
}
