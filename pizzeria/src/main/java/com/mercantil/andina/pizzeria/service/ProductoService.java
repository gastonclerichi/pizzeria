package com.mercantil.andina.pizzeria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercantil.andina.pizzeria.backend.entity.Producto;
import com.mercantil.andina.pizzeria.repository.ProductoRepository;

@Service
public class ProductoService 
{
	@Autowired
	ProductoRepository repository;	
	
	@Transactional
	public Producto save(Producto producto)
	{		
		return repository.save(producto);				
	}		
	
	/***
	 * 
	 * @param idProducto
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public void deleteById(String idProducto) throws Exception
	{
		repository.deleteById(idProducto);		
	}	
	
	public Producto findById(String id)
	{
		return repository.findById(id);
	}
}
