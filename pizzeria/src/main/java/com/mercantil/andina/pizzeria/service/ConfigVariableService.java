package com.mercantil.andina.pizzeria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercantil.andina.pizzeria.backend.entity.ConfigVariable;
import com.mercantil.andina.pizzeria.repository.ConfigVariableRepository;

@Service
public class ConfigVariableService  
{
	@Autowired
	ConfigVariableRepository configVariableRepository;
	
	public ConfigVariable getVariable(String nombre)
	{
		return configVariableRepository.findByNombre(nombre);
	}


}
