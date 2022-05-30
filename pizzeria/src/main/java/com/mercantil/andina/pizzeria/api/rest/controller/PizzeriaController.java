package com.mercantil.andina.pizzeria.api.rest.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Strings;
import com.mercantil.andina.pizzeria.api.rest.domain.IFPedido;
import com.mercantil.andina.pizzeria.api.rest.domain.IFProducto;
import com.mercantil.andina.pizzeria.api.rest.domain.views.PizzeriaJsonView;
import com.mercantil.andina.pizzeria.service.CalculoService;


@RestController
@RequestMapping("/pizzeria/api/private")
@Validated
public class PizzeriaController
{
	@Autowired
	CalculoService calculoService;	
	
	/***
	 Method: POST
 	 Path: /productos
 	 Request
	 {
		 "id": "89efb206-2aa6-4e21-8a23-5765e3de1f31",
		 "nombre": "Jamón y morrones",
		 "descripcionCorta" : "Pizza de jamón y morrones",
		 "descripcionLarga" : "Mozzarella, jamón, morrones y aceitunas verdes",
		 "precioUnitario" : 550.00
 	 }
	*/
	
	@JsonView(PizzeriaJsonView.ViewPizzeria.class)
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/productos")	
	public void crearProducto(@RequestBody IFProducto producto) 
	{	
		if(producto.getId()==null)
		{			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de producto debe ser not null");
		}	
		if(producto.getNombre()==null || producto.getNombre().trim().isEmpty())
		{			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Nombre del Producto no puede ser vacío o nulo");
		}
		
		try
		{			
			calculoService.save(producto,true,null);			
		}
		catch(Exception e)
		{			
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al intentar guardar el Producto."+e.getMessage(),e);
		}
	}	
	
	/**
	 Method: PUT
 	 Path: /productos/89efb206-2aa6-4e21-8a23-5765e3de1f31
 	 Request
 	 {
		 "nombre": "Jamón y morrones",
		 "descripcionCorta" : "Pizza de jamón y morrones",
		 "descripcionLarga" : "Mozzarella, jamón, morrones y aceitunas verdes",
		 "precioUnitario" : 500.00
 	 }
 	 Response 204
	*/
	
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@PutMapping("/productos/{idProducto}")
	public void modificarProducto(@RequestBody IFProducto producto,@PathVariable String idProducto) 
	{			
		if(producto.getNombre()==null || producto.getNombre().trim().isEmpty())
		{			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Nombre del Producto no puede ser vacío o nulo");
		}
		
		try
		{			
			calculoService.save(producto,false,idProducto);			
		}
		catch(Exception e)
		{			
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al intentar Modificar el Producto."+e.getMessage(),e);
		}
	}
	
	/**
	 Method: GET
 	 Path: /productos/89efb206-2aa6-4e21-8a23-5765e3de1f31
 	 Response 200
 	 {
		 "id": "89efb206-2aa6-4e21-8a23-5765e3de1f31",
		 "nombre": "Jamón y morrones",
		 "descripcionCorta" : "Pizza de jamón y morrones",
		 "descripcionLarga" : "Mozzarella, jamón, morrones y aceitunas verdes",
		 "precioUnitario" : 500.00
 	 }
	*/
	
	@JsonView(PizzeriaJsonView.ViewPizzeria.class)
	@GetMapping("/productos/{idProducto}")
	public IFProducto getProducto(@PathVariable String idProducto)
	{		
		if(Strings.isNullOrEmpty(idProducto))
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Se espera el ID del Producto a mostrar");
		}
		
		try
		{
			return calculoService.getProducto(idProducto);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocurrió un error al intentar obtener el Producto",e);
		}
	}
	
	/**
	 Method: DELETE
 	 Path: /productos/89efb206-2aa6-4e21-8a23-5765e3de1f31
 	 Response 204
	 */
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(path="/productos/{idProducto}", method = RequestMethod.DELETE)
	public void borrarProducto(@PathVariable String idProducto)
	{		
		if(Strings.isNullOrEmpty(idProducto))
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Se espera el ID del Producto a eliminar");
		}	
		try
		{			
			calculoService.delete(idProducto);
			
		}
		catch(Exception e)
		{			
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al intentar eliminar el Producto."+e.getMessage(),e);
		}
	}	
	
	/***
	 Method: POST
	 Path: /pedidos
    Request
	 {
	 	"direccion": "Dorton Road 80",
		"email": "tsayb@opera.com",
		"telefono": "(0351) 48158101",
		"horario": "21:00",
		"detalle": [
					{ "producto": "89efb206-2aa6-4e21-8a23-5765e3de1f31",
					  "cantidad": 1 
					},
					{ "producto": "e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1",
					  "cantidad": 1 
					 }
					]
	 }
 	 Response 201
	 {
		"fecha": "2020-05-24",
		"direccion": "Dorton Road 80",
		"email": "tsayb@opera.com",
		"telefono": "(0351) 48158101",
		"horario": "21:00",
		"detalle": [
					{ "producto": "89efb206-2aa6-4e21-8a23-5765e3de1f31",
					  "nombre": "Jamón y morrones",
					  "cantidad": 1 ,
					  "importe" : 550.00 
					},
					{ "producto": "e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1",
					  "nombre": "Palmitos",
					  "cantidad": 1 ,
					  "importe" : 600.00 
					 }
					] ,
		"total": 1150.00,
		"descuento": false,
		"estado": "PENDIENTE"
	 }
	*/
	
	@JsonView(PizzeriaJsonView.ViewPizzeria.class)
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/pedidos")	
	public IFPedido crearPedido(@RequestBody IFPedido pedido) 
	{	
		if(pedido.getDetalle() == null || pedido.getDetalle().isEmpty())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Se espera al menos un pedido");
		}		
		
		try
		{			
			return calculoService.save(pedido);			
		}
		catch(Exception e)
		{			
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al intentar crear el Pedido."+e.getMessage(),e);
		}
	}	
	
	
	/**
	 Method: GET
	 Path: /pedidos?fecha=2020-05-26
	 Response 200
	 [
	  {
		"fecha": "2020-05-26",
		"direccion": "Dorton Road 80",
		"email": "tsayb@opera.com",
		"telefono": "(0351) 48158101",
		"horario": "21:00",
		"detalle": [
					{ "producto": "89efb206-2aa6-4e21-8a23-5765e3de1f31",
					  "nombre": "Jamón y morrones",
					  "cantidad": 2 ,
					  "importe" : 1100.00 
					},
					{ "producto": "e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1",
					  "nombre": "Palmitos",
					  "cantidad": 2 ,
					  "importe" : 1200.00 
					 }
					] ,
		 "total": 1610.00,
		 "descuento": true
		},
		{
		 "fecha": "2020-05-26",
		 "direccion": "Artisan Hill 47",
		 "email": "ghathawayg@home.pl",
		 "telefono": "(0358) 48997013",
		 "horario": "22:30",
		 "detalle": [
					 { "producto": "89efb206-2aa6-4e21-8a23-5765e3de1f31",
					   "nombre": "Jamón y morrones",
					   "cantidad": 1 ,
					   "importe" : 550.00 
					  }
					 ] ,
		 "total": 550.00,
		 "descuento": false
		}
	]
	 */
	@JsonView(PizzeriaJsonView.ViewPizzeria.class)
	@GetMapping("/pedidos")
	public List<IFPedido> getPedidos(@RequestParam String fecha)
	{		
		if(fecha == null)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Se espera la fecha para mostrar los pedidos");
		}
		
		try
		{
			return calculoService.getPedidos(LocalDate.parse(fecha));
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocurrió un error al intentar obtener los Pedidos",e);
		}
	}
}
