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

import com.jornadamilhas.service.DestinoService;

@SpringBootTest
@AutoConfigureMockMvc
class DestinoControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private DestinoService service;
	
	@Test
	void testDeveRestornarStatus201QuandoSalvaDestino() throws Exception {
		//ARRENGE
		String json = """
				{
				    "fotoOne": "img.t1",
				    "fotoTwo": "img.t2",
				    "nome": "teste",
				    "meta": "exemplo teste",
				    "texto": "texto exemplo teste",
				    "preco": 99.99
			    }
				""";
		//ACT
		var response = mvc.perform(post("/destinos")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse();
		
		//ASSERT
		Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	void testDeveRestornarStatus200QuandoBuscaTodosOsDestinos() throws Exception {
		//ACT
		var response = mvc.perform(get("/destinos"))
				.andReturn()
				.getResponse()
				.getStatus();
		
		//ASSERT
		Assertions.assertEquals(HttpStatus.OK.value(), response);
	}
	
	@Test
	void testDeveRestornarStatus200QuandoAtualizaDadosDeDestino() throws Exception {
		//ARRENGE
		String json = """
				{
				    "id": 1,
				    "nome": "nome update"
				}
				""";
		//ACT
		var response = mvc.perform(put("/destinos")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse();
		
		//ASSERT
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	void testDeveRestornarStatus200QuandoDeletaDestino() throws Exception {
		//ARRENGE 
		Long idExistente = 1l;
		
		//ACT
		var response = mvc.perform(delete("/destinos/{id}", idExistente))
				.andReturn()
				.getResponse()
				.getStatus();
		
		//ASSERT
		Assertions.assertEquals(HttpStatus.OK.value(), response);
	}
	
	@Test
	void testDeveRestornarStatus200QuandoBuscaDestinoPeloNome() throws Exception {
		//ARRENGE
		String nome = "teste";
		//ACT
		var response = mvc.perform(get("/destinos/busca?nome=teste", nome))
				.andReturn()
				.getResponse()
				.getStatus();
		//ASSERT
		Assertions.assertEquals(HttpStatus.OK.value(), response);
	}
	
	@Test
	void testDeveRestornarStatus200AoDetalharDestinoPeloID() throws Exception {
		//ARRENGE
		Long idExistente = 1l;
		//ACT
		var response = mvc.perform(get("/destinos/{id}", idExistente))
				.andReturn()
				.getResponse()
				.getStatus();
		//ASSERT
		Assertions.assertEquals(HttpStatus.OK.value(), response);
	}
	
	
}
