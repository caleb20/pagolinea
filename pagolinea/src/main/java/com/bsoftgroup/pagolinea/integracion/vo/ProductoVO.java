package com.bsoftgroup.pagolinea.integracion.vo;

public class ProductoVO {

	private Integer codigo;
	private String descripcion;
	private String precio;

	public ProductoVO() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

}
