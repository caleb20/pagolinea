package com.bsoftgroup.pagolinea.integracion.vo;

public class ClienteVO {

	private Integer codigo;
	private String nombres;
	private String empresa;

	public ClienteVO() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}
