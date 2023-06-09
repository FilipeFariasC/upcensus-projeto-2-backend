package br.edu.ifpb.upcensus.presentation.module.module.response;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.ifpb.upcensus.infrastructure.domain.FileType;

public class FileTypeResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private final FileType fileType;

	public FileTypeResponse(FileType fileType) {
		super();
		this.fileType = fileType;
	}
	
	@JsonProperty("file_type")
	public FileType getFileType() {
		return fileType;
	}


	@JsonProperty("name")
	public String getName() {
		return fileType.getLabel();
	}

	@JsonProperty("extension")
	public String getExtension() {
		return fileType.getExtension();
	}


	@JsonProperty("mime_type")
	public String getMimeType() {
		return fileType.getMimeType();
	}
	
	@JsonProperty("tabular_data")
	public boolean isTabularData() {
		return fileType.isTabularData();
	}


	@Override
	public String toString() {
		return String.format("{file_type: %s, name: %s, extension: %s, mime_type: %s, tabular_data: %s}", fileType, getName(), getExtension(), getMimeType(), isTabularData());
	}

	@Override
	public int hashCode() {
		return Objects.hash(fileType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileTypeResponse other = (FileTypeResponse) obj;
		return fileType == other.fileType;
	}
	
	
	
	
}
