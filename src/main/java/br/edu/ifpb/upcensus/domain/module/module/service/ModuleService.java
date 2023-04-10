package br.edu.ifpb.upcensus.domain.module.module.service;

import br.edu.ifpb.upcensus.domain.shared.service.DomainService;
import br.edu.ifpb.upcensus.infrastructure.util.FileType;

import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.domain.module.module.model.Module;

public interface ModuleService extends DomainService<Module, Long> {
	
	
	default void uploadFile(Long id, MultipartFile file, boolean ignoreHeaderRow, FileType fileType) {
		
		Module module = findById(id);
		
		
		
	}
	


}
