package com.mercantil.andina.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercantil.andina.pizzeria.backend.entity.ConfigVariable;



public interface ConfigVariableRepository extends JpaRepository<ConfigVariable, Long> 
{
	ConfigVariable findByNombre(String variable);	
}
