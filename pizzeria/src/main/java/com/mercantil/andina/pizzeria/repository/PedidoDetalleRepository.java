package com.mercantil.andina.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercantil.andina.pizzeria.backend.entity.PedidoDetalle;

@Repository
public interface PedidoDetalleRepository  extends JpaRepository<PedidoDetalle, Long>
{	
	List<PedidoDetalle> findByPedidoCabeceraId(Long idPedidoCabecera);
}
