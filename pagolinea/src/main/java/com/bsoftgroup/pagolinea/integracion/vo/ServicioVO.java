package com.bsoftgroup.pagolinea.integracion.vo;

public class ServicioVO {

	private Integer codigo;
	private String estado;
	private String monto;
	private ClienteVO cliente;
	private ProductoVO producto;

	public ServicioVO() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public ProductoVO getProducto() {
		return producto;
	}

	public void setProducto(ProductoVO producto) {
		this.producto = producto;
	}

}
