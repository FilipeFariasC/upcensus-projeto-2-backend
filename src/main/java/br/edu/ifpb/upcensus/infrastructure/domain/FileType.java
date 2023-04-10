package br.edu.ifpb.upcensus.infrastructure.domain;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.edu.ifpb.upcensus.domain.shared.model.DomainEnum;
import br.edu.ifpb.upcensus.infrastructure.annotation.DomainDescriptor;
import br.edu.ifpb.upcensus.infrastructure.exception.ElementNotFoundException;
import br.edu.ifpb.upcensus.infrastructure.exception.UnsupportedFileFormatException;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;


@DomainDescriptor(name = "Tipo de Arquivo")
public enum FileType implements DomainEnum<FileType> {
	JSON("\\.json", "application/json"), 
	XLS("\\.xls", "application/vnd.ms-excel"),
	ODS("\\.ods", "application/vnd.oasis.opendocument.spreadsheet"),
	XLSX("\\.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
	CSV("\\.csv", "text/csv"),
	TEXT("(\\.[a-zA-Z]{3})?", "text/plain", "Texto"),
	YAML("\\.ya?ml", "text/yaml");

	private final Pattern extensionPattern;
	private final String mimeType;
	private final String label;

	public static final String FILE_PREFIX = "^[\\w\\s,-]+%s$";

	private FileType(String extensionPattern, String mimeType) {
		this(extensionPattern, mimeType, "");
	}
	
	private FileType(String extensionPattern, String mimeType, String label) {
		this.extensionPattern = Pattern.compile(String.format(FILE_PREFIX, extensionPattern));
		this.mimeType = mimeType;
		this.label = StringUtils.isEmpty(label) ? this.name() : label;
	}

	public Pattern getExtensionPattern() {
		return extensionPattern;
	}

	public String getMimeType() {
		return mimeType;
	}

	public static Boolean isSupportedFileExtension(String extension) {
		return Stream.of(FileType.values())
				.anyMatch(filetype -> filetype.getExtensionPattern().matcher(extension).matches());
	}

	public static Boolean isSupportedMimeType(String mimeType) {
		return Stream.of(FileType.values()).anyMatch(filetype -> filetype.getMimeType().equals(mimeType));
	}

	public static FileType from(String filename, String mimeType) {
		return Stream.of(FileType.values()).filter(filetype -> filetype.equals(filename, mimeType)).findFirst()
				.orElseThrow(() -> new UnsupportedFileFormatException(filename, mimeType));
	}

	public Boolean equals(String extension, String mimeType) {
		Matcher matcher = extensionPattern.matcher(extension);

		return matcher.matches() || this.mimeType.equals(mimeType);
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public FileType getValue() {
		return this;
	}
	
	@JsonCreator
	public static FileType fromString(String str) {
		return Arrays.stream(FileType.values())
			.filter(fileType -> fileType.name().equals(str))
			.findFirst()
			.orElseThrow(() -> new ElementNotFoundException(FileType.class, str));
	}
}
