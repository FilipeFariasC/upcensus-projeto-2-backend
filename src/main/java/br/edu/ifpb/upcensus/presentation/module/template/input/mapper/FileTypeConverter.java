package br.edu.ifpb.upcensus.presentation.module.template.input.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.util.CollectionUtils;
import br.edu.ifpb.upcensus.presentation.module.module.response.FileTypeResponse;

public class FileTypeConverter {
	
	public static FileTypeResponse modelToResponse(FileType fileType) {
		return new FileTypeResponse(fileType);
	}

	public static Set<FileTypeResponse> modelSetToResponseSet(Set<FileType> fileTypes) {
		return CollectionUtils.map(fileTypes, FileTypeConverter::modelToResponse, Collectors.toSet());
	}

	public static List<FileTypeResponse> modelListToResponseList(List<FileType> fileTypes) {
		return CollectionUtils.map(fileTypes, FileTypeConverter::modelToResponse, Collectors.toList());
	}
}
