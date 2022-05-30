package com.mercantil.andina.pizzeria.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mercantil.andina.pizzeria.backend.entity.SecRol;


public interface SecRolRepository extends JpaRepository<SecRol, Long> 
{
	SecRol findByNombre(String nombre);
	
	SecRol findByNombreLikeIgnoreCase (String nombre);
	
	Page<SecRol> findByNombreLikeIgnoreCase(String nombre, Pageable page);

	int countByNombreLikeIgnoreCase(String nombre);
	
}