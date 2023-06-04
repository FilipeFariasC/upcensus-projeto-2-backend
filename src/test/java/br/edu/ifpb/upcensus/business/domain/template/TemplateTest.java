package br.edu.ifpb.upcensus.business.domain.template;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifpb.upcensus.business.form.TestAplicationBaseCrud;
import br.edu.ifpb.upcensus.business.module.template.resources.InputTemplateEndpoints;
import br.edu.ifpb.upcensus.domain.module.template.model.InputTemplate.Type;
import br.edu.ifpb.upcensus.infrastructure.util.SeveralUtilities;
import br.edu.ifpb.upcensus.presentation.module.template.request.InputTemplateRequest;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class TemplateTest extends TestAplicationBaseCrud{

	protected InputTemplateRequest templateRequest = new InputTemplateRequest();
	
	private Map<String, String> mappings = new HashMap();
	
	
	@BeforeEach
	public void reset() throws Exception{
		String code = SeveralUtilities.getTestTemplate();
		mappings.put(code,SeveralUtilities.getNumericString(1));
		templateRequest.setType(Type.CSV);
		templateRequest.setCode(SeveralUtilities.getAlphaNumericString(5));
		templateRequest.setName("Modelo de arquivo teste");
		templateRequest.setMappings(mappings);
		templateRequest.setFieldIdentifier(code);
	}
	
	@Test
	public void happy_flux_test_post_input() throws Exception{
		mockMvc.perform(post("http://localhost/module/templates/input")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(templateRequest)))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	public void happy_flux_test_get() throws Exception{
		mockMvc.perform(get(InputTemplateEndpoints.TEMPLATES)
			.contentType("application/json")
			.content(objectMapper.writeValueAsString("")))
			.andExpect(status().isOk());
	}
	
	
}
