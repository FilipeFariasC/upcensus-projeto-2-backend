package br.edu.ifpb.upcensus.batchTest.reader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@SpringBatchTest
public class readerTest {
	
	@Autowired
	private ItemReader<String> reader;
	
	@Test
	public void test_reader() throws Exception {
		FlatFileItemReader<String> flatFileReader = (FlatFileItemReader<String>) reader;
		flatFileReader.open(new ExecutionContext());
		assertFalse(flatFileReader.read().isEmpty());
	}
	
	@Test
	public void test_reader_codigo() throws Exception {
		FlatFileItemReader<String> flatFileReader = (FlatFileItemReader<String>) reader;
		flatFileReader.open(new ExecutionContext());
		String string = flatFileReader.read();
		flatFileReader.close();
		flatFileReader.open(new ExecutionContext());
		//assertEquals(string.getCodigo(), flatFileReader.read().());
	}
	
	@Test
	public void test_reader_type_context() throws Exception {
		FlatFileItemReader<String> flatFileReader = (FlatFileItemReader<String>) reader;
		flatFileReader.open(new ExecutionContext());
		//assertEquals(CursoIfpb.class, flatFileReader.read().getClass());
	}
	
	

}
