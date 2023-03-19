package br.edu.ifpb.upcensus.presentation;

import static br.edu.ifpb.upcensus.presentation.utils.ApiEndpoints.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController(FILE_PREFIX)
public class FileResources {
	
	
	@PostMapping(UPLOAD)
	public ResponseEntity<JobResponse> upload(@RequestParam("arquivo") MultipartFile file) throws Exception {
		String caminho = arquivoService.save(file);
		
		return ResponseEntity.ok(JobResponse.of(caminho, FileUtils.Extension.CSV, FileUtils.Extension.JSON, jobExecution.getCreateTime(), jobExecution.getStartTime(), jobExecution.getEndTime(), jobExecution.getExitStatus()));
	}
}
