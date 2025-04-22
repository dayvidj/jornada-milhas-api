package com.jornadamilhas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jornadamilhas.dto.DestinoDTO;
import com.jornadamilhas.dto.DestinoUpdaterDTO;
import com.jornadamilhas.exception.ObjetoNaoEncontadoException;
import com.jornadamilhas.model.Destino;
import com.jornadamilhas.repository.DestinoRepository;

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
	public DestinoDTO atualizarDestino(DestinoUpdaterDTO dadosAtualizacao) {
		if(!repository.existsById(dadosAtualizacao.id())) {
			throw new ObjetoNaoEncontadoException("Destino não encontrado com id: "+dadosAtualizacao.id());
		}
	
		var destino = repository.getReferenceById(dadosAtualizacao.id());
		destino.atualizarDados(dadosAtualizacao);
	
		return new DestinoDTO(destino);
	}

	@Transactional
	public String deletarDestino(Long id) {
		if(!repository.existsById(id)) {
			throw new ObjetoNaoEncontadoException("Destino não encontrado com id: "+id);
		}
		repository.deleteById(id);
		
		return "Destino deletado com sucesso.";
	}

	@Transactional(readOnly = true)
	public List<DestinoDTO> buscarDestinosPeloNome(String nome) {
		var destinos = repository.findByNome(nome);
		if(destinos.isEmpty()) {
			throw new ObjetoNaoEncontadoException("Nenhum destino foi encontrado");
		}
		return destinos.stream().map(DestinoDTO::new).toList();			
	}

	@Transactional(readOnly = true)
	public DestinoDTO detalharDestino(Long id) {
		var destino = repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontadoException("Destino com ID " + id + " não encontrado"));

	    return new DestinoDTO(destino);
	}
	
}
