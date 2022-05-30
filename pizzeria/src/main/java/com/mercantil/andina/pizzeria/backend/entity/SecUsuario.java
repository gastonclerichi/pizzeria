package com.mercantil.andina.pizzeria.backend.entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;

import com.mercantil.andina.pizzeria.backend.LocalDateTimeJpaConverter;
import com.mercantil.andina.pizzeria.util.Funciones;

@Entity
@Table(name = "sec_usuario")
public class SecUsuario extends AbstractEntity implements Serializable, UserDetails
{
	private static final long serialVersionUID = 1L;	
	
	@Size(min = 3, max = 50)
	private String apellido;

	@Size(max = 50)
	@Column(unique = true)
	private String email;

	@Size(min = 3, max = 50)
	private String nombre;

	@NotNull
	@Size(min = 8, max = 255)
	private String password;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(unique = true)
	private String usuario;	
	
	private boolean bloqueado = false;


	// bi-directional many-to-one association to SecRol
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_rol")
	private SecRol secRolUsuario;
	
	@Transient
//	@NotNull
	@Size(min = 8, max = 20)
	private String passwordConfirmada;

	@Column(name="reset_token")
	private String resetToken;
	
	@Column(name="expiry_date_token")
	@Convert(converter = LocalDateTimeJpaConverter.class)
	private LocalDateTime expiryDateToken;	

	@Column(name="activo")
	private Boolean activo;

	@NotNull
	@Column(name="inicio_sesion_codigo")
	private Integer inicioSesionCodigo;

	
	public SecUsuario()
	{
	}


	public String getApellido()
	{
		return this.apellido;
	}


	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}


	public String getEmail()
	{
		return this.email;
	}


	public void setEmail(String email)
	{
		this.email = email;
	}


	public String getNombre()
	{
		return this.nombre;
	}


	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}


	public String getPassword()
	{
		return this.password;
	}


	public void setPassword(String password)
	{
		this.password = password;
	}

	
	public String getUsuario()
	{
		return this.usuario;
	}

	
	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}


	public SecRol getSecRolUsuario()
	{
		return secRolUsuario;
	}

	
	public void setSecRolUsuario(SecRol secRolUsuario)
	{
		this.secRolUsuario = secRolUsuario;
	}


	public boolean isBloqueado()
	{
		return bloqueado;
	}


	public void setBloqueado(boolean bloqueado)
	{
		this.bloqueado = bloqueado;
	}

	@Override
	
	public String getUsername()
	{
		// TODO Auto-generated method stub
		return this.usuario;
	}
	
	public String getPasswordConfirmada()
	{
		return passwordConfirmada;
	}


	public void setPasswordConfirmada(String passwordConfirmada)
	{
		this.passwordConfirmada = passwordConfirmada;
	}

	public String getResetToken()
	{
		return resetToken;
	}

	public void setResetToken(String resetToken)
	{
		this.resetToken = resetToken;
	}

	public LocalDateTime getExpiryDateToken()
	{
		return expiryDateToken;
	}

	public void setExpiryDateToken(LocalDateTime expiryDateToken)
	{
		this.expiryDateToken = expiryDateToken;
	}

	@Override
	
	public boolean isAccountNonExpired()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void setExpiryDate(int minutes)
	{
		this.expiryDateToken = Funciones.getLocalDateTimeNow().plusMinutes(minutes);
	}

	public boolean isExpired()
	{
		return Funciones.getLocalDateTimeNow().isAfter(this.expiryDateToken);
	}

	@Override
	
	public boolean isAccountNonLocked()
	{		
		return false;
	}

	@Override

	public boolean isCredentialsNonExpired()
	{		
		return false;
	}

	@Override

	public boolean isEnabled()
	{		
		return false;
	}		

	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Integer getInicioSesionCodigo() {
		return inicioSesionCodigo;
	}

	public void setInicioSesionCodigo(Integer inicioSesionCodigo) {
		this.inicioSesionCodigo = inicioSesionCodigo;
	}


	@Override
	@Transient
	public Collection<SecAuthority> getAuthorities()
	{
		Set<SecAuthority> authorities = new HashSet<SecAuthority>();
		SecAuthority autoRol = new SecAuthority("ROLE_" + secRolUsuario.getNombre());
		authorities.add(autoRol);
		return authorities;
	}	
}