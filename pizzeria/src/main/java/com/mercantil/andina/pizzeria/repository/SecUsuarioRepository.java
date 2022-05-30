package com.mercantil.andina.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercantil.andina.pizzeria.backend.entity.SecUsuario;

public interface SecUsuarioRepository extends JpaRepository<SecUsuario, Long>
{
	SecUsuario findByUsuarioAndPassword(String nombreUsuario,String password);
	
	SecUsuario findByUsuario(String nombreUsuario);
}
