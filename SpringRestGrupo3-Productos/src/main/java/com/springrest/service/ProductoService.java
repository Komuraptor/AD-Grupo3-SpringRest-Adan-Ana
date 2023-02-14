package com.springrest.service;

import java.util.List;

import com.springrest.entity.Producto;
import com.springrest.model.ProductoDTO;

public interface ProductoService {

	public abstract ProductoDTO addProducto(ProductoDTO productoDTO);
	
	public abstract List<ProductoDTO> listAllProductos();
	
	public abstract Producto findProductoById(int id);
	
	public abstract ProductoDTO findByProductoIdModel(int id);
	
	public abstract void removeProducto(int id);
	
	public abstract Producto transform(ProductoDTO productoDTO);
	
	public abstract ProductoDTO transform(Producto producto);
	
}
