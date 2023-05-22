package br.edu.ifpb.upcensus.domain.module.module.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.shared.service.DomainService;
import br.edu.ifpb.upcensus.infrastructure.domain.FileType;

public interface ModuleService extends DomainService<Module, Long> {
	
	void migrate(Long id);
	
	void uploadFile(Long id, MultipartFile file, boolean ignoreHeaderRow, FileType fileType, String delimiter);
	
	void registerAnswers(Long id, Long idTemplate, List<Map<String, String>> answers);


	default Module findByCode(String code) {
		return findByProperty("code", code);
	}
	default List<Module> findAllByCode(Collection<String> codes) {
		return findAllByProperty("code", codes);
	}
}
