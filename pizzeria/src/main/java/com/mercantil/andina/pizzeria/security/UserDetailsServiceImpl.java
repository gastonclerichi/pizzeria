package com.mercantil.andina.pizzeria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mercantil.andina.pizzeria.backend.entity.SecUsuario;
import com.mercantil.andina.pizzeria.service.SecUsuarioService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	private final SecUsuarioService usuarioService;
	
	@Autowired
	public UserDetailsServiceImpl(SecUsuarioService usuarioService)
	{
		this.usuarioService = usuarioService;
	}

	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException
	{
		SecUsuario usuario = usuarioService.findByUsuario(nombreUsuario);
		
		if (null == usuario)
		{
			throw new UsernameNotFoundException("Ningún usuario presente con nombre de usuario: " + nombreUsuario);
		} 
		else
		{
			if(usuario.getActivo())
			{
				return new org.springframework.security.core.userdetails.User(usuario.getUsuario(), usuario.getPassword(),usuario.getAuthorities());
			}
			else
			{
				throw new UsernameNotFoundException("El usuario se encuentra inactivo: " + nombreUsuario);
			}
		}
	}
}