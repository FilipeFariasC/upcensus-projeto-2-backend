package br.edu.ifpb.upcensus.business.form.configuration.resources;

import br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints;
import br.edu.ifpb.upcensus.business.form.shared.FormEndpoints;
import br.edu.ifpb.upcensus.business.shared.ApiEndpoints;

public interface ConfigurationEndpoints {
	public static final String CONFIGURATION_PREFIX =  "/configurations";
	// Absolute
	public static final String CONFIGURATIONS = FormEndpoints.FORM + CONFIGURATION_PREFIX;
	public static final String CONFIGURATION = CONFIGURATIONS + ApiEndpoints.ID;
	

	public static final String CONFIGURATION_FIELDS = CONFIGURATION + FieldEndpoints.FIELD_PREFIX;
	public static final String CONFIGURATION_FIELDS_ADD = CONFIGURATION_FIELDS;
	public static final String CONFIGURATION_FIELDS_REMOVE = CONFIGURATION_FIELDS;
}
