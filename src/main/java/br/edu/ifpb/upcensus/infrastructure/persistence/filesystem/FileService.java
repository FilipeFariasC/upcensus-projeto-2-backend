package br.edu.ifpb.upcensus.infrastructure.persistence.filesystem;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	default void init() { }
	
	String save(MultipartFile file);
	default String[] saveAll(MultipartFile... files) {
		return this.saveAll(Arrays.asList(files))
			.stream()
			.toArray(String[]::new);
	}
	default Collection<String> saveAll(Collection<MultipartFile> files) {
		return files
			.stream()
			.map(this::save)
			.collect(Collectors.toList());
	}
	
	Resource load(String filename);
	default Resource[] loadAll(String... filenames) {
		return this.loadAll(Arrays.asList(filenames))
			.stream()
			.toArray(Resource[]::new);
	}
	default Collection<Resource> loadAll(Collection<String> filenames) {
		return filenames
			.stream()
			.map(this::load)
			.collect(Collectors.toList());
	}
	
	void delete(String filename);
	default void deleteAll(String... filenames) {
		this.deleteAll(Arrays.asList(filenames));
	}
	default void deleteAll(Collection<String> filenames) {
		filenames.forEach(this::delete);
	}

	String save(String type, MultipartFile file);
	
}
