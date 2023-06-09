package br.edu.ifpb.upcensus.presentation.module.template.input.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.mapper.MapStructConfig;
import br.edu.ifpb.upcensus.presentation.module.module.response.FileTypeResponse;
@Mapper(config = MapStructConfig.class)
public interface FileTypeMapper  {

	default FileTypeResponse modelToResponse(FileType fileType) {
		return new FileTypeResponse(fileType);
	}

	Set<FileTypeResponse> modelSetToResponseSet(Set<FileType> fileTypes);

	List<FileTypeResponse> modelListToResponseList(List<FileType> fileTypes);
	
}
