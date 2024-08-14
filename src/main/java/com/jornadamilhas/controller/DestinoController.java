package com.jornadamilhas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jornadamilhas.dto.DestinoDTO;
import com.jornadamilhas.dto.DestinoUpdaterDTO;
import com.jornadamilhas.service.DestinoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/destinos")
public class DestinoController {
	
	@Autowired
	private DestinoService destinoService;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody DestinoDTO dadosDestino) {
		var retorno = destinoService.salvarDestino(dadosDestino);
		return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
	}
	
	@GetMapping
	public ResponseEntity exibir() {
		return ResponseEntity.ok(destinoService.exibirDestinos());
	}
		
	@PutMapping
	public ResponseEntity atualizar(@RequestBody @Valid DestinoUpdaterDTO dadosAtualizacao) {
		var destinoAtualizado = destinoService.atualizarDestino(dadosAtualizacao);
		return ResponseEntity.ok(destinoAtualizado); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deletar(@PathVariable Long id) {
		return ResponseEntity.ok(destinoService.deletarDestino(id));
	}
	
	@GetMapping("/busca")
	public ResponseEntity buscarPeloNome(@RequestParam String nome) {
		return ResponseEntity.ok(destinoService.buscarDestinoPeloNome(nome));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		return ResponseEntity.ok(destinoService.detalharDestino(id));
	}

}
