package com.jornadamilhas.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.jornadamilhas.service.DepoimentoService;

@SpringBootTest
@AutoConfigureMockMvc
class DepoimentoControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private DepoimentoService service;

	@Test
	void testDeveRetornarStatus201QuandoSalvaDepoimento() throws Exception {
		// ARRANGE: Configura os dados necessários para o teste
	    // Cria um JSON com os dados do depoimento a ser salvo
		String json = """ 
				{
				    "foto": "teste",
				    "texto": "teste",
				    "nome": "teste"
				}
				""";
		
		// ACT: Executa a ação a ser testada
	    // Simula uma requisição POST para o endpoint "/depoimentos" com o JSON criado
		var response = mvc.perform(
				post("/depoimentos")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
		).andReturn().getResponse();
		
		// ASSERT: Verifica se o resultado está conforme o esperado
		// Checa se o código de status HTTP da resposta é 201 (Created), indicando que o recurso foi criado com sucesso
		Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	void testDeveRetornarStatus200QuandoConsultaDepoimentos() throws Exception {
		//ACT
		var response = mvc.perform(
				get("/depoimentos"))
				.andReturn()
				.getResponse();
		//ASSERT
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	void testDeveRetornarStatus200QuandoAtualizaDepoimento() throws Exception {
		//ARRENGE 
		String json = """ 
				{
					"id": 1,
				    "foto": "testeUpdate",
				    "texto": "teste"
				}
				""";
		
		//ACT
		var response = mvc.perform(
				put("/depoimentos")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
		
		//ASSERT
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	void testDeveRetornarStatus200QuandoDeletaDepoimento() throws Exception {
		//ARRENGE 
		Long idExistente = 1l;
		
		//ACT
		var response = mvc.perform(
				delete("/depoimentos/{id}", idExistente))
				.andReturn()
				.getResponse();
		
		//ASSERT
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	void testDeveRetornarStatus200QuandoExibeDepoimentosAleatorios() throws Exception {
		//ACT
		var response = mvc.perform(
				get("/depoimentos-home"))
				.andReturn()
				.getResponse();
		
		//ASSERT
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
}
