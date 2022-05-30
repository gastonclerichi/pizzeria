package com.mercantil.andina.pizzeria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercantil.andina.pizzeria.backend.entity.SecUsuario;
import com.mercantil.andina.pizzeria.repository.SecUsuarioRepository;

@Service
public class SecUsuarioService 
{
	@Autowired
    SecUsuarioRepository usuarioRepository;

	public SecUsuario findByUsuarioAndPassword(String nombre,String pass)
	{
		return usuarioRepository.findByUsuarioAndPassword(nombre, pass);
	}
	
	public SecUsuario findByUsuario(String nombre)
	{
		return usuarioRepository.findByUsuario(nombre);
	}

}
