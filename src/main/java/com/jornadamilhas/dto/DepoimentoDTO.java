package com.jornadamilhas.dto;

import com.jornadamilhas.model.Depoimento;

import jakarta.validation.constraints.NotBlank;

public record DepoimentoDTO(
		@NotBlank(message = "Campo foto inválido ou não informado")
		String foto, 
		@NotBlank(message = "Campo texto inválido ou não informado")
		String texto, 
		@NotBlank(message = "Campo nome inválido ou não informado")
		String nome) {
	
	public DepoimentoDTO(Depoimento depoimento) {
		this(depoimento.getFoto(), depoimento.getTexto(), depoimento.getNome());
	}
}
