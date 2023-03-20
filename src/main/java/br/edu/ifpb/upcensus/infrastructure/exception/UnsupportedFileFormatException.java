package br.edu.ifpb.upcensus.infrastructure.exception;

import org.springframework.http.HttpStatus;

import br.edu.ifpb.upcensus.infrastructure.annotation.DomainException;
import br.edu.ifpb.upcensus.infrastructure.util.MessageKeys;

@DomainException(
	key = MessageKeys.FILE_UNSUPPORTED_FORMAT,
	status = HttpStatus.BAD_REQUEST,
	append = true
)
public class UnsupportedFileFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final String filename;
	private final String mimeType;
	
	public UnsupportedFileFormatException(
			String filename,
			String mimeType
	) {
		super();
		this.filename = filename;
		this.mimeType = mimeType;
	}

	@Override
	public String getMessage() {
		return String.format("Filename: %s;MimeType: %s", filename, mimeType);
	}

}
