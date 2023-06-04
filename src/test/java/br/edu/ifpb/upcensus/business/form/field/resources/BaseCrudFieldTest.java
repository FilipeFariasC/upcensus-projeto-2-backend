package br.edu.ifpb.upcensus.business.form.field.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import br.edu.ifpb.upcensus.infrastructure.util.SeveralUtilities;
import br.edu.ifpb.upcensus.presentation.form.characteristic.request.CharacteristicRequest;
import br.edu.ifpb.upcensus.presentation.form.field.request.FieldRequest;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class BaseCrudFieldTest extends TestAplicationBaseCrud{

	protected FieldRequest fieldRequest = new FieldRequest();
	
	protected List<Long>  idsCharacterist =  new ArrayList<Long>();
	
	@BeforeAll
	public void setup() throws Exception{
		
		CharacteristicRequest characteristicRequest= new CharacteristicRequest();
		characteristicRequest.setAttribute(br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic.Attribute.MAX_VALUE);
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

		fieldRequest.setCode(SeveralUtilities.getAlphaNumericString(5)+"."+SeveralUtilities.getAlphaNumericString(5));
		fieldRequest.setName("nome do teste");
		fieldRequest.setDescription("teste de campo de descrição");
		fieldRequest.setCharacteristics(idsCharacterist);
	}
	
	@Test
	public void happy_flux_test_post() throws Exception{
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated());
	}
	
	
	@Test 
	public void test_null_characteristic() throws Exception{
	  
	fieldRequest.setCharacteristics(null);
	  
	 mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
		 .contentType("application/json")
		 .content(objectMapper.writeValueAsString(fieldRequest)))
		 .andExpect(status().isCreated()); 
	 }
	
	@Test 
	public void test_repeat_code() throws Exception{
	  
	 mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
		 .contentType("application/json")
		 .content(objectMapper.writeValueAsString(fieldRequest)))
		 .andExpect(status().isCreated()); 
	
	 mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
			 .contentType("application/json")
			 .content(objectMapper.writeValueAsString(fieldRequest)))
			 .andExpect(status().isBadRequest()); 
	}
	
	@Test
	public void test_code_size() throws Exception{
		
		fieldRequest.setCode(SeveralUtilities.getAlphaNumericString(129));
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isBadRequest());
		
		fieldRequest.setCode(SeveralUtilities.getAlphaNumericString(2));
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void test_name_size() throws Exception{
		
		fieldRequest.setName(SeveralUtilities.getAlphaNumericString(129));
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isBadRequest());
		
		fieldRequest.setName(SeveralUtilities.getAlphaNumericString(2));
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void test_description_size() throws Exception{
		
		fieldRequest.setDescription(SeveralUtilities.getAlphaNumericString(513));
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isBadRequest());
		
		fieldRequest.setDescription(SeveralUtilities.getAlphaNumericString(512));
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	public void description_string_special_character_test() throws Exception{
		
		fieldRequest.setDescription("aá'?@.#$%!aaá'?@.#$%!a");
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void code_string_special_character_test() throws Exception{
		
		fieldRequest.setCode(SeveralUtilities.getSpecialString(10));
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void name_string_special_character_test() throws Exception{
		
		fieldRequest.setName("aá'?@.#$%!áaá'?@.#$%!á&*(@");
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void happy_flux_delete_test() throws Exception{
		
		MvcResult resultado =mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated())
				.andReturn();
		
		int idDelete = Integer.parseInt(resultado.getResponse().getRedirectedUrl().replaceAll("http://localhost/form/fields/", ""));
		
		mockMvc.perform(delete(FieldEndpoints.FIELDS_ABSOLUTE+"/"+idDelete)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString("")))
				.andExpect(status().isNoContent());

	}
	
	@Test
	public void happy_flux_update_test() throws Exception{
		
		
		MvcResult resultado =mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated())
				.andReturn();
		
		fieldRequest.setCode(SeveralUtilities.getAlphaNumericString(5)+"."+SeveralUtilities.getAlphaNumericString(5));
		fieldRequest.setName("nome do testev2");
		fieldRequest.setDescription("teste de campo de descriçãov2");
		
		int idUp = Integer.parseInt(resultado.getResponse().getRedirectedUrl().replaceAll("http://localhost/form/fields/", ""));
		
		mockMvc.perform(put(FieldEndpoints.FIELDS_ABSOLUTE+"/"+idUp)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(idUp))
				.andExpect(jsonPath("$.data.code").value(fieldRequest.getCode()))
				.andExpect(jsonPath("$.data.name").value(fieldRequest.getName()))
				.andExpect(jsonPath("$.data.description").value(fieldRequest.getDescription()));
	}
	
	@Test
	public void happy_flux_get_id_test() throws Exception{
		
		MvcResult resultado =mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated())
				.andReturn();
		
		int id = Integer.parseInt(resultado.getResponse().getRedirectedUrl().replaceAll("http://localhost/form/fields/", ""));
		
		mockMvc.perform(get(FieldEndpoints.FIELDS_ABSOLUTE+"/"+id)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString("")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(id))
				.andExpect(jsonPath("$.data.code").value(fieldRequest.getCode()))
				.andExpect(jsonPath("$.data.name").value(fieldRequest.getName()))
				.andExpect(jsonPath("$.data.description").value(fieldRequest.getDescription()));
	}
	
	@Test
	public void get_characteristic() throws Exception{
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated())
				.andReturn();
		
		mockMvc.perform(get("http://localhost/form/fields/1/characteristics")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString("")))
				.andExpect(status().isOk());
				/*.andExpect(jsonPath("$.data[0].value").value("10"))
				.andExpect(jsonPath("$.data[0].attribute").value("MIN_VALUE"));*/
	}
	
	@Test
	public void add_exist_characteristic() throws Exception{
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated())
				.andReturn();
		
		mockMvc.perform(patch("http://localhost/form/fields/1/characteristics/add")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString("")))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void add_not_exist_characteristic() throws Exception{
		
		CharacteristicRequest characteristicRequest= new CharacteristicRequest();
		characteristicRequest.setAttribute(br.edu.ifpb.upcensus.domain.form.characteristic.model.Characteristic.Attribute.MIN_VALUE);
		characteristicRequest.setValue("10");
		MvcResult resultado = mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andReturn();
		String id = resultado.getResponse().getRedirectedUrl().replaceAll("http://localhost/form/characteristics/", "");
		this.idsCharacterist.add(Long.parseLong(id));
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated())
				.andReturn();
		
		mockMvc.perform(patch("http://localhost/form/fields/1/characteristics/add")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(idsCharacterist)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void remove_exist_characteristic() throws Exception{
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated())
				.andReturn();
		
		mockMvc.perform(patch("http://localhost/form/fields/1/characteristics/remove")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(idsCharacterist)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void remove_not_exist_characteristic() throws Exception{
		
		mockMvc.perform(post(FieldEndpoints.FIELDS_ABSOLUTE)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(fieldRequest)))
				.andExpect(status().isCreated())
				.andReturn();
		
		
		mockMvc.perform(patch("http://localhost/form/fields/1/characteristics/remove")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString("")))
				.andExpect(status().isBadRequest());
	}
	
	 
}
