package com.springrest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springrest.entity.Producto;
import com.springrest.model.ProductoDTO;
import com.springrest.repository.ProductoRepository;
import com.springrest.service.ProductoService;

@Service("productoService")
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	@Qualifier("productoRepository")
	private ProductoRepository productoRepository;

	@Override
	public ProductoDTO addProducto(ProductoDTO productoDTO) {
		productoRepository.save(transform(productoDTO));
		return productoDTO;
	}

	@Override
	public List<ProductoDTO> listAllProductos() {
		return productoRepository.findAll().stream().map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Producto findProductoById(int id) {
		return productoRepository.findById(id);
	}

	@Override
	public ProductoDTO findByProductoIdModel(int id) {
		return transform(productoRepository.findById(id));
	}

	@Override
	public void removeProducto(int id) {
		productoRepository.deleteById(id);
	}

	@Override
	public Producto transform(ProductoDTO productoDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(productoDTO, Producto.class);
	}

	@Override
	public ProductoDTO transform(Producto producto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(producto, ProductoDTO.class);
	}

}
