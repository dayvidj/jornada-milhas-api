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
import org.springframework.web.bind.annotation.RestController;

import com.jornadamilhas.dto.DepoimentoDTO;
import com.jornadamilhas.dto.DepoimentoUpdaterDTO;
import com.jornadamilhas.service.DepoimentoService;

@RestController
@RequestMapping("/depoimentos")
public class DepoimentoController {

	@Autowired
	private DepoimentoService depoimentoService;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody DepoimentoDTO depoimento) {
		var retorno = depoimentoService.salvarDepoimento(depoimento);
		return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
	}	
	
	@GetMapping
	public ResponseEntity exibir() {	
		return ResponseEntity.ok(depoimentoService.listarDepoimentos());
	}
	
	@PutMapping
	public ResponseEntity atualizar(@RequestBody DepoimentoUpdaterDTO dados) {
		var depoimentoAtualizado = depoimentoService.atualizarPorId(dados);
		return ResponseEntity.ok(depoimentoAtualizado);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deletar(@PathVariable Long id) {
		return ResponseEntity.ok(depoimentoService.deletarPorID(id));
	}
	
}
