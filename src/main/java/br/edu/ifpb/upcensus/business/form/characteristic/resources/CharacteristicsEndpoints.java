package br.edu.ifpb.upcensus.business.form.characteristic.resources;

import br.edu.ifpb.upcensus.business.form.shared.FormEndpoints;
import br.edu.ifpb.upcensus.business.shared.ApiEndpoints;

public interface CharacteristicsEndpoints {
	public static final String PREFIX = "/characteristics";
	public static final String CHARACTERISTICS = FormEndpoints.FORM + PREFIX;
	public static final String CHARACTERISTIC_ABSOLUTE = CHARACTERISTICS + ApiEndpoints.ID;

	public static final String CHARACTERISTIC = ApiEndpoints.ID;
	public static final String ATTRIBUTES = "/attributes";
	public static final String ATTRIBUTES_NAME = "/attributes/{name}";
}
