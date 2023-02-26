package com.springrest.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.entity.ProductoFavorito;

@Repository("productoFavoritoRepository")
public interface ProductoFavoritoRepository extends JpaRepository <ProductoFavorito, Serializable> {
	
}
