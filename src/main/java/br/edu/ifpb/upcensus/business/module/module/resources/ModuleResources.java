package br.edu.ifpb.upcensus.business.module.module.resources;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.shared.ApiEndpoints;
import br.edu.ifpb.upcensus.business.shared.BaseCrudResource;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.module.service.ModuleService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.presentation.file.request.FileRequest;
import br.edu.ifpb.upcensus.presentation.module.module.mapper.ModuleMapper;
import br.edu.ifpb.upcensus.presentation.module.module.request.ModuleRequest;
import br.edu.ifpb.upcensus.presentation.module.module.response.ModuleResponse;
import br.edu.ifpb.upcensus.presentation.shared.response.Response;

@RestController
@RequestMapping(ModuleEndpoints.MODULES)
public class ModuleResources extends BaseCrudResource<Module, Long, ModuleRequest, ModuleResponse>{

	private final ModuleService moduleService;
	private final ModuleMapper moduleMapper;
	private final ResponseService responseService;

	public ModuleResources(
			final ModuleService moduleService,
			final ModuleMapper moduleMapper,
			final ResponseService responseService) {
		
		this.moduleMapper = moduleMapper;
		this.moduleService = moduleService;
		this.responseService = responseService;
	}
	
	@PostMapping(ApiEndpoints.ID+"/upload")
	@ResponseStatus(CREATED)
	public Response<?> uploadFileToModule(@PathVariable Long id, FileRequest request){
		moduleService.uploadFile(id, request.getFile(), request.isIgnoreHeaderRow(), request.getFileType());
		
		
		return null;
	}


	@Override
	protected ResponseService getResponseService() {
		return this.responseService;
	}

	@Override
	protected ModuleService getModelService() {
		return this.moduleService;
	}

	@Override
	protected ModuleMapper getDomainMapper() {
		return this.moduleMapper;
	}

}
