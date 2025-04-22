package com.jornadamilhas.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jornadamilhas.dto.ErroDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> tratarErroValidacao(MethodArgumentNotValidException ex) {
		Map<String, Object> erros = new HashMap<>();
		
		for(FieldError erro: ex.getBindingResult().getFieldErrors()) {
			erros.put(erro.getField(), erro.getDefaultMessage());
		}
	
		Map<String, Object> body = new LinkedHashMap<>();
		
		body.put("timestamp", LocalDate.now());
		body.put("erro", "Erro de validação");
		body.put("mensagens", erros);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);	
	}

	@ExceptionHandler(ObjetoNaoEncontadoException.class)
	public ResponseEntity<ErroDTO> tratarObjetoNaoEncontrado(ObjetoNaoEncontadoException ex, HttpServletRequest req) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErroDTO(
						LocalDateTime.now(), 
						"Recurso não encontrado", 
						ex.getMessage(), 
						req.getRequestURI())
				);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroDTO> tratarErroGenerico(Exception ex, HttpServletRequest req) {
		ex.printStackTrace(); // Log para depuração

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErroDTO(
						LocalDateTime.now(),
						"Erro interno",
						"Ocorreu um erro inesperado", req.getRequestURI())
				);
	}

}
