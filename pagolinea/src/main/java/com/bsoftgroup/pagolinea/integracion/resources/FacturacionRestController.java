package com.bsoftgroup.pagolinea.integracion.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bsoftgroup.pagolinea.core.interfaces.FacturacionServiceInterface;
import com.bsoftgroup.pagolinea.core.negocio.bean.Servicio;
import com.bsoftgroup.pagolinea.core.util.AppException;
import com.bsoftgroup.pagolinea.core.util.Transaccion;
import com.bsoftgroup.pagolinea.integracion.util.Util;
import com.bsoftgroup.pagolinea.integracion.vo.ServicioVO;

@RestController
public class FacturacionRestController {

	@Autowired
	private FacturacionServiceInterface facturacionService;

	public FacturacionRestController() {
	}

	@GetMapping(path = "/facturacion/idCliente/{idCliente}/idEmpresa/{idEmpresa}")
	public List<ServicioVO> getServicios(@PathVariable("idCliente") Integer idCliente,
			@PathVariable("idEmpresa") Integer idEmpresa) {
		List<ServicioVO> serviciosvo = null;
		try {
			List<Servicio> servicios = facturacionService.getServicios(idCliente, idEmpresa);
			serviciosvo = Util.passLstServicioBeanToServicioVo(servicios);
		} catch (AppException e) {
			e.printStackTrace();
		}
		return serviciosvo;
	}

	@PostMapping(path = "/facturacion/registrar")
	public Transaccion insertarServicio(@RequestBody ServicioVO serviciovo) {
		Transaccion tx = new Transaccion();
		try {
			Servicio servicio = Util.passServicioVOToServicioBean(serviciovo);
			tx = facturacionService.insertarServicio(servicio);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tx;
	}

	@PutMapping(path = "/facturacion/pagar")
	public Transaccion pagarServicio(@RequestBody ServicioVO serviciovo) {
		Transaccion tx = new Transaccion();
		try {
			Servicio servicio = Util.passServicioVOToServicioBean(serviciovo);
			tx = facturacionService.pagarServicio(servicio);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tx;
	}

	@DeleteMapping(path = "/facturacion/delete/{id}")
	public Transaccion deleteServicio(@PathVariable("id") Integer idServicio) {
		Transaccion tx = new Transaccion();
		try {
			Servicio servicio = new Servicio();
			servicio.setCodigo(idServicio);
			tx = facturacionService.deleteServicio(servicio);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tx;
	}
}
