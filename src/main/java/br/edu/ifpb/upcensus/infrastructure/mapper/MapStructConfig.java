package br.edu.ifpb.upcensus.infrastructure.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

@MapperConfig(
	componentModel = MappingConstants.ComponentModel.SPRING,
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	implementationPackage = "<PACKAGE_NAME>.impl",
	collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
	nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface MapStructConfig { }
