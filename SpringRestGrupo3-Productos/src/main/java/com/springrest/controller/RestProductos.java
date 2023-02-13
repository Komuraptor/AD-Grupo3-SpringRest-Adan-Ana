package com.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.model.ProductoDTO;
import com.springrest.service.ProductoService;

@RestController
@RequestMapping("/api")
public class RestProductos {
	
	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;
	
	@PostMapping("/categories/{id}/product")
	public ResponseEntity<?> newProductWithCategory() {
		return null;
	}
	
	@GetMapping("/categories/{id}/product")
	public ResponseEntity<?> listProductsFromCategory() {
		return null;
	}
	
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
	
	@PutMapping("/products/{id}")
	public ResponseEntity<?> updateProduct() {
		return null;
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable int id) {
		productoService.removeProducto(id);
	}
	
	@DeleteMapping("/categories/{id}")
	public void deleteCategory() {
		
	}
	
	@DeleteMapping("/categories/{id}/products")
	public void deleteProductsFromCategory() {
		
	}
	
	@PostMapping("/categories/{id}")
	public ResponseEntity<?> newCategory() {
		return null;
	}
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<?> updateCategory() {
		return null;
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<?> getCategory() {
		return null;
	}
}
