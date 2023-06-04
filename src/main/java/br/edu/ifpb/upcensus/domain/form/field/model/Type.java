package br.edu.ifpb.upcensus.domain.form.field.model;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.edu.ifpb.upcensus.domain.shared.model.DomainEnum;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.exception.ElementNotFoundException;

@DomainDescriptor(name = "Tipo de campo")
public enum Type implements DomainEnum<Type>{
	
	PLAIN_TEXT(".*", "Texto"),
	INTEGER("^[-+]?\\d+$", "Inteiro"),
	DECIMAL("^[-+]?\\d+[.,]?\\d+$", "Decimal"),
	ALPHANUMERIC("^[A-Za-zÀ-ú\\d\\s]+$", "Alfanumérico"),
	ALPHABETIC("^[A-Za-zÀ-ú\\s]+$", "Alfabético"),
	DATE("^\\d{4}-\\d{2}-\\d{2}$", "Data"),
	TIMESTAMP("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(.\\d{3}Z)$", "Data e Hora"),
	BOOLEAN("^(?i)false|true$", "Booleano");
	

	private final Pattern pattern;
	private final String label;
	private Type(String pattern, String label) {
		this.pattern = Pattern.compile(pattern);
		this.label = label;
	}
	
	public boolean match(final String value) {
		return pattern.matcher(value).find();
	}
	public boolean isDate() {
		return this.equals(DATE);
	}
	public boolean isTimestamp() {
		return this.equals(DATE);
	}
	public boolean isDateTime() {
		return isDate() || isDateTime();
	}
	public boolean isInteger() {
		return this.equals(INTEGER);
	}
	public boolean isDecimal() {
		return this.equals(DECIMAL);
	}
	public boolean isNumeric() {
		return isInteger() || isDecimal();
	}
	public boolean isBoolean() {
		return this.equals(BOOLEAN);
	}
	
	public static boolean isValidType(String str) {
		return Arrays.stream(values())
			.anyMatch(type->type.name().equals(str));
	}
	
	public Pattern getPattern() {
		return pattern;
	}
	
	@Override
	public Type getValue() {
		return this;
	}
	@Override
	public String getLabel() {
		return label;
	}
	
	@JsonCreator
	public static Type from(String attribute) {
		return Stream.of(values())
			.filter(attr-> attr.name().equals(attribute))
			.findFirst()
			.orElseThrow(()-> new ElementNotFoundException(Type.class, attribute));
	}
}