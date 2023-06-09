package br.edu.ifpb.upcensus.infrastructure.domain;

import java.util.Arrays;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.edu.ifpb.upcensus.domain.shared.model.DomainEnum;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.exception.ElementNotFoundException;
import br.edu.ifpb.upcensus.infrastructure.exception.UnsupportedFileFormatException;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;
import br.edu.ifpb.upcensus.presentation.module.module.response.FileTypeResponse;


@DomainDescriptor(name = "Tipo de Arquivo")
public enum FileType implements DomainEnum<FileType> {
	CSV(".csv", "text/csv", true),
	JSON(".json", "application/json"), 
	ODS(".ods", "application/vnd.oasis.opendocument.spreadsheet", true),
	TEXT(".txt", "text/plain", "Texto", true),
	XLS(".xls", "application/vnd.ms-excel", true),
	XLSX(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", true),
	YAML(".yaml", "text/yaml");

	private final String extension;
	private final String mimeType;
	private final String label;
	private final boolean tabularData;

	private FileType(String extension, String mimeType) {
		this(extension, mimeType, "", false);
	}
	private FileType(String extension, String mimeType, boolean tabularData) {
		this(extension, mimeType, "", tabularData);
	}
	
	private FileType(String extension, String mimeType, String label, boolean tabularData) {
		this.extension = extension;
		this.mimeType = mimeType;
		this.label = StringUtils.isEmpty(label) ? this.name() : label;
		this.tabularData = tabularData;
	}

	public String getExtension() {
		return extension;
	}

	public String getMimeType() {
		return mimeType;
	}

	public static boolean isSupportedFileExtension(String extension) {
		if (StringUtils.isEmpty(extension))
			return false;
		return Stream.of(FileType.values())
				.anyMatch(fileType -> extension.endsWith(fileType.extension));
	}

	public static Boolean isSupportedMimeType(String mimeType) {
		return Stream.of(FileType.values()).anyMatch(filetype -> filetype.getMimeType().equals(mimeType));
	}

	public static FileType from(String filename, String mimeType) {
		return Stream.of(FileType.values()).filter(filetype -> filetype.equals(filename, mimeType)).findFirst()
				.orElseThrow(() -> new UnsupportedFileFormatException(filename, mimeType));
	}

	public boolean equals(String extension, String mimeType) {
		return this.extension.equals(extension) || this.mimeType.equals(mimeType);
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public FileType getValue() {
		return this;
	}
	
	public FileTypeResponse getResponseModel() {
		return new FileTypeResponse(this);
	}
	
	public boolean isTabularData() {
		return tabularData;
	}
	
	@JsonCreator
	public static FileType fromString(String str) {
		return Arrays.stream(FileType.values())
			.filter(fileType -> fileType.name().equals(str))
			.findFirst()
			.orElseThrow(() -> new ElementNotFoundException(FileType.class, str));
	}
}
