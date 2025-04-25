package com.jornadamilhas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.jornadamilhas.dto.DepoimentoResponseDTO;
import com.jornadamilhas.service.DepoimentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/depoimentos")
public class DepoimentoController {

	@Autowired
	private DepoimentoService depoimentoService;

	@PostMapping
	public ResponseEntity<DepoimentoResponseDTO> salvar(@RequestBody @Valid DepoimentoDTO depoimento) {
		var depoimentoSalvo = depoimentoService.salvarDepoimento(depoimento);
		return ResponseEntity.status(HttpStatus.CREATED).body(depoimentoSalvo);
	}

	@GetMapping
	public ResponseEntity<Page<DepoimentoResponseDTO>> exibirTodos(Pageable pageable) {
		return ResponseEntity.ok(depoimentoService.listarDepoimentos(pageable));
	}

	@GetMapping("home")
	public ResponseEntity<List<DepoimentoDTO>> exibirAleatorio() {
		return ResponseEntity.ok(depoimentoService.listaAleatoria());
	}
	
	@PutMapping
	public ResponseEntity<DepoimentoDTO> atualizar(@RequestBody @Valid DepoimentoResponseDTO dados) {
		var depoimentoAtualizado = depoimentoService.atualizarPorId(dados);
		return ResponseEntity.ok(depoimentoAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		return ResponseEntity.ok(depoimentoService.deletarPorID(id));
	}

}
