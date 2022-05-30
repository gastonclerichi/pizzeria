package com.mercantil.andina.pizzeria.backend.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="config_variable")
public class ConfigVariable extends AbstractEntity implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min = 2, max = 60)
	@Column(unique = true)
	private String nombre;
	
	@NotNull
	@Size(min = 2, max = 255)
	private String valor;
	
	@Size(max = 255)
	private String descripcion;		

	@Column(name="pantalla_nombre")
	private String pantallaNombre;
	
	@Column(name="pantalla_editable")
	private Boolean pantallaEditable; 
	
	public ConfigVariable() 
	{		
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public String getValor() 
	{
		return valor;
	}

	public void setValor(String valor)
	{
		this.valor = valor;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	public String getPantallaNombre() {
		return pantallaNombre;
	}

	public void setPantallaNombre(String pantallaNombre) {
		this.pantallaNombre = pantallaNombre;
	}

	public Boolean getPantallaEditable() {
		return pantallaEditable;
	}

	public void setPantallaEditable(Boolean pantallaEditable) {
		this.pantallaEditable = pantallaEditable;
	}
}