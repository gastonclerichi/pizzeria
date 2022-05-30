package com.mercantil.andina.pizzeria.api.rest.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mercantil.andina.pizzeria.api.rest.domain.views.PizzeriaJsonView;
import com.mercantil.andina.pizzeria.util.LocalDateJacksonDeserializer;
import com.mercantil.andina.pizzeria.util.LocalDateJacksonSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonView(PizzeriaJsonView.ViewPizzeria.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IFPedido
{	
	@JsonDeserialize(using = LocalDateJacksonDeserializer.class)
    @JsonSerialize(using = LocalDateJacksonSerializer.class)
	LocalDate fecha;
	
	String direccion;
	String email;
	String telefono;
	String horario;
	
	List<IFDetalle> detalle;
	
	Double total;
	Boolean descuento;
	String estado;
}
