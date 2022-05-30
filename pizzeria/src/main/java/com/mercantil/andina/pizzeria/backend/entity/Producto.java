package com.mercantil.andina.pizzeria.backend.entity;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Producto extends Auditable<String> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id	
	private String id;	
	
	private String nombre;	
	
	private String descripcionCorta;
	
	private String descripcionLarga;	
	
	private Double precioUnitario;		
}