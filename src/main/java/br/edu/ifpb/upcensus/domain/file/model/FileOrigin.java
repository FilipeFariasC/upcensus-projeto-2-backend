package br.edu.ifpb.upcensus.domain.file.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.domain.shared.model.DomainEnum;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.exception.ElementNotFoundException;

@DomainDescriptor(name = "OrigemArquivo")
public enum FileOrigin  implements DomainEnum<FileOrigin> {
	
	CENSUP("CENSUP"),SUAP("SUAP");
	
	private final String label;
	
	FileOrigin(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

	@Override
	public FileOrigin getValue() {
		return this;
	}
	
	@JsonCreator
	public static FileOrigin from(String fileOrigin) {
		return Stream.of(FileOrigin.values())
			.filter(attr-> attr.name().equals(fileOrigin))
			.findFirst()
			.orElseThrow(()-> new ElementNotFoundException(FileOrigin.class, fileOrigin));
	}

}
