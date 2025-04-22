package com.jornadamilhas.dto;

import java.time.LocalDateTime;

public record ErroDTO(
		LocalDateTime timestamp, 
		String erro, 
		String mensagem, 
		String caminho) {
}
