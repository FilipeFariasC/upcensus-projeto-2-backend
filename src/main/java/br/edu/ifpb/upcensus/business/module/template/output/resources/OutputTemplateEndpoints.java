package br.edu.ifpb.upcensus.business.module.template.output.resources;

import br.edu.ifpb.upcensus.business.module.ModuleEndpoints;
import br.edu.ifpb.upcensus.business.shared.ApiEndpoints;

public interface OutputTemplateEndpoints {
	public static final String TEMPLATE_PREFIX =  "/templates/output";
	// Absolute
	public static final String TEMPLATES = ModuleEndpoints.PREFIX + TEMPLATE_PREFIX;
	public static final String TEMPLATE = TEMPLATES + ApiEndpoints.ID;
}
