package br.edu.ifpb.upcensus.infrastructure.persistence.filesystem;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.infrastructure.util.TimeUtils;
import br.edu.ifpb.upcensus.infrastructure.util.FileUtils;
import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;

@Service
public class FileServiceImpl implements FileService{
	
	
	public FileServiceImpl(){
		this.init();
	}
	
	@Override
	public void init() {
		try {
			Files.createDirectories(FileUtils.UPLOAD_PATH);
		} catch (IOException exception) {
			throw new UncheckedIOException(exception);
		}
	}
	
	@Override
	public String save(MultipartFile file) {
		String renamed = filename(file);
		Path path = FileUtils.UPLOAD_PATH.resolve(renamed);
		File filePath = path.toFile();
		try {
			if (filePath.exists()) {
				return null;
			}
			Files.copy(file.getInputStream(), path);
			return path.getFileName().toString();
		} catch (IOException exception) {
			throw new UncheckedIOException(exception);
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path path = FileUtils.UPLOAD_PATH.resolve(filename);
			Resource resource = new UrlResource(path.toUri());
			
			if (resource.exists() && resource.isReadable()) return resource;
			
			return null;
		} catch (MalformedURLException exception) {
			throw new UncheckedIOException(exception);
		}
	}

	@Override
	public void delete(String filename) {
		try {
			Path path = FileUtils.UPLOAD_PATH.resolve(filename);

			Files.delete(path);
		} catch (IOException exception) {
			throw new UncheckedIOException(exception);
		}
	}
	

	public String filename(MultipartFile file) {
		String filename = FileUtils.getFilename(file);
		String extension = FileUtils.getExtension(file);
		
		return filename(filename, extension);
	}

	private String filename(String filename, String extension) {
		return MessageFormat.format("{0}-{1}{2}", 
			filename, 
			TimeUtils.toString(LocalDateTime.now(), DateTimeFormatter.ofPattern(TimeUtils.FILE_TIMESTAMP)), 
			StringUtils.isEmpty(extension) ? "" : extension);
	}

}
