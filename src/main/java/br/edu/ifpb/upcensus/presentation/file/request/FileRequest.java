package br.edu.ifpb.upcensus.presentation.file.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.infrastructure.util.FileUtils;

public class FileRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private MultipartFile file;
	

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return String.format("{file: %s}", FileUtils.fileToString(file));
	}
	
	
}
