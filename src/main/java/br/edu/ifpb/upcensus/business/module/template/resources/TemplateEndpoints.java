package br.edu.ifpb.upcensus.business.module.template.resources;

import br.edu.ifpb.upcensus.business.module.ModuleEndpoints;
import br.edu.ifpb.upcensus.business.shared.ApiEndpoints;

public interface TemplateEndpoints {
	public static final String TEMPLATE_PREFIX =  "/templates";
	// Absolute
	public static final String TEMPLATES = ModuleEndpoints.PREFIX + TEMPLATE_PREFIX;
	public static final String TEMPLATE = TEMPLATES + ApiEndpoints.ID;
}
