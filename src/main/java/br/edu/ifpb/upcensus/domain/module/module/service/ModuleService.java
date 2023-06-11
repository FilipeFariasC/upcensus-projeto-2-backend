package br.edu.ifpb.upcensus.domain.module.module.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.shared.service.DomainCodeableService;
import br.edu.ifpb.upcensus.infrastructure.domain.FileType;

public interface ModuleService extends DomainCodeableService<Module, Long> {
	
	void migrate(Long id);
	
	void uploadFile(Long id, MultipartFile file, boolean ignoreHeaderRow, FileType fileType, String delimiter);
	
	void registerAnswers(Long id, Long idTemplate, List<Map<String, String>> answers);

}
