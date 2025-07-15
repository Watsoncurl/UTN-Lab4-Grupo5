package datosImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import datos.MovimientoDao;
import entidades.Movimientos;
import filtros.MovimientosFiltros;

public class MovimientoDaoImpl implements MovimientoDao {

	private Connection conexion;

	public MovimientoDaoImpl() {
		conexion = Conexion.getConexion().getSQLConexion();
	}

	private Movimientos mapearMovimiento(ResultSet rs) throws SQLException {
		Movimientos movimiento = new Movimientos();
		movimiento.setIdMovimiento(rs.getInt("id_movimiento"));
		movimiento.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
		movimiento.setIdCuenta(rs.getInt("id_cuenta"));
		movimiento.setFecha(rs.getTimestamp("fecha")); // Para no perder hora
		movimiento.setConcepto(rs.getString("concepto"));
		movimiento.setImporte(rs.getDouble("importe"));
		return movimiento;
	}
	
	private Movimientos mapearMovimientoConDescripcion(ResultSet rs) throws SQLException {
	    Movimientos movimiento = new Movimientos();
	    movimiento.setIdMovimiento(rs.getInt("id_movimiento"));
	    movimiento.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
	    movimiento.setTipoMovimientoDescripcion(rs.getString("tipo_movimiento_descripcion"));
	    movimiento.setIdCuenta(rs.getInt("id_cuenta"));
	    movimiento.setFecha(rs.getTimestamp("fecha"));
	    movimiento.setConcepto(rs.getString("concepto"));
	    movimiento.setImporte(rs.getDouble("importe"));
	    return movimiento;
	}

	

	@Override
	public List<Movimientos> ListarTodosFiltrados(MovimientosFiltros filtro) {
		List<Movimientos> listaMovimientos = new ArrayList<>();

		String busqueda = filtro.getBusqueda();
		String tipoMovimiento = filtro.getTipoMovimiento();
		String fechaDesde = filtro.getFechaDesde();
		String fechaHasta = filtro.getFechaHasta();

		StringBuilder sql = new StringBuilder(
			"SELECT m.id_movimiento, m.id_tipo_movimiento, tm.descripcion AS tipo_movimiento_descripcion, " +
			"m.id_cuenta, m.fecha, m.concepto, m.importe " +
			"FROM Movimientos m " +
			"INNER JOIN Tipos_Movimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento " +
			"INNER JOIN Cuentas c ON m.id_cuenta = c.id_cuenta " +
			"INNER JOIN tipos_cuenta t ON c.id_tipo_cuenta = t.id_tipo_cuenta " +
			"WHERE 1 = 1 "
		);

		List<Object> parametros = new ArrayList<>();

		if (busqueda != null && !busqueda.trim().isEmpty()) {
			sql.append("AND (c.nro_cuenta LIKE ? OR t.descripcion LIKE ?) ");
			String search = "%" + busqueda.trim() + "%";
			parametros.add(search);
			parametros.add(search);
		}

		if (tipoMovimiento != null && !tipoMovimiento.isEmpty()) {
			if (tipoMovimiento.equalsIgnoreCase("credito")) {
				sql.append("AND m.id_tipo_movimiento = 5 ");
			} else if (tipoMovimiento.equalsIgnoreCase("debito")) {
				sql.append("AND m.id_tipo_movimiento = 4 ");
			}
		}

		if (fechaDesde != null && !fechaDesde.isEmpty()) {
			sql.append("AND DATE(m.fecha) >= ? ");
			parametros.add(Date.valueOf(fechaDesde));
		}

		if (fechaHasta != null && !fechaHasta.isEmpty()) {
			LocalDate hasta = LocalDate.parse(fechaHasta).plusDays(1); // Inclusivo
			sql.append("AND m.fecha < ? ");
			parametros.add(Date.valueOf(hasta));
		}

		sql.append("ORDER BY m.fecha DESC");

		try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
			for (int i = 0; i < parametros.size(); i++) {
				ps.setObject(i + 1, parametros.get(i));
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					listaMovimientos.add(mapearMovimiento(rs));
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al filtrar movimientos: " + e.getMessage());
			e.printStackTrace();
		}

		return listaMovimientos;
	}
	
	public List<Movimientos> ListarTodosConDescripcion() {
	    List<Movimientos> listaMovimientos = new ArrayList<>();

	    String sql = "SELECT m.id_movimiento, m.id_tipo_movimiento, tm.descripcion AS tipo_movimiento_descripcion, " +
	                 "m.id_cuenta, m.fecha, m.concepto, m.importe " +
	                 "FROM Movimientos m " +
	                 "INNER JOIN Tipos_Movimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento " +
	                 "ORDER BY m.fecha DESC";

	    try (PreparedStatement ps = conexion.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            listaMovimientos.add(mapearMovimientoConDescripcion(rs));
	        }

	    } catch (SQLException e) {
	        System.err.println("Error al listar movimientos con descripción: " + e.getMessage());
	    }

	    return listaMovimientos;
	}


	@Override
	public List<Movimientos> ListarTodos() {
		List<Movimientos> listaMovimientos = new ArrayList<>();

		String sql = "SELECT m.id_movimiento, m.id_tipo_movimiento, tm.descripcion AS tipo_movimiento_descripcion, " +
					 "m.id_cuenta, m.fecha, m.concepto, m.importe " +
					 "FROM Movimientos m " +
					 "INNER JOIN Tipos_Movimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento " +
					 "ORDER BY m.fecha DESC";

		try (PreparedStatement ps = conexion.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				listaMovimientos.add(mapearMovimiento(rs));
			}

		} catch (SQLException e) {
			System.err.println("Error al listar todos los movimientos: " + e.getMessage());
		}

		return listaMovimientos;
	}

	@Override
	public List<Movimientos> obtenerPorCuenta(String nroCuenta) {
		List<Movimientos> listaMovimientos = new ArrayList<>();
		String sql = "SELECT m.id_movimiento, m.id_tipo_movimiento, tm.descripcion AS tipo_movimiento_descripcion, " +
					 "m.id_cuenta, m.fecha, m.concepto, m.importe " +
					 "FROM Movimientos m " +
					 "INNER JOIN Tipos_Movimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento " +
					 "INNER JOIN Cuentas c ON m.id_cuenta = c.id_cuenta " +
					 "WHERE c.nro_cuenta = ?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setString(1, nroCuenta);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					listaMovimientos.add(mapearMovimiento(rs));
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener movimientos por número de cuenta: " + e.getMessage());
			e.printStackTrace();
		}
		return listaMovimientos;
	}
}
