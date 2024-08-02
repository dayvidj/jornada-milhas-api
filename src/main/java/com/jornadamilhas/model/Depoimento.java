package com.jornadamilhas.model;

import java.util.Optional;

import com.jornadamilhas.dto.DepoimentoDTO;
import com.jornadamilhas.dto.DepoimentoUpdaterDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "depoimentos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Depoimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String foto;
	private String texto;
	private String nome;

	public Depoimento(DepoimentoDTO depoimento) {
		this.foto = depoimento.foto();
		this.texto = depoimento.texto();
		this.nome = depoimento.nome();
	}

	public void atualizarDados(DepoimentoUpdaterDTO dados) {
		Optional.ofNullable(dados.foto()).ifPresent(foto -> this.foto = foto);
	    Optional.ofNullable(dados.texto()).ifPresent(texto -> this.texto = texto);
	    Optional.ofNullable(dados.nome()).ifPresent(nome -> this.nome = nome);
	}

}
