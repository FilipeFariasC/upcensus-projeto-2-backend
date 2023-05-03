package br.edu.ifpb.upcensus.business.job;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Validation;

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
import org.springframework.batch.core.step.job.JobStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.edu.ifpb.upcensus.domain.form.field.model.Field;
import br.edu.ifpb.upcensus.domain.form.field.service.FieldService;
import br.edu.ifpb.upcensus.domain.module.module.model.Answer;
import br.edu.ifpb.upcensus.domain.module.module.model.Module;
import br.edu.ifpb.upcensus.domain.module.module.service.ModuleService;
import br.edu.ifpb.upcensus.domain.module.template.model.Template;
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

	public JobService(final JobBuilderFactory jobFactory, final StepBuilderFactory stepBuilderFactory,
			final JobLauncher jobLauncher) {
		super();
		this.jobFactory = jobFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.jobLauncher = jobLauncher;
	}

	public void runFileJob(MultipartFile file, Module module, boolean ignoreHeaderRow, FileType fileType,
			String delimiter) {
		try {

			Template template = module.getTemplateByFileType(fileType);
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

	public Step readFileStep(MultipartFile file, Module module, Template template, boolean ignoreHeaderRow,
			String delimiter) throws IOException {
		return stepBuilderFactory.get("readFileSaveInDatabase").<Map<String, String>, Map<String, String>>chunk(1)
				.reader(getFileReader(file, template, ignoreHeaderRow, delimiter)).processor(validator())
				.writer(writeToDatabase(module, template)).build();
	}

	public void runAnswerJob(Module module, Template template, List<Map<String, String>> answers) {
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

	public Step readAnswerStep(List<Map<String, String>> answers, Module module, Template template) throws IOException {
		return stepBuilderFactory.get("readAnswersSaveInDatabase").<Map<String, String>, Map<String, String>>chunk(1)
				.reader(getAnswerReader(answers))
				.processor(validator())
				.writer(writeToDatabase(module, template))
				.build();
	}

	private ItemReader<Map<String, String>> getFileReader(MultipartFile file, Template template,
			boolean ignoreHeaderRow, String delimiter) throws IOException {
		System.out.println("???");
		switch (template.getFileType()) {
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

	private ItemWriter<Map<String, String>> writeToDatabase(Module module, Template template) {
		System.out.println("???");
		return (item) -> {
			System.out.println(item);
			final String codeIdentifier = template.getFieldIdentifier().getCode();

			Set<Answer> answers = item.stream().flatMap(map -> {
				final String identifier = map.get(codeIdentifier);
				return map.entrySet().stream().map(entry -> {
					final Field field = template.getFieldFromCode(entry.getKey());
					final Answer answer = Answer.of(module, template, field, identifier, entry.getValue());
					answer.register();
					return answer;
				});
			}).collect(Collectors.toSet());
			module.addAllAnswers(answers);
			moduleService.save(module);
		};
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	private ItemProcessor<Map<String, String>, Map<String, String>> validator() {
		System.out.println("???");
		return (item) -> item;
	}
}
