package com.mercantil.andina.pizzeria.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercantil.andina.pizzeria.backend.entity.PedidoCabecera;

@Repository
public interface PedidoCabeceraRepository  extends JpaRepository<PedidoCabecera, Long>
{	
	@Query("Select p FROM PedidoCabecera p WHERE p.fechaAlta = ?1")
	List<PedidoCabecera> findByFecha(LocalDate fecha);
}
