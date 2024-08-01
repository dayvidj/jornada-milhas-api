package com.jornadamilhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jornadamilhas.model.Depoimento;

public interface DepoimentoRepository extends JpaRepository<Depoimento, Long> {
	
}
