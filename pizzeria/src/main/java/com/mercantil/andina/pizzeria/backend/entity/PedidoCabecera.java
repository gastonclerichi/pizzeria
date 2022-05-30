package com.mercantil.andina.pizzeria.backend.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PedidoCabecera extends AbstractEntity
{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String direccion;	
	
	private String email;
	
	private String telefono;
	
	private String horario;
	
	private LocalDate fechaAlta;	
	
	private Double montoTotal;	
	
	private Boolean aplicoDescuento;
	
	private String estado;
}