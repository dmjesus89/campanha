package com.br.campanha.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.br.campanha.CampanhaApplication;
import com.br.campanha.mvc.entity.CampanhaEntity;
import com.br.campanha.mvc.entity.TimeEntity;
import com.br.campanha.service.CampanhaService;

/**
 * @author Josh Long
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CampanhaApplication.class)
@WebAppConfiguration
public class CampanhaControllerTeste {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private CampanhaService campanhaService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void inserirCampanha() throws Exception {

		String campanhaWithJson = json(criarMockitoCampanha1());

		this.mockMvc.perform(post("/campanha/inserirCampanha").contentType(contentType).content(campanhaWithJson))
				.andExpect(status().isCreated());

	}

	@Test
	public void inserirCampanhaDataInvalida() throws Exception {
		CampanhaEntity campanha = criarMockitoCampanha1();
		campanha.setDtInicio(campanha.getDtFim().plusDays(1));
		String campanhaWithJson = json(campanha);

		this.mockMvc.perform(post("/campanha/inserirCampanha").contentType(contentType).content(campanhaWithJson))
				.andExpect(status().isExpectationFailed()).andReturn();

	}

	@Test
	public void inserirCampanhaInvalida() throws Exception {
		CampanhaEntity campanha = criarMockitoCampanha1();
		campanha.setDtInicio(null);
		String campanhaWithJson = json(campanha);

		this.mockMvc.perform(post("/campanha/inserirCampanha").contentType(contentType).content(campanhaWithJson))
				.andExpect(status().isBadRequest()).andReturn();

	}

	@Test
	public void listarTodosVigentes() throws Exception {
		String campanhaWithJson = json(criarMockitoCampanha1());

		this.mockMvc.perform(post("/campanha/inserirCampanha").contentType(contentType).content(campanhaWithJson))
				.andExpect(status().isCreated()).andReturn();

		MvcResult result = this.mockMvc
				.perform(get("/campanha/listaCampanhasVigente").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		assertTrue(result.getResponse().getContentAsString().contains("Sou mais Vitória"));
	}

	@Test
	public void removerCampanha() throws Exception {
		String campanhaWithJson = json(criarMockitoCampanha1());

		this.mockMvc.perform(post("/campanha/inserirCampanha").contentType(contentType).content(campanhaWithJson))
				.andExpect(status().isCreated()).andReturn();

		for (CampanhaEntity campanha : this.campanhaService.listarTodosVigentes()) {
			this.mockMvc.perform(
					delete("/campanha/removerCampanha/{id}", campanha.getId()).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isNoContent()).andReturn();
		}

		this.mockMvc.perform(get("/campanha/listaCampanhasVigente").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void removerCampanhaNaoEncontrada() throws Exception {

		Long id = 99L;

		this.mockMvc.perform(delete("/campanha/removerCampanha/{id}", id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();

	}

	@Test
	public void listarTodosVigentesSemRetorno() throws Exception {

		for (CampanhaEntity campanha : this.campanhaService.listarTodosVigentes()) {
			this.mockMvc.perform(
					delete("/campanha/removerCampanha/{id}", campanha.getId()).accept(MediaType.APPLICATION_JSON))
					.andReturn();
		}

		MvcResult result = this.mockMvc
				.perform(get("/campanha/listaCampanhasVigente").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		assertTrue(result.getResponse().getContentAsString().equals("[]"));
	}

	private CampanhaEntity criarMockitoCampanha1() {
		TimeEntity timeCoracao = new TimeEntity("Esporte Clube Vitória");
		LocalDate dataInicioVigencia = LocalDate.of(2017, 11, 1);
		LocalDate dataFimVigencia = LocalDate.now();
		CampanhaEntity campanha = new CampanhaEntity("Sou mais Vitória", dataInicioVigencia, dataFimVigencia,
				timeCoracao);
		return campanha;
	}

	private CampanhaEntity criarMockitoCampanha2() {
		TimeEntity timeCoracao = new TimeEntity("Palmeiras");
		LocalDate dataInicioVigencia = LocalDate.of(2017, 11, 1);
		LocalDate dataFimVigencia = LocalDate.now();

		CampanhaEntity campanha = new CampanhaEntity("Avante Palmeiras", dataInicioVigencia, dataFimVigencia,
				timeCoracao);
		return campanha;
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}