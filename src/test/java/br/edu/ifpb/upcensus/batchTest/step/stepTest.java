package br.edu.ifpb.upcensus.batchTest.step;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.batch.runtime.BatchStatus;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@SpringBatchTest
public class stepTest {

	@Autowired
	private JobLauncherTestUtils jobUtils;
	
	@Test
	public void test_step_reader_writer() {
		JobExecution jobExecution =jobUtils.launchStep("cursosIfpbParaArquivo");
		StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();

		assertEquals(stepExecution.getCommitCount()-1, stepExecution.getReadCount());
		assertEquals(stepExecution.getCommitCount()-1, stepExecution.getWriteCount());
	}
	
	@Test
	public void test_step_skip_args() {
		JobExecution jobExecution =jobUtils.launchStep("cursosIfpbParaArquivo");
		StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();

		assertEquals(0,stepExecution.getSkipCount());
	}
	
	@Test
	public void test_step_name() {
		JobExecution jobExecution =jobUtils.launchStep("cursosIfpbParaArquivo");
		StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();
		
		assertEquals("cursosIfpbParaArquivo",stepExecution.getStepName());
	}
	
	@Test
	public void test_step_lines() {
		JobExecution jobExecution =jobUtils.launchStep("cursosIfpbParaArquivo");
		StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();
		
		assertEquals(stepExecution.getReadCount()+1, stepExecution.getCommitCount());
		
	}
	
	@Test
	public void test_step_skiped_writer_reader() {
		JobExecution jobExecution =jobUtils.launchStep("cursosIfpbParaArquivo");
		StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();
		
		assertEquals(0, stepExecution.getReadSkipCount());
		assertEquals(0, stepExecution.getWriteSkipCount());
		
	}
	
	@Test
	public void test_step_skip_args_exit() {
		JobExecution jobExecution =jobUtils.launchStep("cursosIfpbParaArquivo");
		StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();

		assertEquals(ExitStatus.COMPLETED.getExitCode(),stepExecution.getExitStatus().getExitCode());
	}
	
	@Test
	public void test_step_skip_args_status() {
		JobExecution jobExecution =jobUtils.launchStep("cursosIfpbParaArquivo");
		StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();

		assertEquals(jobExecution.getStatus().getBatchStatus(), BatchStatus.COMPLETED);
	}
}
