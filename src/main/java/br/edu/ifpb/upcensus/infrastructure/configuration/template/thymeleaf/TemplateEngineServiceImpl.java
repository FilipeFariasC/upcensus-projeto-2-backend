package br.edu.ifpb.upcensus.infrastructure.configuration.template.thymeleaf;

import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class TemplateEngineServiceImpl implements TemplateEngineService {

	private final TemplateEngine templateEngine;
	
    public TemplateEngineServiceImpl(final TemplateEngine templateEngine) {
		super();
		this.templateEngine = templateEngine;
	}

	public String process(String template, Map<String, Object> variables) {
    	Context context = new Context(LocaleContextHolder.getLocale(), variables);
    	
    	return templateEngine.process(template, context);
    }
}
