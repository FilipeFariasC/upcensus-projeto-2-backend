package br.edu.ifpb.upcensus.business.domain.template;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifpb.upcensus.business.form.TestAplicationBaseCrud;
import br.edu.ifpb.upcensus.infrastructure.domain.FileType;
import br.edu.ifpb.upcensus.presentation.module.template.request.TemplateRequest;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class TemplateTest extends TestAplicationBaseCrud{

	protected TemplateRequest templateRequest = new TemplateRequest();
	
	private Map<String, String> mappings;
	
	@BeforeEach
	public void reset() throws Exception{
		//mappings.put("aluno.nome","1");
		templateRequest.setFileType(FileType.CSV);
		templateRequest.setCode("CSV_ALUNOS");
		templateRequest.setName("Modelo de arquivo teste");
		//templateRequest.setMappings(mappings);
	}
	
	@Test
	public void happy_flux_test_post_input() throws Exception{
		mockMvc.perform(post("http://localhost/module/templates/input")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(templateRequest)))
				.andExpect(status().isCreated());
	}
	
	
}
