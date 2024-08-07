package com.jornadamilhas.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jornadamilhas.dto.DepoimentoDTO;
import com.jornadamilhas.dto.DepoimentoUpdaterDTO;
import com.jornadamilhas.model.Depoimento;
import com.jornadamilhas.repository.DepoimentoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DepoimentoService {
	
	@Autowired
	private DepoimentoRepository repository;
	
	@Transactional
	public DepoimentoUpdaterDTO salvarDepoimento(DepoimentoDTO dados) {
		var depoimento = repository.save(new Depoimento(dados));
		return new DepoimentoUpdaterDTO(depoimento);
	}
	
	@Transactional(readOnly = true)
	public List<DepoimentoUpdaterDTO> listarDepoimentos() {
		return repository.findAll().stream().map(DepoimentoUpdaterDTO::new).toList(); 
	}

	@Transactional
	public DepoimentoUpdaterDTO atualizarPorId(DepoimentoUpdaterDTO dados) {
		if(!repository.existsById(dados.id())) {
			throw new EntityNotFoundException("Depoimento não encontrado");
		}
		
		var depoimento = repository.getReferenceById(dados.id());
		depoimento.atualizarDados(dados);
		
		return new DepoimentoUpdaterDTO(depoimento);
	}

	@Transactional
	public String deletarPorID(Long id) {
		if(!repository.existsById(id)) {
			throw new EntityNotFoundException("Depoimento não encontrado");
		}
		repository.deleteById(id);
		return "Depoimento deletado com sucesso!";
	}
	 
	@Transactional(readOnly = true)
	public List<DepoimentoUpdaterDTO> listaRandom(){
		var depoimentos = repository.findAll();	
		
		var listaRandom = depoimentos.stream()
				.map(DepoimentoUpdaterDTO::new)
				.collect(Collectors.toCollection(ArrayList::new));			
		
		Collections.shuffle(listaRandom);
		
		return listaRandom.stream().limit(3).toList();
	}

}
