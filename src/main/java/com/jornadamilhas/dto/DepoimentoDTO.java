package com.jornadamilhas.dto;

import com.jornadamilhas.model.Depoimento;

public record DepoimentoDTO(String foto, String texto, String nome) {
	
	public DepoimentoDTO(Depoimento depoimento) {
		this(depoimento.getFoto(), depoimento.getTexto(), depoimento.getNome());
	}
	
}
