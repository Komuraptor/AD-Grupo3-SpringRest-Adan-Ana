package com.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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
	@PostMapping("/categories/{id}/product")
	public ResponseEntity<?> newProductWithCategory() {
		return null;
	}
	
//	Recupera todos los productos de una determinada categoría
	@GetMapping("/categories/{id}/product")
	public ResponseEntity<?> listProductsFromCategory() {
		return null;
	}
	
//	Recupera el producto correspondiente a ese id
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
	@PutMapping("/products")
	public ResponseEntity<?> updateProduct(@RequestBody ProductoDTO producto) {
		return ResponseEntity.ok(productoService.addProducto(producto));
	}
	
//	Elimina el producto correspondiente a ese id
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable int id) {
		productoService.removeProducto(id);
	}
	
//	Elimina una categoría y todos sus productos (categoría correspondiente a ese id)
	@DeleteMapping("/categories/{id}")
	public void deleteCategory(@PathVariable int id) {
		categoriaService.removeCategoria(id);
	}
	
//	Elimina todos los productos de una determinada categoría
	@DeleteMapping("/categories/{id}/products")
	public void deleteProductsFromCategory() {
		
	}
	
//	Crea una nueva categoría
	@PostMapping("/categories")
	public ResponseEntity<?> newCategory(@RequestBody CategoriaDTO categoria) {
		return ResponseEntity.ok(categoriaService.addCategoria(categoria));
	}
	
//	Actualiza una categoría
	@PutMapping("/categories")
	public ResponseEntity<?> updateCategory(@RequestBody CategoriaDTO categoria) {
		return ResponseEntity.ok(categoriaService.addCategoria(categoria));
	}
	
//	Recupera la categoría correspondiente a ese id
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
}
