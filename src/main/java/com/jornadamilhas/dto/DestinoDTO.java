package com.jornadamilhas.dto;

import java.math.BigDecimal;

public record DestinoDTO(
		String fotoOne, 
		String fotoTwo, 
		String nome, 
		String meta, 
		String texto, 
		BigDecimal preco) {
}
