package com.mercantil.andina.pizzeria.api.rest.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.mercantil.andina.pizzeria.api.rest.domain.views.PizzeriaJsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonView(PizzeriaJsonView.ViewPizzeria.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IFProducto
{	
	String id;
	String nombre;
	String descripcionCorta;
	String descripcionLarga;
	Double precioUnitario;
}
