package br.edu.ifpb.upcensus.presentation.file.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.util.FileUtils;

public class FileRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private MultipartFile file;
	private FileType fileType;
	private boolean ignoreHeaderRow;
	
	private String delimiter;

	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public boolean isIgnoreHeaderRow() {
		return ignoreHeaderRow;
	}
	public void setIgnoreHeaderRow(boolean ignoreHeaderRow) {
		this.ignoreHeaderRow = ignoreHeaderRow;
	}
	
	public String getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	@Override
	public String toString() {
		return String.format("{file: %s, file_type: %s, ignore_header_row: %s, delimiter: %s}", FileUtils.fileToString(file), fileType,ignoreHeaderRow, delimiter);
	}
	
	
}
