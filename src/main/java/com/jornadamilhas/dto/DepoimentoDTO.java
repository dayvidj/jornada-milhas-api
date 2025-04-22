package com.jornadamilhas.dto;

import com.jornadamilhas.model.Depoimento;

import jakarta.validation.constraints.NotBlank;

public record DepoimentoDTO(
		@NotBlank(message = "Campo inválido ou não informado")
		String foto, 
		@NotBlank(message = "Campo inválido ou não informado")
		String texto, 
		@NotBlank(message = "Campo inválido ou não informado")
		String nome) {
	
	public DepoimentoDTO(Depoimento depoimento) {
		this(depoimento.getFoto(), depoimento.getTexto(), depoimento.getNome());
	}
}
