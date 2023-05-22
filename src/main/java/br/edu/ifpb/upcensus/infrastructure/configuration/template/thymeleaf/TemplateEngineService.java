package br.edu.ifpb.upcensus.infrastructure.configuration.template.thymeleaf;

import java.util.Map;

public interface TemplateEngineService {

    String process(String template, Map<String, Object> variables);
}
