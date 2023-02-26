package com.springrest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springrest.entity.ProductoFavorito;
import com.springrest.model.ProductoFavoritoDTO;
import com.springrest.repository.ProductoFavoritoRepository;
import com.springrest.service.ProductoFavoritoService;

@Service("productoFavoritoService")
public class ProductoFavoritoServiceImpl implements ProductoFavoritoService {
	
	@Autowired
	@Qualifier("productoFavoritoRepository")
	private ProductoFavoritoRepository productoFavoritoRepository;

	@Override
	public ProductoFavoritoDTO favoriteProduct(ProductoFavoritoDTO productoFavoritoDTO) {
		productoFavoritoRepository.save(transform(productoFavoritoDTO));
		return productoFavoritoDTO;
	}

	@Override
	public List<ProductoFavoritoDTO> listAllProductosFavoritos() {
		return productoFavoritoRepository.findAll().stream().map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public void removeProductoFavorito(int id) {
		productoFavoritoRepository.deleteById(id);
	}

	@Override
	public ProductoFavorito transform(ProductoFavoritoDTO productoFavoritoDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(productoFavoritoDTO, ProductoFavorito.class);
	}

	@Override
	public ProductoFavoritoDTO transform(ProductoFavorito productoFavorito) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(productoFavorito, ProductoFavoritoDTO.class);
	}

}
