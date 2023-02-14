package com.springrest.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.entity.Categoria;

@Repository("categoriaRepository")
public interface CategoriaRepository extends JpaRepository <Categoria, Serializable> {

	public abstract Categoria findById(int id);
	
}
