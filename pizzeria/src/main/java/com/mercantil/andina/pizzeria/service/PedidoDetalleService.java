package com.mercantil.andina.pizzeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercantil.andina.pizzeria.backend.entity.PedidoDetalle;
import com.mercantil.andina.pizzeria.repository.PedidoDetalleRepository;

@Service
public class PedidoDetalleService 
{
	@Autowired
	PedidoDetalleRepository repository;		
	
	
	@Transactional
	public void saveAll(List<PedidoDetalle> lstPedidosDetalle) throws Exception
	{		
		if(lstPedidosDetalle != null && !lstPedidosDetalle.isEmpty())
		{		
			try
			{			
				repository.saveAll(lstPedidosDetalle);				
			}
			catch(Exception e)
			{			
				throw new Exception("Ocurrió un error al intentar Guardar el Pedido Detalle <"+e.getMessage()+">");
			}
		}			
	}		
	
	List<PedidoDetalle> findByPedidoCabeceraId(Long idPedidoCabecera)
	{
		return repository.findByPedidoCabeceraId(idPedidoCabecera);
	}
}
