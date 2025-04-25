package com.jornadamilhas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jornadamilhas.dto.DestinoDTO;
import com.jornadamilhas.dto.DestinoResponseDTO;
import com.jornadamilhas.exception.ObjetoNaoEncontadoException;
import com.jornadamilhas.model.Destino;
import com.jornadamilhas.repository.DestinoRepository;

@Service
public class DestinoService {

	@Autowired
	private DestinoRepository repository;
	
	@Transactional
	public DestinoResponseDTO salvarDestino(DestinoDTO dados) {
		var destino = repository.save(new Destino(dados));
		return new DestinoResponseDTO(destino);
	}

	@Transactional(readOnly = true)
	public Page<DestinoResponseDTO> exibirDestinos(Pageable pageable) {
		return repository.findAll(pageable).map(DestinoResponseDTO::new);
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
	
	@Transactional
	public DestinoDTO atualizarDestino(DestinoResponseDTO dadosAtualizacao) {
		var destino = repository.findById(dadosAtualizacao.id())
				.orElseThrow(() -> new ObjetoNaoEncontadoException("Destino não encontrado"));
		
		destino.atualizarDados(dadosAtualizacao);
		return new DestinoDTO(destino);
	}

	@Transactional
	public String deletarDestino(Long id) {
		repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontadoException("Destino com ID " + id + " não encontrado"));
		
		repository.deleteById(id);
		return "Destino deletado com sucesso.";
	}

}
