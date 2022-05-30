package com.mercantil.andina.pizzeria.backend.entity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="pedido_detalle")
public class PedidoDetalle extends AbstractEntity
{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;	
	
	@ManyToOne
	@JoinColumn(name="id_pedido_cabecera")
	private PedidoCabecera pedidoCabecera;	
	
	private Integer cantidad;
	
	private Double precioUnitario;	
}