package com.jornadamilhas.dto;

import java.math.BigDecimal;

import com.jornadamilhas.model.Destino;

public record DestinoDTO(
		String fotoOne, 
		String fotoTwo, 
		String nome, 
		String meta, 
		String texto, 
		BigDecimal preco) {

	public DestinoDTO(Destino destino) {
		this(destino.getFotoOne(), destino.getFotoTwo(), destino.getNome(), destino.getMeta(), destino.getTexto(), destino.getPreco());
	}
}
