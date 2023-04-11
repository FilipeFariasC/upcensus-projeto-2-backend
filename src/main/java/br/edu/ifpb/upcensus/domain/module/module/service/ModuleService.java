package br.edu.ifpb.upcensus.domain.module.module.service;

import java.util.Collection;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.business.job.JobService;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.domain.shared.service.DomainService;
import br.edu.ifpb.upcensus.infrastructure.domain.FileType;

public interface ModuleService extends DomainService<Module, Long> {
	
	JobService getJobService();
	
	default void uploadFile(Long id, MultipartFile file, boolean ignoreHeaderRow, FileType fileType) {
		
		Module module = findById(id);
		
		Template template = module.getTemplateByFileType(fileType);
		
		getJobService().runJob(file, template, ignoreHeaderRow, fileType);
		
	}
	


	default Module findByCode(String code) {
		return findByProperty("code", code);
	}
	default List<Module> findAllByCode(Collection<String> codes) {
		return findAllByProperty("code", codes);
	}

}
