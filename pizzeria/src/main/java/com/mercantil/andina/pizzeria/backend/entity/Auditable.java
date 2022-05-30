package com.mercantil.andina.pizzeria.backend.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.mercantil.andina.pizzeria.util.Funciones;


@MappedSuperclass
public abstract class Auditable<U>
{
	@Transient
	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	@LastModifiedBy
	protected U lastModifiedBy;
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModifiedDate;

	public Auditable()
	{
		super();
	}

	public Auditable(U lastModifiedBy, Date lastModifiedDate)
	{
		super();
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public U getLastModifiedBy()
	{
		return lastModifiedBy;
	}

	public void setLastModifiedBy(U lastModifiedBy)
	{
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate()
	{
		return lastModifiedDate;
	}
	
	public String getLastModifiedDateString()
	{
		if(lastModifiedDate != null)
		{
			return df.format(lastModifiedDate);
		}
		else
		{
			return "";
		}
	}

	public void setLastModifiedDate(Date lastModifiedDate)
	{
		this.lastModifiedDate = lastModifiedDate;
	}
	
		
	@SuppressWarnings("unchecked")
	@PrePersist
	public void prePersist()
	{
		Date fecha = Funciones.getUtilDateNew();
		this.lastModifiedDate = fecha;
		this.lastModifiedBy =(U)"API";
	}

	@SuppressWarnings("unchecked")
	@PreUpdate
	public void preUpdate()
	{
		Date fecha = Funciones.getUtilDateNew();
		this.lastModifiedDate = fecha;
		this.lastModifiedBy = (U)"API";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastModifiedBy == null) ? 0 : lastModifiedBy.hashCode());
		result = prime * result + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auditable other = (Auditable) obj;
		if (lastModifiedBy == null)
		{
			if (other.lastModifiedBy != null)
				return false;
		} else if (!lastModifiedBy.equals(other.lastModifiedBy))
			return false;
		if (lastModifiedDate == null)
		{
			if (other.lastModifiedDate != null)
				return false;
		} else if (!lastModifiedDate.equals(other.lastModifiedDate))
			return false;
		return true;
	}

	public String getAuditoria()
	{
		StringBuilder sb = new StringBuilder();
			
		if(lastModifiedBy!=null && !((String)lastModifiedBy).isEmpty() && lastModifiedDate != null)
		{
			sb.append(lastModifiedBy);
			sb.append(" - ");
			sb.append(df.format(lastModifiedDate));
		}
		else if(lastModifiedDate != null)
		{
			sb.append(df.format(lastModifiedDate));
		}
		return sb.toString();
	}


}