package br.edu.ifpb.upcensus.business.module.template.input.resources;

import br.edu.ifpb.upcensus.business.module.ModuleEndpoints;
import br.edu.ifpb.upcensus.business.shared.ApiEndpoints;

public interface InputTemplateEndpoints {
	public static final String TEMPLATE_PREFIX =  "/templates/input";
	// Absolute
	public static final String TEMPLATES = ModuleEndpoints.PREFIX + TEMPLATE_PREFIX;
	public static final String TEMPLATE = TEMPLATES + ApiEndpoints.ID;
	

	public static final String INPUT_TEMPLATE_TYPES = "/types";
	public static final String INPUT_TEMPLATE_TYPE = INPUT_TEMPLATE_TYPES + "/{type}";
}
