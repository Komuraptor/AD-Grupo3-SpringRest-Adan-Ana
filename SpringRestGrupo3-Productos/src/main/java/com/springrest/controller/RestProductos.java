package com.springrest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.entity.User;
import com.springrest.model.CategoriaDTO;
import com.springrest.model.ProductoDTO;
import com.springrest.model.ProductoFavoritoDTO;
import com.springrest.service.CategoriaService;
import com.springrest.service.ProductoFavoritoService;
import com.springrest.service.ProductoService;
import com.springrest.service.impl.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class RestProductos {
	
	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;
	
	@Autowired
	@Qualifier("categoriaService")
	private CategoriaService categoriaService;
	
	@Autowired
	@Qualifier("productoFavoritoService")
	private ProductoFavoritoService productoFavoritoService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
//	Recupera todas las categorias
	@GetMapping("/categories")
	public ResponseEntity<?> listCategories() {
		List<CategoriaDTO> categorias = categoriaService.listAllCategorias();
		if(categorias.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(categorias);
		}
	}
	
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
	
//	Recupera todos los productos
	@GetMapping("/products")
	public ResponseEntity<?> listProducts() {
		List<ProductoDTO> productos = productoService.listAllProductos();
		if(productos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(productos);
		}
	}
	
//	Recupera todos los productos de una determinada categoría
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/categories/{id}/product")
	public ResponseEntity<?> listProductsFromCategory(@PathVariable int id) {
		System.out.print("Ha pasado por aqui");
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
	
//	Recupera los productos favoritos de un usuario
	@GetMapping("/productos/favoritos")
	public ResponseEntity<?> getFavoriteProducts() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User principal = userService.findUsuario(auth.getPrincipal().toString());
		List<ProductoFavoritoDTO> productos = productoFavoritoService.listAllProductosFavoritos();
		List<ProductoFavoritoDTO> productosUsuario = new ArrayList<>();
		for(ProductoFavoritoDTO p: productos) {
			if(p.getIdUser() == principal.getId()) {
				productosUsuario.add(p);
			}
		}
		if(productosUsuario.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(productosUsuario);
		}
	}
	
//	Marca producto como favorito
	@PostMapping("/productos/favoritos")
	public ResponseEntity<?> favoriteProduct(@RequestBody ProductoFavoritoDTO productoFavoritoDTO) {
		if(productoFavoritoDTO == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User principal = userService.findUsuario(auth.getPrincipal().toString());
			productoFavoritoDTO.setIdUser((int) principal.getId());
			return ResponseEntity.ok(productoFavoritoService.favoriteProduct(productoFavoritoDTO));
		}
	}

	
//	Desmarca producto como favorito
	@DeleteMapping("/productos/favoritos/{id}")
	public void deleteProductoFavorito(@PathVariable int id) {
		productoFavoritoService.removeProductoFavorito(id);
	}
}
