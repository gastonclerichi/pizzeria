package com.mercantil.andina.pizzeria.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercantil.andina.pizzeria.backend.entity.PedidoCabecera;
import com.mercantil.andina.pizzeria.repository.PedidoCabeceraRepository;

@Service
public class PedidoCabeceraService 
{
	@Autowired
	PedidoCabeceraRepository repository;	
	
	public PedidoCabecera save(PedidoCabecera item)
	{
		return repository.save(item);
	}

	public List<PedidoCabecera> findByFecha(LocalDate fecha) 
	{		
		return repository.findByFecha(fecha);
	}
}
