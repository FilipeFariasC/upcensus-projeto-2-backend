package br.edu.ifpb.upcensus.presentation.module.module.mapper;

import org.mapstruct.Mapper;

import br.edu.ifpb.upcensus.domain.module.module.model.Metadata;
import br.edu.ifpb.upcensus.domain.module.module.service.MetadataService;
import br.edu.ifpb.upcensus.infrastructure.mapper.BaseMapper;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.module.module.request.MetadataRequest;
import br.edu.ifpb.upcensus.presentation.module.module.response.MetadataResponse;

@Mapper(
	config = MapStructConfig.class,
	uses = MetadataService.class
)
public interface MetadataMapper extends BaseMapper<Metadata, MetadataRequest, MetadataResponse>{ }
	