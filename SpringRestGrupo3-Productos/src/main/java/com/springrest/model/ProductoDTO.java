package com.springrest.model;

public class ProductoDTO {
	
	private int id;
	private String nombre;
	private String descripcion;
	private int precio;
	private int idCategoria;
	private int favorito;
	
	public ProductoDTO() {
		super();
	}

	public ProductoDTO(int id, String nombre, String descripcion, int precio, int idCategoria, int favorito) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.idCategoria = idCategoria;
		this.favorito = favorito;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getFavorito() {
		return favorito;
	}

	public void setFavorito(int favorito) {
		this.favorito = favorito;
	}

}
