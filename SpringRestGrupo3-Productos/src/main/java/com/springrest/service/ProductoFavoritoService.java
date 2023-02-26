package com.springrest.service;

import java.util.List;

import com.springrest.entity.ProductoFavorito;
import com.springrest.model.ProductoFavoritoDTO;

public interface ProductoFavoritoService {

	public abstract ProductoFavoritoDTO favoriteProduct(ProductoFavoritoDTO productoFavoritoDTO);
	
	public abstract List<ProductoFavoritoDTO> listAllProductosFavoritos();
	
	public abstract void removeProductoFavorito(int id);
	
	public abstract ProductoFavorito transform(ProductoFavoritoDTO productoFavoritoDTO);
	
	public abstract ProductoFavoritoDTO transform(ProductoFavorito productoFavorito);
	
}
