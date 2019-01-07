package com.bsoftgroup.pagolinea.integracion.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bsoftgroup.pagolinea.core.negocio.bean.Cliente;
import com.bsoftgroup.pagolinea.core.negocio.bean.Producto;
import com.bsoftgroup.pagolinea.core.negocio.bean.Servicio;
import com.bsoftgroup.pagolinea.integracion.vo.ClienteVO;
import com.bsoftgroup.pagolinea.integracion.vo.ProductoVO;
import com.bsoftgroup.pagolinea.integracion.vo.ServicioVO;

public class Util {
	public static List<ServicioVO> passLstServicioBeanToServicioVo(List<Servicio> servicios) {
		List<ServicioVO> serviciosvo = new ArrayList<>();
		Iterator<Servicio> itServicio = servicios.iterator();
		while (itServicio.hasNext()) {
			Servicio serviciotmp = itServicio.next();
			ServicioVO serviciovotmp = new ServicioVO();
			ProductoVO productovotmp = new ProductoVO();
			ClienteVO clientevotmp = new ClienteVO();
			serviciovotmp.setCodigo(serviciotmp.getCodigo());
			serviciovotmp.setEstado(serviciotmp.getEstado());
			serviciovotmp.setMonto(new Integer(serviciotmp.getMonto()).toString());
			clientevotmp.setCodigo(serviciotmp.getCliente().getCodigo());
			clientevotmp.setNombres(serviciotmp.getCliente().getNombres());
			productovotmp.setCodigo(serviciotmp.getProducto().getCodigo());
			productovotmp.setDescripcion(serviciotmp.getProducto().getDescripcion());
			serviciovotmp.setCliente(clientevotmp);
			serviciovotmp.setProducto(productovotmp);
			serviciosvo.add(serviciovotmp);
			serviciovotmp = null;
		}
		return serviciosvo;
	}

	public static Servicio passServicioVOToServicioBean(ServicioVO serviciovo) {
		Servicio servicio = new Servicio();
		servicio.setCliente(new Cliente(serviciovo.getCliente().getCodigo(), serviciovo.getCliente().getNombres()));
		servicio.setProducto(
				new Producto(serviciovo.getProducto().getCodigo(), serviciovo.getProducto().getDescripcion()));
		if (serviciovo.getCodigo() != null)
			servicio.setCodigo(serviciovo.getCodigo());
		servicio.setEstado(serviciovo.getEstado());
		if (serviciovo.getMonto() != null)
			servicio.setMonto(new Integer(serviciovo.getMonto()));
		return servicio;
	}
}
