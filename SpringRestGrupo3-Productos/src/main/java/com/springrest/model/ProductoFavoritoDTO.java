package com.springrest.model;

public class ProductoFavoritoDTO {
	
	private int id;
	private int idProducto;
	private int idUser;
	
	public ProductoFavoritoDTO() {
		super();
	}

	public ProductoFavoritoDTO(int id, int idProducto, int idUser) {
		super();
		this.id = id;
		this.idProducto = idProducto;
		this.idUser = idUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

}
