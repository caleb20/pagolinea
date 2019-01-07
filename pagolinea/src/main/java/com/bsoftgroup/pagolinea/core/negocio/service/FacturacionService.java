package com.bsoftgroup.pagolinea.core.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsoftgroup.pagolinea.core.interfaces.FacturacionDaoInterface;
import com.bsoftgroup.pagolinea.core.interfaces.FacturacionServiceInterface;
import com.bsoftgroup.pagolinea.core.negocio.bean.Servicio;
import com.bsoftgroup.pagolinea.core.util.AppException;
import com.bsoftgroup.pagolinea.core.util.Transaccion;

@Service
public class FacturacionService implements FacturacionServiceInterface {

	@Autowired
	private FacturacionDaoInterface facturacioDao;

	public FacturacionService() {
	}

	@Override
	public List<Servicio> getServicios(Integer idCliente, Integer idEmpresa) throws AppException {
		return facturacioDao.getServicios(idCliente, idEmpresa);
	}

	@Override
	public Transaccion insertarServicio(Servicio servicio) throws AppException {
		return facturacioDao.insertarServicio(servicio);
	}

	@Override
	public Transaccion pagarServicio(Servicio servicio) throws AppException {
		return facturacioDao.pagarServicio(servicio);
	}

	@Override
	public Transaccion deleteServicio(Servicio servicio) throws AppException {
		return facturacioDao.deleteServicio(servicio);
	}

	@Override
	public Transaccion extornarServicio(Servicio servicio) throws AppException {
		return facturacioDao.extornarServicio(servicio);
	}
}
