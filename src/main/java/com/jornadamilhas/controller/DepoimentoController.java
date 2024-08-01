package com.jornadamilhas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jornadamilhas.dto.DepoimentoDTO;
import com.jornadamilhas.model.Depoimento;
import com.jornadamilhas.repository.DepoimentoRepository;

@RestController
@RequestMapping("/depoimentos")
public class DepoimentoController {

	@Autowired
	private DepoimentoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity salvar(@RequestBody DepoimentoDTO dadosDepoimento) {
		Depoimento depoimento = new Depoimento(dadosDepoimento);
		repository.save(depoimento);
		return ResponseEntity.ok(depoimento);
	}	
	
	@GetMapping
	public ResponseEntity recuperar() {	
		return ResponseEntity.ok(repository.findAll());
	}
	
	
}
