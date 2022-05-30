package com.mercantil.andina.pizzeria.api.rest.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.mercantil.andina.pizzeria.api.rest.domain.views.PizzeriaJsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonView(PizzeriaJsonView.ViewPizzeria.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IFPedidoTest
{
	String direccion;
	String email;
	String telefono;
	String horario;
	
	List<IFDetalleTest> detalle;	
}
