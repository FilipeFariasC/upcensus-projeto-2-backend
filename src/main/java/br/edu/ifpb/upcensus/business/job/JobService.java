package br.edu.ifpb.upcensus.business.job;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.domain.module.template.model.Template;
import br.edu.ifpb.upcensus.infrastructure.builder.ItemReaderBuilder;
import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.exception.UnsupportedFileFormatException;
import br.edu.ifpb.upcensus.infrastructure.util.FileUtils;
import br.edu.ifpb.upcensus.infrastructure.util.TimeUtils;

@Service
public class JobService {
	
	private final JobBuilderFactory jobFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	private JobLauncher jobLauncher;

	public JobService(JobBuilderFactory jobFactory, StepBuilderFactory stepBuilderFactory, JobLauncher jobLauncher) {
		super();
		this.jobFactory = jobFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.jobLauncher = jobLauncher;
	}

	public void runJob(MultipartFile file, Template template, boolean ignoreHeaderRow, FileType fileType) {
		try {
			Job job = jobFactory
				.get(MessageFormat.format("{0}-{1}", template.getCode(), TimeUtils.toString(LocalDateTime.now(), TimeUtils.FILE_TIMESTAMP)))
				.start(buildFirstStep(file, template, ignoreHeaderRow))
				.build()
				;
			jobLauncher.run(job, new JobParameters());
		} catch (IOException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Step buildFirstStep(MultipartFile file, Template template, boolean ignoreHeaderRow) throws IOException {
		return stepBuilderFactory
			.get("firstStep")
			.<Map<String, String>, Map<String, String>>chunk(1)
			.reader(getReader(file, template, ignoreHeaderRow))
			.writer((item)->{
				System.out.println(item);
			}).build();
	}
	
	private ItemReader<Map<String, String>> getReader(MultipartFile file, Template template, boolean ignoreHeaderRow) throws IOException {
		switch (template.getFileType()) {
		case CSV:
			return new ItemReaderBuilder().buildCsvReader(file, template, ignoreHeaderRow);
		case XLSX:
			return new ItemReaderBuilder().buildXlsxReader(file, template, ignoreHeaderRow);
		default:
			throw new UnsupportedFileFormatException(file.getOriginalFilename(), FileUtils.getMimeType(file));
		}
	}
}
