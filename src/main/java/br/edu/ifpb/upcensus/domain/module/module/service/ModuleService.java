package br.edu.ifpb.upcensus.domain.module.module.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.business.job.JobService;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.domain.shared.service.DomainService;
import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.exception.ElementNotFoundException;

public interface ModuleService extends DomainService<Module, Long> {
	
	JobService getJobService();
	
	default void uploadFile(Long id, MultipartFile file, boolean ignoreHeaderRow, FileType fileType, String delimiter) {
		
		Module module = findById(id);
		module.clearAnswers();
		getJobService().runFileJob(file, saveAndFlush(module), ignoreHeaderRow, fileType, delimiter);
		
	}
	
	default void registerAnswers(Long id, Long idTemplate, List<Map<String, String>> answers) {
		
		Module module = findById(id);
		Optional<Template> opt = module.getTemplateById(idTemplate);
		
		if (!opt.isPresent())
			return ;
		
		Template template = opt.orElseThrow(()->new ElementNotFoundException(Template.class, idTemplate));
		
		module.clearAnswers();
		getJobService().runAnswerJob(
			saveAndFlush(module), 
			template, 
			answers
		);
		
	}


	default Module findByCode(String code) {
		return findByProperty("code", code);
	}
	default List<Module> findAllByCode(Collection<String> codes) {
		return findAllByProperty("code", codes);
	}
}
