package com.jornadamilhas.model;

import java.math.BigDecimal;
import java.util.Optional;

import com.jornadamilhas.dto.DestinoDTO;
import com.jornadamilhas.dto.DestinoUpdaterDTO;

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
	private String foto;
	private String nome;
	private BigDecimal preco;
	
	public Destino(DestinoDTO destino) {
		this.foto = destino.foto();
		this.nome = destino.nome();
		this.preco = destino.preco();
	}

	public void atualizarDados(DestinoUpdaterDTO dadosAtualizacao) {
		Optional.ofNullable(dadosAtualizacao.foto()).ifPresent(foto -> this.foto = foto);
		Optional.ofNullable(dadosAtualizacao.nome()).ifPresent(nome -> this.nome = nome);
		Optional.ofNullable(dadosAtualizacao.preco()).ifPresent(preco -> this.preco = preco);
	}
	
}
