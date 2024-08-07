package com.jornadamilhas.dto;

import com.jornadamilhas.model.Depoimento;

import jakarta.validation.constraints.NotNull;

public record DepoimentoUpdaterDTO(
		@NotNull(message = "ID não pode ser nulo")
		Long id, 
		String foto, 
		String texto, 
		String nome) {

	public DepoimentoUpdaterDTO(Depoimento depoimento) {
		this(depoimento.getId(), depoimento.getFoto(), depoimento.getTexto(), depoimento.getNome());
	}

}
