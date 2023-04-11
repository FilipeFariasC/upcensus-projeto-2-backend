package br.edu.ifpb.upcensus.presentation.file.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.util.FileUtils;

public class FileRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private MultipartFile file;
	@JsonProperty("fileType")
	private FileType fileType;
	
	@JsonProperty("ignoreHeaderRow")
	private boolean ignoreHeaderRow;

	public FileType getFileType() {
		return fileType;
	}
	
	

	public MultipartFile getFile() {
		return file;
	}



	public void setFile(MultipartFile file) {
		this.file = file;
	}



	@JsonProperty("file_type")
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public boolean isIgnoreHeaderRow() {
		return ignoreHeaderRow;
	}

	@JsonProperty("file_type")
	public void setIgnoreHeaderRow(boolean ignoreHeaderRow) {
		this.ignoreHeaderRow = ignoreHeaderRow;
	}
	


	@Override
	public String toString() {
		return String.format("{file: %s, file_type: %s, ignore_header_row: %s}", FileUtils.fileToString(file), fileType,ignoreHeaderRow);
	}
	
	
}
