package br.edu.ifpb.upcensus.business.form.characteristic.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;

import br.edu.ifpb.upcensus.business.form.TestAplicationBaseCrud;
import br.edu.ifpb.upcensus.domain.form.characteristic.model.Attribute;
import br.edu.ifpb.upcensus.infrastructure.util.SeveralUtilities;
import br.edu.ifpb.upcensus.presentation.form.characteristic.request.CharacteristicRequest;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class BaseCrudCharacteristicTest extends TestAplicationBaseCrud{

	protected CharacteristicRequest characteristicRequest= new CharacteristicRequest();
	
	protected Random random = new Random();
	
	@BeforeEach
	public void reset() throws Exception{
		
		characteristicRequest.setAttribute(Attribute.MIN_VALUE);
		characteristicRequest.setValue(""+random.nextInt(11));
		characteristicRequest.setDescription("valor teste");
	}
	
	@Test
	public void happy_flux_test_post() throws Exception{
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void happy_flux_test_delete() throws Exception{
		
		MvcResult resultado =mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated())
				//.andExpect(jsonPath("$.data.id").hasJsonPath())
				.andReturn();
		
		int idDelete = Integer.parseInt(resultado.getResponse().getRedirectedUrl().replaceAll("http://localhost/form/characteristics/", ""));
		
		mockMvc.perform(delete(CharacteristicsEndpoints.CHARACTERISTICS+"/"+idDelete)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString("")))
				.andExpect(status().isNoContent());
		
		mockMvc.perform(get(CharacteristicsEndpoints.CHARACTERISTICS+"/"+idDelete)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString("")))
				.andExpect(status().isNotFound());

	}
	
	@Test
	public void happy_flux_test_update() throws Exception{
		
		
		MvcResult resultado =mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated())
				.andReturn();
		
		characteristicRequest.setAttribute(Attribute.TYPE);
		characteristicRequest.setValue("5");
		characteristicRequest.setDescription("valor teste update");
		
		int idUp = Integer.parseInt(resultado.getResponse().getRedirectedUrl().replaceAll("http://localhost/form/characteristics/", ""));
		
		mockMvc.perform(put(CharacteristicsEndpoints.CHARACTERISTICS+"/"+idUp)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(idUp))
				.andExpect(jsonPath("$.data.attribute").value(characteristicRequest.getAttribute().toString()))
				.andExpect(jsonPath("$.data.value").value(characteristicRequest.getValue()))
				.andExpect(jsonPath("$.data.description").value(characteristicRequest.getDescription()));
	}
	
	@Test
	public void happy_flux_get_id_test() throws Exception{
		
		MvcResult resultado =mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated())
				.andReturn();
		
		int id = Integer.parseInt(resultado.getResponse().getRedirectedUrl().replaceAll("http://localhost/form/characteristics/", ""));
		
		mockMvc.perform(get(CharacteristicsEndpoints.CHARACTERISTICS+"/"+id)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString("")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(id))
				.andExpect(jsonPath("$.data.attribute").value(characteristicRequest.getAttribute().toString()))
				.andExpect(jsonPath("$.data.value").value(characteristicRequest.getValue()))
				.andExpect(jsonPath("$.data.description").value(characteristicRequest.getDescription()));
	}
	
	@Test
	public void atribute_max_value_test() throws Exception{
		
		characteristicRequest.setAttribute(Attribute.MAX_VALUE);
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void atribute_min_length_test() throws Exception{
		
		characteristicRequest.setAttribute(Attribute.MIN_LENGTH);
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void atribute_max_length_test() throws Exception{
		
		characteristicRequest.setAttribute(Attribute.MAX_LENGTH);
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void atribute_type_test() throws Exception{
		
		characteristicRequest.setAttribute(Attribute.TYPE);
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void atribute_required_test() throws Exception{
		
		characteristicRequest.setAttribute(Attribute.REQUIRED);
		characteristicRequest.setValue("true");
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
		//perguntar se precisa de bloqueio no value para true ou false
	}
	
	@Test
	public void atribute_pattern_test() throws Exception{
		
		characteristicRequest.setAttribute(Attribute.PATTERN);
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void atribute_null_test() throws Exception{
		
		characteristicRequest.setAttribute(null);
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isBadRequest());
	}
	
	
	@Test
	public void value_string_special_character_test() throws Exception{
		
		characteristicRequest.setValue("aá'?@#$%!aa");
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void description_string_special_character_test() throws Exception{
		
		characteristicRequest.setDescription("aá'?@#$%!aa");
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void flux_test_all_null() throws Exception{
		
		characteristicRequest.setAttribute(null);
		characteristicRequest.setValue(null);
		characteristicRequest.setDescription(null);
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void test_value_size() throws Exception{
		
		characteristicRequest.setValue(SeveralUtilities.getAlphaNumericString(129));
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isBadRequest());
		
		characteristicRequest.setValue(SeveralUtilities.getAlphaNumericString(128));
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void test_description_size() throws Exception{
		
		characteristicRequest.setDescription(SeveralUtilities.getAlphaNumericString(513));
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isBadRequest());
		
		characteristicRequest.setDescription(SeveralUtilities.getAlphaNumericString(512));
		
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test 
	public void test_repeat() throws Exception{
	  
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
	
		mockMvc.perform(post(CharacteristicsEndpoints.CHARACTERISTICS)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(characteristicRequest)))
				.andExpect(status().isCreated());
	
	}
	
	
	
}
