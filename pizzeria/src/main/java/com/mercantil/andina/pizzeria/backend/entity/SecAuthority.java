package com.mercantil.andina.pizzeria.backend.entity;
import org.springframework.security.core.GrantedAuthority;

public class SecAuthority implements GrantedAuthority
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;

	public SecAuthority(String authority)
	{
		this.authority = authority;
	}

	@Override
	public String getAuthority()
	{
		return authority;
	}

	@Override
	public int hashCode()
	{
		return authority.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (!(obj instanceof SecAuthority))
			return false;
		return ((SecAuthority) obj).getAuthority().equals(authority);
	}
}
