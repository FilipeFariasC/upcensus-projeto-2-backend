package br.edu.ifpb.upcensus.business.form.configuration.resources;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifpb.upcensus.business.form.TestAplicationBaseCrud;
import br.edu.ifpb.upcensus.domain.form.configuration.model.Configuration;
import br.edu.ifpb.upcensus.domain.form.configuration.model.ConfigurationCharacteristic;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class BaseCrudConfigurationTest extends TestAplicationBaseCrud{

	@Autowired
	protected Configuration configuration;
	
	@Autowired
	protected ConfigurationCharacteristic configurationCharacteristic;
	
	@BeforeAll
	public void setup() throws Exception{
		
	}
	
	@BeforeEach
	public void reset() throws Exception{
		
	}
}
