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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jornadamilhas.dto.DestinoDTO;
import com.jornadamilhas.dto.DestinoResponseDTO;
import com.jornadamilhas.service.DestinoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

	@Autowired
	private DestinoService destinoService;

	@PostMapping
	public ResponseEntity<DestinoResponseDTO> salvar(@RequestBody @Valid DestinoDTO dadosDestino) {
		var retorno = destinoService.salvarDestino(dadosDestino);
		return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
	}

	@GetMapping
	public ResponseEntity<Page<DestinoResponseDTO>> exibirTodos(Pageable pageable) {
		return ResponseEntity.ok(destinoService.exibirDestinos(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DestinoDTO> detalhar(@PathVariable Long id) {
		return ResponseEntity.ok(destinoService.detalharDestino(id));
	}

	@GetMapping("/busca")
	public ResponseEntity<List<DestinoDTO>> buscarPeloNome(@RequestParam String nome) {
		return ResponseEntity.ok(destinoService.buscarDestinosPeloNome(nome));
	}

	@PutMapping
	public ResponseEntity<DestinoDTO> atualizar(@RequestBody @Valid DestinoResponseDTO dadosAtualizacao) {
		var destinoAtualizado = destinoService.atualizarDestino(dadosAtualizacao);
		return ResponseEntity.ok(destinoAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		return ResponseEntity.ok(destinoService.deletarDestino(id));
	}

}
