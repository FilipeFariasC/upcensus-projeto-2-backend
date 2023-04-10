package br.edu.ifpb.upcensus.presentation.file.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.ifpb.upcensus.infrastructure.util.FileType;
import br.edu.ifpb.upcensus.infrastructure.util.FileUtils;

public class FileRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private MultipartFile file;
	
	@JsonProperty("file_type")
	private FileType fileType;
	
	@JsonProperty("ignore_header_row")
	private boolean ignoreHeaderRow;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public boolean isIgnoreHeaderRow() {
		return ignoreHeaderRow;
	}

	public void setIgnoreHeaderRow(boolean ignoreHeaderRow) {
		this.ignoreHeaderRow = ignoreHeaderRow;
	}
	


	@Override
	public String toString() {
		return String.format("{file: %s, file_type: %s, ignore_header_row: %s}", FileUtils.fileToString(file),fileType,ignoreHeaderRow);
	}
	
	
}
