package com.springrest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.model.CategoriaDTO;
import com.springrest.model.ProductoDTO;
import com.springrest.service.CategoriaService;
import com.springrest.service.ProductoService;

@RestController
@RequestMapping("/api")
public class RestProductos {
	
	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;
	
	@Autowired
	@Qualifier("categoriaService")
	private CategoriaService categoriaService;
	
//	Crea un nuevo producto para una categoría
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/categories/{id}/product")
	public ResponseEntity<?> newProductWithCategory(@PathVariable int id, @RequestBody ProductoDTO producto) {
		if(producto == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			producto.setIdCategoria(id);
			return ResponseEntity.ok(productoService.addProducto(producto));
		}
	}
	
//	Recupera todos los productos de una determinada categoría
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/categories/{id}/product")
	public ResponseEntity<?> listProductsFromCategory(@PathVariable int id) {
		List<ProductoDTO> productos = productoService.listAllProductos();
		List<ProductoDTO> productosCategoria = new ArrayList<>();
		for(ProductoDTO p: productos) {
			if(p.getIdCategoria() == id) {
				productosCategoria.add(p);
			}
		}
		if(productosCategoria.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(productosCategoria);
		}
	}
	
//	Ver todos los productos que existen en el sistema
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/products")
	public ResponseEntity<?> listProducts() {
		if(productoService.listAllProductos().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(productoService.listAllProductos());
		}
	}
	
//	Recupera el producto correspondiente a ese id
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProduct(@PathVariable int id) {
		ProductoDTO producto = productoService.findByProductoIdModel(id);
		if(producto==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(producto);
		}
	}
	
//	Actualiza el producto correspondiente a ese id
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/products")
	public ResponseEntity<?> updateProduct(@RequestBody ProductoDTO producto) {
		if(producto == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(productoService.addProducto(producto));
		}
	}
	
//	Elimina el producto correspondiente a ese id
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable int id) {
		productoService.removeProducto(id);
	}
	
//	Elimina una categoría y todos sus productos (categoría correspondiente a ese id)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/categories/{id}")
	public void deleteCategory(@PathVariable int id) {
		categoriaService.removeCategoria(id);
	}
	
//	Elimina todos los productos de una determinada categoría
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/categories/{id}/products")
	public void deleteProductsFromCategory(@PathVariable int id) {
		List<ProductoDTO> productos = productoService.listAllProductos();
		for(ProductoDTO p: productos) {
			if(p.getIdCategoria() == id) {
				productoService.removeProducto(p.getId());
			}
		}
	}
	
//	Crea una nueva categoría
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/categories")
	public ResponseEntity<?> newCategory(@RequestBody CategoriaDTO categoria) {
		if(categoria == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(categoriaService.addCategoria(categoria));
		}
	}
	
//	Actualiza una categoría
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/categories")
	public ResponseEntity<?> updateCategory(@RequestBody CategoriaDTO categoria) {
		if(categoria == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(categoriaService.addCategoria(categoria));
		}
	}
	
//	Recupera la categoría correspondiente a ese id
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/categories/{id}")
	public ResponseEntity<?> getCategory(@PathVariable int id) {
		CategoriaDTO categoria = categoriaService.findByCategoriaIdModel(id);
		if(categoria==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(categoria);
		}
	}
	
//	Recupera todos los productos de una determinada categoría
//	@PreAuthorize("hasRole('ROLE_USER')")
//	@GetMapping("/products/{id}/favorites")
//	public ResponseEntity<?> listFavoriteProducts(@PathVariable int id) {
//		return null;
//	}
}
