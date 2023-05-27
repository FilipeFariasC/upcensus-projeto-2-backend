package br.edu.ifpb.upcensus.infrastructure.external.google.enums;

import java.util.Arrays;

import br.edu.ifpb.upcensus.domain.shared.model.DomainEnum;

public enum GooglePermissionTypes implements DomainEnum<GooglePermissionTypes>{
	USER("user"),GROUP("group"),DOMAIN("domain"),ANYONE("anyone");

	private final String label;

	GooglePermissionTypes(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public GooglePermissionTypes getValue() {
		return this;
	}
	public static boolean isValidType(String str) {
		return Arrays.stream(values())
			.anyMatch(type->type.name().equals(str));
			
	}

}
