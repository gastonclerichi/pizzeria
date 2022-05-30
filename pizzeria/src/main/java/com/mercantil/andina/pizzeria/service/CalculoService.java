package com.mercantil.andina.pizzeria.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercantil.andina.pizzeria.api.rest.domain.IFDetalle;
import com.mercantil.andina.pizzeria.api.rest.domain.IFPedido;
import com.mercantil.andina.pizzeria.api.rest.domain.IFProducto;
import com.mercantil.andina.pizzeria.backend.entity.PedidoCabecera;
import com.mercantil.andina.pizzeria.backend.entity.PedidoDetalle;
import com.mercantil.andina.pizzeria.backend.entity.Producto;

@Service
public class CalculoService 
{	
	@Autowired
	PedidoCabeceraService pedidoCabeceraService;
	@Autowired
	PedidoDetalleService pedidoDetalleService;
	@Autowired
	ProductoService productoService;
	
	private String ESTADO_PENDIENTE = "PENDIENTE";		
	
	@Transactional
	public Producto save(IFProducto producto,boolean isNew,String idProducto) throws Exception
	{		
		Producto item;
		if(isNew)
		{
			item = productoService.findById(producto.getId());
			if(item != null && item.getId() != null) 
				throw new Exception("Existe un Producto con ID: " + producto.getId());			
			else
				item = new Producto();
		}
		else
		{			
			item = productoService.findById(idProducto);
			if(item == null) 
				throw new Exception("No existe un Producto con ID: " + idProducto + ", al cual Modificar.");
		}
		
		try
		{		
			item.setId(producto.getId());
			item.setNombre(producto.getNombre());	
			item.setDescripcionCorta(producto.getDescripcionCorta());
			item.setDescripcionLarga(producto.getDescripcionLarga());
			item.setPrecioUnitario(producto.getPrecioUnitario());
			item = productoService.save(item);				
		}
		catch(Exception e)
		{			
			throw new Exception("Ocurrió un error al intentar Guardar/Modificar el Producto <"+e.getMessage()+">");
		}
		
		return item;		
	}	
	
	public IFProducto getProducto(String idProducto) throws Exception
	{	
		Producto item = productoService.findById(idProducto);
		if(item == null) 
			throw new Exception("No existe un Producto con ID: " + idProducto);	
		
		IFProducto iFProducto = new IFProducto();
		iFProducto.setId(idProducto);
		iFProducto.setNombre(item.getNombre());	
		iFProducto.setDescripcionCorta(item.getDescripcionCorta());
		iFProducto.setDescripcionLarga(item.getDescripcionLarga());
		iFProducto.setPrecioUnitario(item.getPrecioUnitario());
		
		return iFProducto;		
	}	
	
	/***
	 * 
	 * @param idProducto
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public void delete(String idProducto) throws Exception
	{
		productoService.deleteById(idProducto);		
	}	
	
	public Producto findById(String id)
	{
		return productoService.findById(id);
	}
	
	@Transactional
	public IFPedido save(IFPedido pedido) throws Exception
	{		
		PedidoCabecera cabecera = new PedidoCabecera();		
		cabecera.setDireccion(pedido.getDireccion());
		cabecera.setEmail(pedido.getEmail());
		cabecera.setTelefono(pedido.getTelefono());
		cabecera.setHorario(pedido.getHorario());
		cabecera.setFechaAlta(LocalDate.now());
		cabecera.setAplicoDescuento(pedido.getDetalle().stream().collect(Collectors.summarizingDouble(d -> d.getCantidad())).getSum() > 3 ? true : false);
		cabecera.setEstado(ESTADO_PENDIENTE);
		
		PedidoDetalle detalle = null;
		List<PedidoDetalle> lstDetalles = new ArrayList<>();
		Producto producto;
		Double montoTotal = 0d;
		for(IFDetalle pedidoDetalle : pedido.getDetalle())
		{
			producto = productoService.findById(pedidoDetalle.getProducto());
			if(producto == null || producto.getId() == null)
				throw new Exception("No existe un Producto con ID: " + pedidoDetalle.getProducto() + ". No se puede crear el Pedido.");			
			
			//Generamos la lista de pedidos detalles que vamos a persistir en la base
			detalle = new PedidoDetalle();
			detalle.setProducto(producto);
			detalle.setPedidoCabecera(cabecera);
			detalle.setCantidad(pedidoDetalle.getCantidad());
			detalle.setPrecioUnitario(producto.getPrecioUnitario() * pedidoDetalle.getCantidad());
			lstDetalles.add(detalle);
			
			//Acumulamos el monto total del pedido
			montoTotal += detalle.getPrecioUnitario();
		}
		
		if(cabecera.getAplicoDescuento())
			montoTotal = montoTotal - (montoTotal * 30 / 100);
		
		cabecera.setMontoTotal(montoTotal);
		
		try
		{			
			cabecera = pedidoCabeceraService.save(cabecera);
			pedidoDetalleService.saveAll(lstDetalles);
		}
		catch(Exception e)
		{			
			throw new Exception("Ocurrió un error al intentar Guardar el Pedido <"+e.getMessage()+">");
		}
		
		return convertToIFPedidoCabecera(cabecera,lstDetalles);		
	}		
	
	public IFPedido convertToIFPedidoCabecera(PedidoCabecera cabecera,List<PedidoDetalle> detalles)
	{
		IFPedido iFPedido = new IFPedido();	
		iFPedido.setFecha(cabecera.getFechaAlta());
		iFPedido.setDireccion(cabecera.getDireccion());
		iFPedido.setEmail(cabecera.getEmail());
		iFPedido.setTelefono(cabecera.getTelefono());
		iFPedido.setHorario(cabecera.getHorario());
		
		List<IFDetalle> iFDetalles = new ArrayList<>();
		IFDetalle iFfDetalle;
		for(PedidoDetalle detalle : detalles)
		{
			iFfDetalle = new IFDetalle();
			iFfDetalle.setProducto(detalle.getProducto().getId());
			iFfDetalle.setNombre(detalle.getProducto().getNombre());
			iFfDetalle.setCantidad(detalle.getCantidad());
			iFfDetalle.setImporte(detalle.getPrecioUnitario());
			iFDetalles.add(iFfDetalle);
		}
		
		iFPedido.setDetalle(iFDetalles);
		iFPedido.setTotal(cabecera.getMontoTotal());
		iFPedido.setDescuento(cabecera.getAplicoDescuento());
		iFPedido.setEstado(cabecera.getEstado());
		
		return iFPedido;
	}
	
	List<PedidoCabecera> findByFecha(LocalDate fecha)
	{
		return pedidoCabeceraService.findByFecha(fecha);
	}
	
	public List<IFPedido> getPedidos(LocalDate fecha) throws Exception
	{	
		List<IFPedido> lstIFPedidos = new ArrayList<>();
		List<PedidoCabecera> lstPedidosCabecera = findByFecha(fecha);
		if(lstPedidosCabecera != null && !lstPedidosCabecera.isEmpty())
		{
			List<PedidoDetalle> lstDetalles;
			for(PedidoCabecera cabecera : lstPedidosCabecera)
			{
				lstDetalles = pedidoDetalleService.findByPedidoCabeceraId(cabecera.getId());
				if(lstDetalles != null && !lstDetalles.isEmpty())
					lstIFPedidos.add(convertToIFPedidoCabecera(cabecera,lstDetalles));
			}
		}	
		
		return lstIFPedidos;		
	}
}
