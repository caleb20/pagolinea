package com.bsoftgroup.pagolinea.core.util;

public class Transaccion {
	private String codigo;
	private String descripcion;
	
	private String codigobackend;
	
	public Transaccion() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigobackend() {
		return codigobackend;
	}

	public void setCodigobackend(String codigobackend) {
		this.codigobackend = codigobackend;
	}
}
