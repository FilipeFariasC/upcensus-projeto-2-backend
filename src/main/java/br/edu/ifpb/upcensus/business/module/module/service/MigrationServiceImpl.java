package br.edu.ifpb.upcensus.business.module.module.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.module.service.MigrationService;
import br.edu.ifpb.upcensus.infrastructure.configuration.template.thymeleaf.TemplateEngineService;
import br.edu.ifpb.upcensus.infrastructure.util.FileUtils;
import br.edu.ifpb.upcensus.infrastructure.util.TimeUtils;

@Service
public class MigrationServiceImpl implements MigrationService {
	
	private final TemplateEngineService templateEngineService;

	public MigrationServiceImpl(final TemplateEngineService templateEngineService) {
		super();
		this.templateEngineService = templateEngineService;
	}



	@Override
	public void migrate(Module module) {
		module.migrate();
		Map<String, Object> variables = getMigrationVariables(module);
		
		String result = templateEngineService.process(module.getOutputTemplate().getLayout(), variables);
		
	    try {
			String filename = buildFilename(module);
			Path path = FileUtils.RESULT_PATH.resolve(filename);
			Files.write(path, result.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, Object> getMigrationVariables(Module module) {
		Map<String, Object> variables = new HashMap<>();
		
		variables.put("registros", module.groupedAnswers());
		
		return variables;
	}
	
	private String buildFilename(Module module) {
		return MessageFormat.format("{0}__{1}__{2}",
			module.getCode(), 
			module.getOutputTemplate().getCode(),
			TimeUtils.toString(LocalDateTime.now(), 
			TimeUtils.FILE_TIMESTAMP)
		);
	}
}
