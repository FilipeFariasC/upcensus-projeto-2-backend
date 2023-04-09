package br.edu.ifpb.upcensus.business.file.resources;

import static br.edu.ifpb.upcensus.business.file.resources.FileEndpoints.FILE;
import static br.edu.ifpb.upcensus.business.file.resources.FileEndpoints.UPLOAD;
import static br.edu.ifpb.upcensus.business.file.resources.FileEndpoints.UPLOAD_TYPE;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.upcensus.infrastructure.persistence.filesystem.FileService;
import br.edu.ifpb.upcensus.infrastructure.persistence.filesystem.FileServiceImpl;
import br.edu.ifpb.upcensus.presentation.file.request.FileRequest;


@RestController
@RequestMapping(FILE)
public class FileResources {
	
	private final FileService fileService;
	
	public FileResources(final FileServiceImpl fileService) {
		this.fileService = fileService;
	}
	
	
	/*@PostMapping(UPLOAD)
	public ResponseEntity<String> upload(FileRequest request){
		
		String path = fileService.save(request.getFile());
		
		return ResponseEntity.ok(path);
	}*/
	
	@PostMapping(UPLOAD_TYPE)
	public ResponseEntity<String> upload(@PathVariable String type,FileRequest request){
		
		String path = fileService.save(type,request.getFile());
		
		return ResponseEntity.ok(path);
	}
	
	
}
