package br.edu.ifpb.upcensus.business.module.module.resources;

import br.edu.ifpb.upcensus.business.module.template.input.resources.InputTemplateEndpoints;
import br.edu.ifpb.upcensus.business.shared.ApiEndpoints;

public interface ModuleEndpoints {
	
	public static final String PREFIX = "/modules";
	public static final String MODULES = br.edu.ifpb.upcensus.business.module.ModuleEndpoints.PREFIX + PREFIX;
	public static final String MODULE = MODULES + ApiEndpoints.ID;
	
	public static final String MODULE_TEMPLATE = MODULE + InputTemplateEndpoints.TEMPLATE_PREFIX + "/{idTemplate}";
	
}
