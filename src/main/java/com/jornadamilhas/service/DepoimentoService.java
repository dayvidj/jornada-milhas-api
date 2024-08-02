package com.jornadamilhas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jornadamilhas.dto.DepoimentoDTO;
import com.jornadamilhas.dto.DepoimentoUpdaterDTO;
import com.jornadamilhas.model.Depoimento;
import com.jornadamilhas.repository.DepoimentoRepository;

@Service
public class DepoimentoService {
	
	@Autowired
	private DepoimentoRepository repository;
	
	@Transactional
	public String salvarDepoimento(DepoimentoDTO dados) {
		repository.save(new Depoimento(dados));
		return "Depoimento salvo com sucesso!";
	}
	
	@Transactional(readOnly = true)
	public List<DepoimentoDTO> listarDepoimentos() {
		return repository.findAll().stream().map(DepoimentoDTO::new).toList(); 
	}

	@Transactional
	public DepoimentoDTO atualizarPorId(DepoimentoUpdaterDTO dados) {
		var depoimento = repository.getReferenceById(dados.id());
		depoimento.atualizarDados(dados);
		return new DepoimentoDTO(depoimento);
	}

	@Transactional
	public String deletarPorID(Long id) {
		repository.deleteById(id);
		return ("Depoimento deletado com sucesso!");
	}

}
