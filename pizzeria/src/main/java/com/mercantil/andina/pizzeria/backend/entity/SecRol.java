package com.mercantil.andina.pizzeria.backend.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The persistent class for the sec_rol database table.
 * 
 */
@Entity
@Table(name = "sec_rol")
public class SecRol extends AbstractEntity implements Serializable
{
	private static final long serialVersionUID = 1L;	
	
	@NotNull
	@Size(min = 3, max = 50)
	@Column(unique = true)
	private String nombre;
	
	@Size(max=100)
	private String descripcion;

	private boolean bloqueado = false;


	// bi-directional many-to-one association to SecUsuario
	@OneToMany(mappedBy = "secRolUsuario")
	private List<SecUsuario> secUsuarios;	

	public SecRol()
	{
		this.secUsuarios = new ArrayList<SecUsuario>();
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public boolean isBloqueado()
	{
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado)
	{
		this.bloqueado = bloqueado;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	

	

	public List<SecUsuario> getSecUsuarios()
	{
		return this.secUsuarios;
	}

	public void setSecUsuarios(List<SecUsuario> secUsuarios)
	{
		this.secUsuarios = secUsuarios;
	}

	public SecUsuario addSecUsuario(SecUsuario secUsuario)
	{
		getSecUsuarios().add(secUsuario);
		secUsuario.setSecRolUsuario(this);

		return secUsuario;
	}

	public SecUsuario removeSecUsuario(SecUsuario secUsuario)
	{
		getSecUsuarios().remove(secUsuario);
		secUsuario.setSecRolUsuario(null);

		return secUsuario;
	}	

	@Override
	public String toString()
	{
		return getNombre();
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SecRol other = (SecRol) obj;
		if (nombre == null)
		{
			if (other.nombre != null)
				return false;
		}
		else if (!nombre.equals(other.nombre) )
			return false;
		
		return true;
	}
}