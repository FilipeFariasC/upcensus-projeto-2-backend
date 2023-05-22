package br.edu.ifpb.upcensus.business.form.configuration.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;

import br.edu.ifpb.upcensus.business.form.TestAplicationBaseCrud;
import br.edu.ifpb.upcensus.business.form.characteristic.resources.CharacteristicsEndpoints;
import br.edu.ifpb.upcensus.business.form.field.resources.FieldEndpoints;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.infrastructure.util.SeveralUtilities;
import br.edu.ifpb.upcensus.presentation.form.characteristic.request.CharacteristicRequest;
import br.edu.ifpb.upcensus.presentation.form.configuration.request.ConfigurationFieldRequest;
import br.edu.ifpb.upcensus.presentation.form.configuration.request.ConfigurationRequest;
import br.edu.ifpb.upcensus.presentation.form.field.request.FieldRequest;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class BaseCrudConfigurationTest extends TestAplicationBaseCrud{

	protected ConfigurationRequest configurationRequest = new ConfigurationRequest();
	
	protected ConfigurationFieldRequest configurationFieldRequest = new ConfigurationFieldRequest();
	
	protected HashSet<ConfigurationFieldRequest> fields =  new HashSet<ConfigurationFieldRequest>();
	
	protected HashSet<Long> idsCharacterist =  new HashSet<Long>();
	
	protected FieldRequest fieldRequest = new FieldRequest();
	
	@BeforeAll
	public void setup() throws Exception{
		
		fieldRequest.setCode(SeveralUtilities.getAlphaNumericString(5)+"."+SeveralUtilities.getAlphaNumericString(5));
		fieldRequest.setName("nome do teste");
		fieldRequest.setDescription("teste de campo de descrição");
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)));
		
		CharacteristicRequest characteristicRequest= new CharacteristicRequest();
		characteristicRequest.setAttribute(Attribute.MIN_VALUE);
		characteristicRequest.setValue("10");
		MvcResult resultado = mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andReturn();
		
		String id = resultado.getResponse().getRedirectedUrl().replaceAll("http://localhost/form/characteristics/", "");

		this.idsCharacterist.add(Long.parseLong(id));
	}
	
	@BeforeEach
	public void reset() throws Exception{
		
		configurationFieldRequest.setFieldCode("teste.teste");
		configurationFieldRequest.setCharacteristics(idsCharacterist);
		
		fields.add(configurationFieldRequest);
		
		configurationRequest.setCode(SeveralUtilities.getAlphaNumericString(10));
		configurationRequest.setName("testename");
		configurationRequest.setFields(fields);
		
	}
	
	@Test
	public void happy_flux_test_post() throws Exception{
		
		mockMvc.perform(post(ConfigurationEndpoints.CONFIGURATIONS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(configurationRequest)))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	public void happy_flux_test_get() throws Exception{
		
		MvcResult resultado = mockMvc.perform(post(ConfigurationEndpoints.CONFIGURATIONS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(configurationRequest)))
				.andExpect(status().isCreated())
				.andReturn();;
		
		String id = resultado.getResponse().getRedirectedUrl().replaceAll("http://localhost/form/configurations/", "");

		mockMvc.perform(get(ConfigurationEndpoints.CONFIGURATIONS+"/"+id)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(configurationRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.code").value(configurationRequest.getCode()))
				.andExpect(jsonPath("$.data.name").value(configurationRequest.getName()));
	}
	
	
	
	@Test
	public void happy_flux_test_delete() throws Exception{
		
		MvcResult resultado = mockMvc.perform(post(ConfigurationEndpoints.CONFIGURATIONS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(configurationRequest)))
				.andExpect(status().isCreated())
				.andReturn();;
		
		String id = resultado.getResponse().getRedirectedUrl().replaceAll("http://localhost/form/configurations/", "");

		mockMvc.perform(delete(ConfigurationEndpoints.CONFIGURATIONS+"/"+id)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(configurationRequest)))
				.andExpect(status().isNoContent());
		
	}
	
	@Test
	public void happy_flux_test_patch_bad() throws Exception{
		
		MvcResult resultado = mockMvc.perform(post(ConfigurationEndpoints.CONFIGURATIONS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(configurationRequest)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.data.code").value(configurationRequest.getCode()))
				.andReturn();
				
		String id = resultado.getResponse().getRedirectedUrl().replaceAll("http://localhost/form/configurations/", "");
		configurationFieldRequest.setFieldCode("teste_teste_patch");
		
		mockMvc.perform(patch(ConfigurationEndpoints.CONFIGURATIONS+"/"+id+"/fields/add")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(configurationFieldRequest)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void happy_flux_test_patch_created() throws Exception{
		
		MvcResult resultado = mockMvc.perform(post(ConfigurationEndpoints.CONFIGURATIONS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(configurationRequest)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.data.code").value(configurationRequest.getCode()))
				.andReturn();
				
		String id = resultado.getResponse().getRedirectedUrl().replaceAll("http://localhost/form/configurations/", "");
		System.out.println(id);
		
		
		configurationFieldRequest = new ConfigurationFieldRequest();
		
		configurationFieldRequest.setFieldCode("teste_created_add");

		mockMvc.perform(patch(ConfigurationEndpoints.CONFIGURATIONS+"/"+id+"/fields/add")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(configurationFieldRequest)))
				.andExpect(status().isCreated());
				
	}
	
	@Test
	public void test_null_code() throws Exception{
		
		configurationRequest.setCode(null);
		
		mockMvc.perform(post(ConfigurationEndpoints.CONFIGURATIONS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(configurationRequest)))
				.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void test_null_fields() throws Exception{
		
		configurationRequest.setFields(null);
		
		mockMvc.perform(post(ConfigurationEndpoints.CONFIGURATIONS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(configurationRequest)))
				.andExpect(status().isCreated());
		
	}
	
	
	
}
