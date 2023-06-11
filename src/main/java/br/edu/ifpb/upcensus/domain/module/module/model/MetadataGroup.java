package br.edu.ifpb.upcensus.domain.module.module.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;

@DomainDescriptor(name = "Grupo de Respostas")
public class MetadataGroup implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final Map<String, String> metadata;

	public MetadataGroup(Set<Metadata> metadata) {
		this.metadata = metadata
			.stream()
			.collect(Collectors.toMap(Metadata::getCode, Metadata::getValue));
	}
	
	public Optional<String> find(String metadataCode) {
		return Optional.ofNullable(metadata.get(metadataCode));
	}
	
	public String get(String metadataCode) {
		return metadata.getOrDefault(metadataCode, null);
	}
	public String getOrElse(String metadataCode, String orElse) {
		return metadata.getOrDefault(metadataCode, orElse);
	}
}
