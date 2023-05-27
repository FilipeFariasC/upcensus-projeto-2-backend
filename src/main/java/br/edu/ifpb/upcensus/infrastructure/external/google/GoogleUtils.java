package br.edu.ifpb.upcensus.infrastructure.external.google;

import com.google.api.services.drive.model.Permission;

import br.edu.ifpb.upcensus.infrastructure.external.google.enums.GooglePermissionRoles;
import br.edu.ifpb.upcensus.infrastructure.external.google.enums.GooglePermissionTypes;

public class GoogleUtils {
	
	
	public static Permission createPermission(GooglePermissionRoles role,GooglePermissionTypes type, String userEmail) {
		
		
		if (type.equals(GooglePermissionTypes.ANYONE)) {
			return anyonePermission(role);
		}
		
		Permission userPermission = new Permission();
		userPermission.setRole(role.getLabel());
		userPermission.setType(type.getLabel());
		userPermission.setEmailAddress(userEmail);
		
		return userPermission;
		
	}
	
	public static Permission anyonePermission(GooglePermissionRoles role) {
		Permission userPermission = new Permission();
		userPermission.setRole(role.getLabel());
		userPermission.setType("anyone");
		return userPermission;
		
	}

}
