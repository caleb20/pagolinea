package com.bsoftgroup.pagolinea.core.accesodato.repositorio;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bsoftgroup.pagolinea.core.interfaces.FacturacionDaoInterface;
import com.bsoftgroup.pagolinea.core.negocio.bean.Cliente;
import com.bsoftgroup.pagolinea.core.negocio.bean.Producto;
import com.bsoftgroup.pagolinea.core.negocio.bean.Servicio;
import com.bsoftgroup.pagolinea.core.util.AppException;
import com.bsoftgroup.pagolinea.core.util.Conexion;
import com.bsoftgroup.pagolinea.core.util.Transaccion;

@Repository
public class FacturacionDao implements FacturacionDaoInterface {

	public FacturacionDao() {
	}

	@Override
	public List<Servicio> getServicios(Integer idCliente, Integer idEmpresa) throws AppException {
		List<Servicio> servicios = new ArrayList<>();
		Conexion con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = new Conexion();
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
		String SQL = "SELECT " + " c.codigo as cliente, " + " c.nombres as nombres,"
				+ " p.codigo as producto, " + " p.descripcion as descripcion," + " p.precio as precio,"
				+ " cp.codigo as recibo," + " cp.monto as deuda," + " cp.estado as estado "
				+ " FROM esq_pwj_060119.TBL_CLIENTE c " + " INNER JOIN esq_pwj_060119.TBL_CLIENTE_PRODUCTO cp on c.codigo = cp.cliente "
				+ " INNER JOIN esq_pwj_060119.TBL_PRODUCTO p on p.codigo = cp.producto" + " and c.codigo = ? and c.empresa = ? ";
		try {
			con.getConexion().setAutoCommit(false);
			pstmt = con.getConexion().prepareStatement(SQL);
			pstmt.setInt(1, idCliente);
			pstmt.setInt(2, idEmpresa);
			rs = pstmt.executeQuery();
			con.getConexion().commit();
			while (rs.next()) {
				Servicio servicio = new Servicio();
				servicio.setCodigo(rs.getInt("recibo"));
				servicio.setEstado(rs.getString("estado"));
				servicio.setCliente(new Cliente(rs.getInt("cliente"), rs.getString("nombres")));
				servicio.setProducto(new Producto(rs.getInt("producto"), rs.getString("descripcion")));
				servicio.setMonto(rs.getInt("deuda"));
				servicios.add(servicio);
				servicio = null;
			}
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		} finally {
			try {
				con.closeResources(con.getConexion(), rs, null, null, pstmt);
			} catch (Exception e) {// TODO Auto-generated catch block e.printStackTrace();
			}
			return servicios;
		}
	}

	@Override
	public Transaccion insertarServicio(Servicio servicio) throws AppException {
		Transaccion tx = new Transaccion();
		CallableStatement cstmt = null;
		String resultado;
		String metodo = "";
		Conexion con = null;
		String SQL = "{? = call esq_pwj_060119.fn_registrar_deuda(?,?,?)}";
		try {
			con = new Conexion();
			con.getConexion().setAutoCommit(false);
			cstmt = con.getConexion().prepareCall(SQL);
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			cstmt.setInt(2, servicio.getCliente().getCodigo());
			cstmt.setInt(3, servicio.getProducto().getCodigo());
			cstmt.setInt(4, servicio.getMonto());
			cstmt.execute();
			resultado = cstmt.getString(1);
			if (resultado.equals("0000")) {
				tx.setCodigo(resultado);
				tx.setDescripcion("Proceso Conforme");
			} else {
				tx.setCodigo(resultado);
				tx.setDescripcion("Error al procesar la transaccion");
			}
			con.getConexion().commit();
		} catch (SQLException sqle) {
			throw new AppException(sqle.getMessage());
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, cstmt, null);
			} catch (Exception e) {
				throw new AppException(e.getMessage());
			}
		}
		return tx;
	}

	@Override
	public Transaccion pagarServicio(Servicio servicio) throws AppException {
		Transaccion tx = new Transaccion();
		PreparedStatement pstmt = null;
		Conexion con = null;
		String SQL = "UPDATE esq_pwj_060119.TBL_CLIENTE_PRODUCTO SET estado = '1' where codigo=?andcliente=? and producto=?";
		try {
			con = new Conexion();
			con.getConexion().setAutoCommit(false);
			pstmt = con.getConexion().prepareStatement(SQL);
			pstmt.setInt(1, servicio.getCodigo());
			pstmt.setInt(2, servicio.getCliente().getCodigo());
			pstmt.setInt(3, servicio.getProducto().getCodigo());
			pstmt.executeUpdate();
			tx.setCodigo("0000");
			tx.setDescripcion("Proceso Conforme");
			con.getConexion().commit();
		} catch (SQLException sqle) {
			throw new AppException(sqle.getMessage());
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, null, pstmt);
			} catch (Exception e) {
				throw new AppException(e.getMessage());
			}
		}
		return tx;
	}

	@Override
	public Transaccion deleteServicio(Servicio servicio) throws AppException {
		Transaccion tx = new Transaccion();
		PreparedStatement pstmt = null;
		Conexion con = null;
		String SQL = "DELETE from esq_pwj_060119.TBL_CLIENTE_PRODUCTO where codigo=?";
		try {
			con = new Conexion();
			con.getConexion().setAutoCommit(false);
			pstmt = con.getConexion().prepareStatement(SQL);
			pstmt.setInt(1, servicio.getCodigo());
			pstmt.setInt(2, servicio.getCliente().getCodigo());
			pstmt.setInt(3, servicio.getProducto().getCodigo());
			pstmt.execute();
			tx.setCodigo("0000");
			tx.setDescripcion("Proceso Conforme");
			con.getConexion().commit();
		} catch (SQLException sqle) {
			throw new AppException(sqle.getMessage());
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, null, pstmt);
			} catch (Exception e) {
				throw new AppException(e.getMessage());
			}
		}
		return tx;
	}

	@Override
	public Transaccion extornarServicio(Servicio servicio) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

}
