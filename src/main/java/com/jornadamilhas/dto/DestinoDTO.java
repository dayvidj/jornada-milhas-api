package com.jornadamilhas.dto;

import java.math.BigDecimal;

import com.jornadamilhas.model.Destino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DestinoDTO(
		@NotBlank(message = "Campo inválido ou não informado")
		String fotoOne,
		@NotBlank(message = "Campo inválido ou não informado")
		String fotoTwo,
		@NotBlank(message = "Campo inválido ou não informado")
		String nome,
		@NotBlank(message = "Campo inválido ou não informado")
		String meta,
		@NotBlank(message = "Campo inválido ou não informado")
		String texto,
		@NotNull(message = "O valor não pode ser nulo")
		BigDecimal preco) {

	public DestinoDTO(Destino destino) {
		this(destino.getFotoOne(), destino.getFotoTwo(), destino.getNome(), destino.getMeta(), destino.getTexto(), destino.getPreco());
	}
}
