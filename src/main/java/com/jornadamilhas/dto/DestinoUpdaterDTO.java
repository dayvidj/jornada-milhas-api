package com.jornadamilhas.dto;

import java.math.BigDecimal;

import com.jornadamilhas.model.Destino;

import jakarta.validation.constraints.NotNull;

public record DestinoUpdaterDTO(
		@NotNull(message = "ID não pode ser nulo")
		Long id, 
		String foto, 
		String nome, 
		BigDecimal preco) {

	public DestinoUpdaterDTO(Destino destino) {
		this(destino.getId(), destino.getFoto(), destino.getNome(), destino.getPreco());
	}
}
