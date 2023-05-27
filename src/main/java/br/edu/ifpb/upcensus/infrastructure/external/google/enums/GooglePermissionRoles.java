package br.edu.ifpb.upcensus.infrastructure.external.google.enums;

import java.util.Arrays;

import br.edu.ifpb.upcensus.domain.shared.model.DomainEnum;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;

@DomainDescriptor(name = "Cargo de Usuário")
public enum GooglePermissionRoles implements DomainEnum<GooglePermissionRoles>{
	
	READER("Reader"),
	OWNER("owner"),
	ORGANIZER("organizer"),
	FILEORGANIZER("fileOrganizer"),
	WRITER("writer"),
	COMMENTER("commenter");
	
	private final String label;

	GooglePermissionRoles(String label) {
		this.label = label;
		
	}

	public static boolean isValidRole(String str) {
		return Arrays.stream(values())
			.anyMatch(role->role.name().equals(str));
			
	}
	
	public GooglePermissionRoles getValue() {
		return this;
	}

	@Override
	public String getLabel() {
		return label;
	}


	
}