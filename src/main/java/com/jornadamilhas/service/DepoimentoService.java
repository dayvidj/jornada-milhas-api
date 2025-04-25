package com.jornadamilhas.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jornadamilhas.dto.DepoimentoDTO;
import com.jornadamilhas.dto.DepoimentoResponseDTO;
import com.jornadamilhas.exception.ObjetoNaoEncontadoException;
import com.jornadamilhas.model.Depoimento;
import com.jornadamilhas.repository.DepoimentoRepository;

@Service
public class DepoimentoService {

	@Autowired
	private DepoimentoRepository repository;

	@Transactional
	public DepoimentoResponseDTO salvarDepoimento(DepoimentoDTO dados) {
		var depoimento = repository.save(new Depoimento(dados));
		return new DepoimentoResponseDTO(depoimento);
	}

	@Transactional(readOnly = true)
	public Page<DepoimentoResponseDTO> listarDepoimentos(Pageable pageable) {
		return repository.findAll(pageable).map(DepoimentoResponseDTO::new);
	}
	
	@Transactional(readOnly = true)
	public List<DepoimentoDTO> listaAleatoria() {
		var depoimentos = repository.findAll();
		
		var listaRandom = depoimentos.stream().map(DepoimentoDTO::new)
				.collect(Collectors.toCollection(ArrayList::new));
		
		Collections.shuffle(listaRandom);
		return listaRandom.stream().limit(3).toList();
	}

	@Transactional
	public DepoimentoDTO atualizarPorId(DepoimentoResponseDTO dados) {
		var depoimento = repository.findById(dados.id())
				.orElseThrow(() -> new ObjetoNaoEncontadoException("Depoimento não encontrado"));

		depoimento.atualizarDados(dados);
		return new DepoimentoDTO(depoimento);
	}

	@Transactional
	public String deletarPorID(Long id) {
		repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontadoException("Depoimento com ID "+id+" não existe."));
		
		repository.deleteById(id);
		return "Depoimento deletado com sucesso!";
	}

}
