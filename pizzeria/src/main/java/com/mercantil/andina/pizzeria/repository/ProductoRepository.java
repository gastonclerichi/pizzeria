package com.mercantil.andina.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercantil.andina.pizzeria.backend.entity.Producto;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Long>
{	
	Producto findById(String id);
	
	@Modifying
	@Query("DELETE FROM Producto p WHERE p.id=?1")
	void deleteById(String idProducto);
}
