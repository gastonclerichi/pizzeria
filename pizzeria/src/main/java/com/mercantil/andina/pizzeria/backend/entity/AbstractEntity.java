package com.mercantil.andina.pizzeria.backend.entity;
import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import javax.persistence.GenerationType;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class AbstractEntity extends Auditable<String> implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private Long id;

	public boolean isNew()
	{
		return id == null;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getId()
	{
		return id;
	}

	@Override
	public int hashCode()
	{
		if (id == null)
		{
			return super.hashCode();
		}

		return 31 + id.hashCode();
	}

	@Override
	public boolean equals(Object other)
	{
		if (id == null)
		{
			// New entities are only equal if the instance if the same
			return super.equals(other);
		}

		if (this == other)
		{
			return true;
		}
		if (!(other instanceof AbstractEntity))
		{
			return false;
		}
		return id.equals(((AbstractEntity) other).id);
	}

}
