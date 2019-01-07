package com.bsoftgroup.pagolinea.core.negocio.bean;

import java.util.List;

public class Empresa {

	private Integer codigo;
	private String razonsocial;
	private List<Cliente> clientes;

	public Empresa() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getRazonsocial() {
		return razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
