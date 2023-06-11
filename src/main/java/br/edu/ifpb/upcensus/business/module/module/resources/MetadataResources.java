package br.edu.ifpb.upcensus.business.module.module.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.business.shared.BaseCrudResource;
import br.edu.ifpb.upcensus.domain.module.module.model.Metadata;
import br.edu.ifpb.upcensus.domain.module.module.service.MetadataService;
import br.edu.ifpb.upcensus.infrastructure.http.response.service.ResponseService;
import br.edu.ifpb.upcensus.presentation.module.module.mapper.MetadataMapper;
import br.edu.ifpb.upcensus.presentation.module.module.request.MetadataRequest;
import br.edu.ifpb.upcensus.presentation.module.module.response.MetadataResponse;

@RestController
@RequestMapping(ModuleEndpoints.METADATA)
public class MetadataResources extends BaseCrudResource<Metadata, Long, MetadataRequest, MetadataResponse>{

	private final MetadataService metadataService;
	private final MetadataMapper metadataMapper;
	private final ResponseService responseService;

	public MetadataResources(
		final MetadataService metadataService,
		final MetadataMapper metadataMapper,
		final ResponseService responseService
	) {
		
		this.metadataMapper = metadataMapper;
		this.metadataService = metadataService;
		this.responseService = responseService;
	}

	@Override
	protected ResponseService getResponseService() {
		return this.responseService;
	}

	@Override
	protected MetadataService getModelService() {
		return this.metadataService;
	}

	@Override
	protected MetadataMapper getDomainMapper() {
		return this.metadataMapper;
	}

}
