package com.jornadamilhas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jornadamilhas.model.Destino;

public interface DestinoRepository extends JpaRepository<Destino, Long> {

	List<Destino> findByNome(String nome);

}
