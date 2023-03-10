package com.springrest.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.entity.Producto;

@Repository("productoRepository")
public interface ProductoRepository extends JpaRepository <Producto, Serializable> {

	public abstract Producto findById(int id);
	
	public abstract List<Producto> findByCategoriaId(int id);
	
}
