package com.jornadamilhas.dto;

import java.math.BigDecimal;

import com.jornadamilhas.model.Destino;

import jakarta.validation.constraints.NotNull;

public record DestinoUpdaterDTO(
		@NotNull(message = "ID não pode ser nulo")
		Long id, 
		String fotoOne, 
		String fotoTwo, 
		String nome, 
		String meta, 
		String texto,  
		BigDecimal preco) {

	public DestinoUpdaterDTO(Destino destino) {
		this(destino.getId(), destino.getFotoOne(), destino.getFotoTwo(), destino.getNome(), destino.getMeta(), destino.getTexto(), destino.getPreco());
	}
}
