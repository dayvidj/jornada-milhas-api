package com.jornadamilhas.dto;

import com.jornadamilhas.model.Depoimento;

public record DepoimentoUpdaterDTO(Long id, String foto, String texto, String nome) {

	public DepoimentoUpdaterDTO(Depoimento depoimento) {
		this(depoimento.getId(), depoimento.getFoto(), depoimento.getTexto(), depoimento.getNome());
	}

}
