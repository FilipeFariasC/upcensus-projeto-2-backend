package br.edu.ifpb.upcensus.business.job;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.upcensus.business.form.shared.pipeline.ValidationPipeline;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.module.service.ModuleService;
import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate;
import br.edu.ifpb.upcensus.infrastructure.builder.AnswerItemProcessor;
import br.edu.ifpb.upcensus.infrastructure.builder.AnswerItemReader;
import br.edu.ifpb.upcensus.infrastructure.builder.ItemReaderBuilder;
import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.infrastructure.exception.UnsupportedFileFormatException;
import br.edu.ifpb.upcensus.infrastructure.util.FileUtils;
import br.edu.ifpb.upcensus.infrastructure.util.TimeUtils;

@Service
public class JobService {

	private final JobBuilderFactory jobFactory;
	private final StepBuilderFactory stepBuilderFactory;

	private final JobLauncher jobLauncher;
	private ModuleService moduleService;
	private final ValidationPipeline pipeline;

	public JobService(
		final JobBuilderFactory jobFactory, 
		final StepBuilderFactory stepBuilderFactory,
		final JobLauncher jobLauncher,
		final ValidationPipeline pipeline
	) {
		super();
		this.jobFactory = jobFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.jobLauncher = jobLauncher;
		this.pipeline = pipeline;
	}

	public void runFileJob(MultipartFile file, Module module, boolean ignoreHeaderRow, FileType fileType,
			String delimiter) {
		try {

			InputTemplate template = module.getTemplateByFileType(fileType);
			Job job = jobFactory
					.get(MessageFormat.format("{0}-{1}__{2}", module.getCode(), template.getCode(),
							TimeUtils.toString(LocalDateTime.now(), TimeUtils.FILE_TIMESTAMP)))
					.start(readFileStep(file, module, template, ignoreHeaderRow, delimiter)).build();
			jobLauncher.run(job, new JobParameters());
		} catch (IOException | JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			throw new RuntimeException(e);
		}
	}

	private Step readFileStep(MultipartFile file, Module module, InputTemplate template, boolean ignoreHeaderRow,
			String delimiter) throws IOException {
		return stepBuilderFactory.get("readFileSaveInDatabase")
				.<Map<String, String>, Set<Answer>>chunk(1)
				.reader(getFileReader(file, template, ignoreHeaderRow, delimiter))
				.processor(validator(module, template))
				.writer(writeToDatabase(module, template)).build();
	}

	public void runAnswerJob(Module module, InputTemplate template, List<Map<String, String>> answers) {
		try {
			Job job = jobFactory
					.get(MessageFormat.format("{0}-{1}__{2}", module.getCode(), template.getCode(),
							TimeUtils.toString(LocalDateTime.now(), TimeUtils.FILE_TIMESTAMP)))
					.start(readAnswerStep(answers, module, template)).build();
			jobLauncher.run(job, new JobParameters());
		} catch (IOException | JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			throw new RuntimeException(e);
		}
	}

	private Step readAnswerStep(List<Map<String, String>> answers, Module module, InputTemplate template) throws IOException {
		return stepBuilderFactory.get("readAnswersSaveInDatabase")
				.<Map<String, String>, Set<Answer>>chunk(1)
				.reader(getAnswerReader(answers))
				.processor(validator(module, template))
				.writer(writeToDatabase(module, template))
				.build();
	}

	private ItemReader<Map<String, String>> getFileReader(MultipartFile file, InputTemplate template,
			boolean ignoreHeaderRow, String delimiter) throws IOException {
		switch (template.getType().getFileType()) {
		case CSV:
			return new ItemReaderBuilder().buildCsvReader(file, template, ignoreHeaderRow, delimiter);
		case XLSX:
			return new ItemReaderBuilder().buildXlsxReader(file, template, ignoreHeaderRow);
		default:
			throw new UnsupportedFileFormatException(file.getOriginalFilename(), FileUtils.getMimeType(file));
		}
	}

	private ItemReader<Map<String, String>> getAnswerReader(List<Map<String, String>> answers) throws IOException {
		return new AnswerItemReader(answers);
	}

	private ItemWriter<Set<Answer>> writeToDatabase(Module module, InputTemplate template) {
		return (item) -> {
			Set<Answer> answers = item.stream()
				.flatMap(answer -> answer.stream())
				.collect(Collectors.toSet());
			module.addAllAnswers(answers);
			moduleService.save(module);
		};
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	private ItemProcessor<Map<String, String>, Set<Answer>> validator(final Module module, final InputTemplate template) {
		return new AnswerItemProcessor(module, template, pipeline);
	}
}
