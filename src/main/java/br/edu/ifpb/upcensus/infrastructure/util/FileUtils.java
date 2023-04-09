package br.edu.ifpb.upcensus.infrastructure.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	private static final String RESULT = "result";
	public static final Path RESULT_PATH = Paths.get(RESULT);

	private static final String UPLOAD = "uploads";
	public static final Path UPLOAD_PATH = Paths.get(UPLOAD);
	
	private static final Tika tika = new Tika();

	private static final Pattern FILENAME_REGEX = Pattern.compile("^(.?[\\w\\s,-]+)(.\\w+)?$");
	private static final int FILE_NAME_GROUP = 1;
	private static final int FILE_EXTENSION_GROUP = 2;
	
	
	private FileUtils() {}

	
	public static String fileToString(MultipartFile file) {
		return fileToString(file.getOriginalFilename(), Long.toString(file.getSize()));
	}
	
	private static String fileToString(String filename, String size) {
		return String.format("{filename: %s, size: %s}", filename, size);
	}
	
	public static String getFilename(MultipartFile file) {
		return getFilename(file.getOriginalFilename());
	}
	public static String getFilename(File file) {
		return getFilename(file.getName());
	}
	public static String getFilename(String filename) {
		return getField(filename, FILE_NAME_GROUP);
	}
	
	public static String getExtension(MultipartFile file) {
		return getExtension(file.getOriginalFilename());
	}
	public static String getExtension(File file) {
		return getExtension(file.getName());
	}
	public static String getExtension(String filename) {
		return getField(filename, FILE_EXTENSION_GROUP);
	}
	
	private static String getField(String filename, int matcherGroup) {
		Matcher matcher = FILENAME_REGEX.matcher(filename);
		if (!matcher.matches() || matcherGroup > matcher.groupCount()) return null;
		return matcher.group(matcherGroup);
	}
	
	
	public static String getMimeType(MultipartFile file) {
		return file.getContentType();
	}
	public static String getMimeType(File file) {
		List<MimeTypeConversor> converters = new ArrayList<>();
		
		converters.add(tika::detect);
		converters.add(f -> Files.probeContentType(f.toPath()));
		
		for (MimeTypeConversor converter: converters) {
			try {
				return converter.convert(file);
			} catch (IOException exception) { }
		}
		return null;
	}
	
	@FunctionalInterface
	private interface MimeTypeConversor {
		public String convert(File file) throws IOException;
	}
}
