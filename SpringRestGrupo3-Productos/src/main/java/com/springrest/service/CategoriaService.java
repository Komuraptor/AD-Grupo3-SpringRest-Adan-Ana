package com.springrest.service;

import java.util.List;

import com.springrest.entity.Categoria;
import com.springrest.model.CategoriaDTO;

public interface CategoriaService {

public abstract CategoriaDTO addCategoria(CategoriaDTO categoriaDTO);
	
	public abstract List<CategoriaDTO> listAllCategorias();
	
	public abstract Categoria findCategoriaById(int id);
	
	public abstract CategoriaDTO findByCategoriaIdModel(int id);
	
	public abstract void removeCategoria(int id);
	
	public abstract Categoria transform(CategoriaDTO categoriaDTO);
	
	public abstract CategoriaDTO transform(Categoria categoria);
	
}
