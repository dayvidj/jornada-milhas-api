package com.jornadamilhas.model;

import java.math.BigDecimal;
import java.util.Optional;

import com.jornadamilhas.dto.DestinoDTO;
import com.jornadamilhas.dto.DestinoUpdaterDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "destinos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Destino {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "foto")
	private String fotoOne;
	@Column(name = "foto_2")
	private String fotoTwo; 
	
	private String nome; 
	private String meta; 
	private String texto; 
	private BigDecimal preco;
	
	public Destino(DestinoDTO destino) {
		this.fotoOne = destino.fotoOne();
		this.fotoTwo = destino.fotoTwo();
		this.nome = destino.nome();
		this.meta = destino.meta();
		this.texto = destino.texto();
		this.preco = destino.preco();
	}

	public void atualizarDados(DestinoUpdaterDTO dadosAtualizacao) {
		Optional.ofNullable(dadosAtualizacao.fotoOne()).ifPresent(fotoOne -> this.fotoOne = fotoOne);
		Optional.ofNullable(dadosAtualizacao.fotoTwo()).ifPresent(fotoTwo -> this.fotoTwo = fotoTwo);
		Optional.ofNullable(dadosAtualizacao.nome()).ifPresent(nome -> this.nome = nome);
		Optional.ofNullable(dadosAtualizacao.meta()).ifPresent(meta -> this.meta = meta);
		Optional.ofNullable(dadosAtualizacao.texto()).ifPresent(texto -> this.texto = texto);
		Optional.ofNullable(dadosAtualizacao.preco()).ifPresent(preco -> this.preco = preco);
	}
	
}
