package com.jornadamilhas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jornadamilhas.dto.DestinoDTO;
import com.jornadamilhas.dto.DestinoUpdaterDTO;
import com.jornadamilhas.model.Destino;
import com.jornadamilhas.repository.DestinoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DestinoService {

	@Autowired
	private DestinoRepository repository;
	
	@Transactional
	public DestinoUpdaterDTO salvarDestino(DestinoDTO dados) {
		var destino = repository.save(new Destino(dados));
		return new DestinoUpdaterDTO(destino);
	}

	@Transactional(readOnly = true)
	public List<DestinoUpdaterDTO> exibirDestinos() {
		var destinos = repository.findAll().stream().map(DestinoUpdaterDTO::new).toList();
		return destinos;
	}

	@Transactional
	public DestinoUpdaterDTO atualizarDestino(DestinoUpdaterDTO dadosAtualizacao) {
		if(!repository.existsById(dadosAtualizacao.id())) {
			throw new EntityNotFoundException("Destino não encontrado com id: "+dadosAtualizacao.id());
		}
	
		var destino = repository.getReferenceById(dadosAtualizacao.id());
		destino.atualizarDados(dadosAtualizacao);
	
		return new DestinoUpdaterDTO(destino);
	}

	@Transactional
	public String deletarDestino(Long id) {
		if(!repository.existsById(id)) {
			throw new EntityNotFoundException("Destino não encontrado com id: "+id);
		}
		repository.deleteById(id);
		
		return "Destino deletado com sucesso.";
	}

	@Transactional(readOnly = true)
	public DestinoUpdaterDTO buscarDestinoPeloNome(String nome) {
		if(repository.findByNome(nome) == null) {
			throw new EntityNotFoundException("Nenhum destino foi encontrado");
		}
		var destino = repository.findByNome(nome);
		return new DestinoUpdaterDTO(destino);			
	}

	@Transactional(readOnly = true)
	public DestinoDTO detalharDestino(Long id) {
		if(!repository.existsById(id)) {
			throw new EntityNotFoundException("Nenhum destino foi encontrado");
		}
		var destino = new DestinoDTO(repository.getReferenceById(id));
		return destino;
	}
	
}
