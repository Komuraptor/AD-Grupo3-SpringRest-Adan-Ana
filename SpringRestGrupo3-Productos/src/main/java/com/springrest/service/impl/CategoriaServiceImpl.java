package com.springrest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springrest.entity.Categoria;
import com.springrest.model.CategoriaDTO;
import com.springrest.repository.CategoriaRepository;
import com.springrest.service.CategoriaService;

@Service("categoriaService")
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	@Qualifier("categoriaRepository")
	private CategoriaRepository categoriaRepository;

	@Override
	public CategoriaDTO addCategoria(CategoriaDTO categoriaDTO) {
		categoriaRepository.save(transform(categoriaDTO));
		return categoriaDTO;
	}

	@Override
	public List<CategoriaDTO> listAllCategorias() {
		return categoriaRepository.findAll().stream().map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Categoria findCategoriaById(int id) {
		return categoriaRepository.findById(id);
	}

	@Override
	public CategoriaDTO findByCategoriaIdModel(int id) {
		return transform(categoriaRepository.findById(id));
	}

	@Override
	public void removeCategoria(int id) {
		categoriaRepository.deleteById(id);
	}

	@Override
	public Categoria transform(CategoriaDTO categoriaDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(categoriaDTO, Categoria.class);
	}

	@Override
	public CategoriaDTO transform(Categoria categoria) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(categoria, CategoriaDTO.class);
	}

}
