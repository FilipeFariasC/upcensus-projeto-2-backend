package br.edu.ifpb.upcensus.business.form.field.resources;

import br.edu.ifpb.upcensus.business.form.characteristic.resources.CharacteristicsEndpoints;
import br.edu.ifpb.upcensus.business.form.shared.FormEndpoints;
import br.edu.ifpb.upcensus.business.shared.ApiEndpoints;

public interface FieldEndpoints {
	public static final String FIELD_PREFIX =  "/fields";
	// Absolute
	public static final String FIELDS_ABSOLUTE = FormEndpoints.FORM + FIELD_PREFIX;
	public static final String FIELD_ABSOLUTE = FIELDS_ABSOLUTE + ApiEndpoints.ID;
	public static final String FIELD_CHARACTERISTICS_ABSOLUTE = FIELD_ABSOLUTE + CharacteristicsEndpoints.PREFIX;
	
	// Relative
	public static final String FIELDS = "/";
	public static final String FIELD = ApiEndpoints.ID;
	public static final String FIELD_CHARACTERISTICS = FIELD + CharacteristicsEndpoints.PREFIX;
	public static final String FIELD_CHARACTERISTICS_ADD = FIELD + CharacteristicsEndpoints.PREFIX + "/add";
	public static final String FIELD_CHARACTERISTICS_REMOVE = FIELD + CharacteristicsEndpoints.PREFIX + "/remove";
}
