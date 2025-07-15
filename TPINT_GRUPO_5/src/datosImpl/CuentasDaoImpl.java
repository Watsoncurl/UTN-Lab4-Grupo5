package datosImpl;
import java.util.List;
import java.util.ArrayList;
import entidades.Cuentas;
import filtros.CuentasFiltros;

import java.sql.*;

import datos.CuentasDao;

public class CuentasDaoImpl implements CuentasDao {

	private Connection conexion;

	public CuentasDaoImpl() {

		conexion = Conexion.getConexion().getSQLConexion();

	}

	@Override

	public boolean crearCuenta(Cuentas cuenta) {


		if (existeNroCuenta(cuenta.getNro_cuenta())) {

			System.out.println("El numero de cuenta ya existe. No se puede crear.");

			return false;

		}

		if (existeNroCuenta(cuenta.getNro_cuenta())) {

			System.out.println("Error: El numero de cuenta ya existe. No se puede crear una cuenta duplicada.");

			return false;

		}

		if (existeCbu(cuenta.getCbu())) {

			System.out.println("Error: El CBU ya esta en uso. ");

			return false;

		}

		String queryInsertar = "INSERT INTO cuentas (id_cliente, id_tipo_cuenta, nro_cuenta, cbu, saldo, estado) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement statementInsertar = conexion.prepareStatement(queryInsertar)) {

			statementInsertar.setInt(1, cuenta.getId_cliente());

			statementInsertar.setString(2, cuenta.getTipo_cuenta());

			statementInsertar.setString(3, cuenta.getNro_cuenta());

			statementInsertar.setString(4, cuenta.getCbu());

			statementInsertar.setDouble(5, cuenta.getSaldo());

			statementInsertar.setBoolean(6, cuenta.isEstado());

			int filasAfectadas = statementInsertar.executeUpdate();

			conexion.commit();

			return filasAfectadas > 0;

		} catch (SQLException e) {

			e.printStackTrace();

			try {


				conexion.rollback();

			} catch (SQLException ex) {

				ex.printStackTrace();

			}

			return false;

		}

	}

	@Override

	public boolean existeNroCuenta(String nroCuenta) {

		String sqlConsulta = "SELECT 1 FROM cuentas WHERE nro_cuenta = ? LIMIT 1";

		try (PreparedStatement consulta = conexion.prepareStatement(sqlConsulta)) {

			consulta.setString(1, nroCuenta);

			try (ResultSet existeNroCuenta = consulta.executeQuery()) {

				return existeNroCuenta.next();

			}

		} catch (SQLException errorSQL) {

			errorSQL.printStackTrace();

		}

		return false;

	}

	@Override

	public boolean existeCbu(String cbu) {

		String sqlConsulta = "SELECT 1 FROM cuentas WHERE cbu = ? LIMIT 1";

		try (PreparedStatement sentenciaConsulta = conexion.prepareStatement(sqlConsulta)) {

			sentenciaConsulta.setString(1, cbu);

			try (ResultSet resultado = sentenciaConsulta.executeQuery()) {

				return resultado.next(); 
			}

		} catch (SQLException errorSQL) {

			errorSQL.printStackTrace();

		}

		return false;

	}

	@Override

	public int contarCuentasActivasPorCliente(int idCliente) {

		String sqlConsulta = "SELECT COUNT(*) AS cantidad FROM cuentas WHERE id_cliente = ? AND estado = true";

		try (PreparedStatement sentenciaConsulta = conexion.prepareStatement(sqlConsulta)) {

			sentenciaConsulta.setInt(1, idCliente);

			try (ResultSet resultado = sentenciaConsulta.executeQuery()) {

				if (resultado.next()) {

					return resultado.getInt("cantidad");

				}

			}

		} catch (SQLException errorSQL) {

			errorSQL.printStackTrace();

		}

		return 0;

	}
	
	@Override
    public Cuentas obtenerPorNroCuenta(String nroCuenta) {

        if (nroCuenta == null || nroCuenta.trim().isEmpty()) {
            return null;
        }
        
        String sql = "SELECT c.id_cuenta, c.id_cliente, c.nro_cuenta, c.cbu, c.saldo, " +
                    "c.estado, c.fecha_creacion, c.id_tipo_cuenta, " +
                    "tc.descripcion AS tipo_cuenta, " +
                    "CONCAT(cl.nombre, ' ', cl.apellido) AS cliente " +
                    "FROM cuentas c " +
                    "INNER JOIN tipos_cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta " +
                    "INNER JOIN clientes cl ON c.id_cliente = cl.id_cliente " +
                    "WHERE c.nro_cuenta = ?";
        
        try (
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setString(1, nroCuenta.trim());
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCuenta(rs);
                    
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cuenta por numero: " + e.getMessage());
            e.printStackTrace();
            
        }
        
        return null;
        
    }

    @Override
    public boolean actualizarPorNroCuenta(Cuentas cuenta) {
        String sql = "UPDATE cuentas SET id_cliente=?, id_tipo_cuenta=?, cbu=?, estado=? "
                   + "WHERE nro_cuenta=?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cuenta.getId_cliente());
            ps.setInt(2, Integer.parseInt(cuenta.getTipo_cuenta()));
            ps.setString(3, cuenta.getCbu());
            ps.setBoolean(4, cuenta.isEstado());
            ps.setString(5, cuenta.getNro_cuenta());

            int filasAfectadas = ps.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cuenta: " + e.getMessage());
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }
	
	@Override
	public List<Cuentas> listarPaginadas(int inicio, int cantidad) {
	    List<Cuentas> lista = new ArrayList<>();
	    String sql = "SELECT c.nro_cuenta, CONCAT(cl.nombre, ' ', cl.apellido) AS cliente, " +
	                 "t.descripcion AS tipo_cuenta, c.saldo, c.estado " +
	                 "FROM cuentas c " +
	                 "INNER JOIN clientes cl ON cl.id_cliente = c.id_cliente " +
	                 "INNER JOIN tipos_cuenta t ON t.id_tipo_cuenta = c.id_tipo_cuenta " +
	                 "LIMIT ? OFFSET ?";

	    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
	        ps.setInt(1, cantidad);
	        ps.setInt(2, inicio);

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Cuentas cuenta = new Cuentas();
	            cuenta.setNro_cuenta(rs.getString("nro_cuenta"));
	            cuenta.setCliente(rs.getString("cliente"));
	            cuenta.setTipo_cuenta(rs.getString("tipo_cuenta"));
	            cuenta.setSaldo(rs.getDouble("saldo"));
	            cuenta.setEstado(rs.getBoolean("estado"));
	            lista.add(cuenta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}

	@Override
	public int contarTotalCuentas() {
	    String sql = "SELECT COUNT(*) FROM cuentas";
	    try (PreparedStatement ps = conexion.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) {
	            return rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	@Override
	public boolean eliminarCuenta(String nroCuenta) {
	    String sql = "UPDATE cuentas SET estado = 0 WHERE nro_cuenta = ?";
	    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
	        stmt.setString(1, nroCuenta);
	        int filasAfectadas = stmt.executeUpdate();
	        conexion.commit();
	        return filasAfectadas > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            conexion.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	    return false;
	}

	private Cuentas mapearCuenta(ResultSet rs) throws SQLException {
        Cuentas cuenta = new Cuentas();
        cuenta.setId_cuenta(rs.getInt("id_cuenta"));
        cuenta.setId_cliente(rs.getInt("id_cliente"));
        cuenta.setNro_cuenta(rs.getString("nro_cuenta"));
        cuenta.setCbu(rs.getString("cbu"));
        cuenta.setSaldo(rs.getDouble("saldo"));
        cuenta.setEstado(rs.getBoolean("estado"));
        cuenta.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
        cuenta.setTipo_cuenta(rs.getString("tipo_cuenta"));
        cuenta.setCliente(rs.getString("cliente"));
        return cuenta;
    }
	
	
	@Override
	public List<Cuentas> filtrar(CuentasFiltros filtro, int inicio, int cantidad) {
	    List<Cuentas> lista = new ArrayList<>();
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT c.nro_cuenta, CONCAT(cl.nombre, ' ', cl.apellido) AS cliente, ")
	       .append("t.descripcion AS tipo_cuenta, c.saldo, c.estado ")
	       .append("FROM cuentas c ")
	       .append("INNER JOIN clientes cl ON cl.id_cliente = c.id_cliente ")
	       .append("INNER JOIN tipos_cuenta t ON t.id_tipo_cuenta = c.id_tipo_cuenta ")
	       .append("WHERE 1=1 ");

	    if (filtro.getBusqueda() != null && !filtro.getBusqueda().trim().isEmpty()) {
	        sql.append("AND (c.nro_cuenta LIKE ? OR c.cbu LIKE ? OR CONCAT(cl.nombre, ' ', cl.apellido) LIKE ?) ");
	    }
	    if (filtro.getTipoCuenta() != null && !filtro.getTipoCuenta().trim().isEmpty()) {
	        sql.append("AND t.descripcion = ? ");
	    }
	    if (filtro.getIdCliente() != null) {
	        sql.append("AND c.id_cliente = ? ");
	    }
	    Boolean estadoBool = filtro.getEstado();
	    if (estadoBool != null) {
	        sql.append("AND c.estado = ? ");
	    }

	    sql.append("LIMIT ? OFFSET ?");

	    try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
	        int paramIndex = 1;

	        if (filtro.getBusqueda() != null && !filtro.getBusqueda().trim().isEmpty()) {
	            String likeParam = "%" + filtro.getBusqueda() + "%";
	            ps.setString(paramIndex++, likeParam);
	            ps.setString(paramIndex++, likeParam);
	            ps.setString(paramIndex++, likeParam);
	        }
	        if (filtro.getTipoCuenta() != null && !filtro.getTipoCuenta().trim().isEmpty()) {
	            ps.setString(paramIndex++, filtro.getTipoCuenta());
	        }
	        if (filtro.getIdCliente() != null) {
	            ps.setInt(paramIndex++, filtro.getIdCliente());
	        }
	        if (estadoBool != null) {
	            ps.setBoolean(paramIndex++, estadoBool);
	        }

	        ps.setInt(paramIndex++, cantidad);
	        ps.setInt(paramIndex++, inicio);

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Cuentas cuenta = new Cuentas();
	            cuenta.setNro_cuenta(rs.getString("nro_cuenta"));
	            cuenta.setCliente(rs.getString("cliente"));
	            cuenta.setTipo_cuenta(rs.getString("tipo_cuenta"));
	            cuenta.setSaldo(rs.getDouble("saldo"));
	            cuenta.setEstado(rs.getBoolean("estado"));
	            lista.add(cuenta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}

	@Override
	public int contarFiltradas(CuentasFiltros filtro) {
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT COUNT(*) AS total ")
	       .append("FROM cuentas c ")
	       .append("INNER JOIN clientes cl ON cl.id_cliente = c.id_cliente ")
	       .append("INNER JOIN tipos_cuenta t ON t.id_tipo_cuenta = c.id_tipo_cuenta ")
	       .append("WHERE 1=1 ");

	    if (filtro.getBusqueda() != null && !filtro.getBusqueda().trim().isEmpty()) {
	        sql.append("AND (c.nro_cuenta LIKE ? OR c.cbu LIKE ? OR CONCAT(cl.nombre, ' ', cl.apellido) LIKE ?) ");
	    }
	    if (filtro.getTipoCuenta() != null && !filtro.getTipoCuenta().trim().isEmpty()) {
	        sql.append("AND t.descripcion = ? ");
	    }
	    if (filtro.getIdCliente() != null) {
	        sql.append("AND c.id_cliente = ? ");
	    }
	    
	    Boolean estadoBool = filtro.getEstado();
	    if (estadoBool != null) {
	        sql.append("AND c.estado = ? ");
	    }

	    try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
	        int paramIndex = 1;

	        if (filtro.getBusqueda() != null && !filtro.getBusqueda().trim().isEmpty()) {
	            String likeParam = "%" + filtro.getBusqueda() + "%";
	            ps.setString(paramIndex++, likeParam);
	            ps.setString(paramIndex++, likeParam);
	            ps.setString(paramIndex++, likeParam);
	        }
	        if (filtro.getTipoCuenta() != null && !filtro.getTipoCuenta().trim().isEmpty()) {
	            ps.setString(paramIndex++, filtro.getTipoCuenta());
	        }
	        if (filtro.getIdCliente() != null) {
	            ps.setInt(paramIndex++, filtro.getIdCliente());
	        }
	        if (estadoBool != null) {
	            ps.setBoolean(paramIndex++, estadoBool);
	        }

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("total");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
	


	@Override
	public boolean activarCuentaPorNroCuenta(String nroCuenta) {
	    String query = "UPDATE cuentas SET estado = 1 WHERE nro_cuenta = ?";
	    try (PreparedStatement stmt = conexion.prepareStatement(query)) {
	        stmt.setString(1, nroCuenta);
	        int filasAfectadas = stmt.executeUpdate();
	        conexion.commit();
	        return filasAfectadas > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            conexion.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return false;
	    }
	}
	 @Override
	    public List<Cuentas> listarTodas() { 
	        List<Cuentas> lista = new ArrayList<>();
	        String sql = "SELECT c.nro_cuenta, CONCAT(cl.nombre, ' ', cl.apellido) AS cliente, " +
	                     "t.descripcion AS tipo_cuenta, c.saldo, c.estado, c.id_cuenta " + 
	                     "FROM cuentas c " +
	                     "INNER JOIN clientes cl ON cl.id_cliente = c.id_cliente " +
	                     "INNER JOIN tipos_cuenta t ON t.id_tipo_cuenta = c.id_tipo_cuenta " +
	                     "WHERE c.estado = TRUE"; 

	        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Cuentas cuenta = new Cuentas();
	                cuenta.setNro_cuenta(rs.getString("nro_cuenta"));
	                cuenta.setCliente(rs.getString("cliente"));
	                cuenta.setTipo_cuenta(rs.getString("tipo_cuenta"));
	                cuenta.setSaldo(rs.getDouble("saldo"));
	                cuenta.setEstado(rs.getBoolean("estado"));
	                cuenta.setId_cuenta(rs.getInt("id_cuenta"));
	                lista.add(cuenta);
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al listar todas las cuentas: " + e.getMessage());
	            e.printStackTrace();
	        }
	        return lista;
	    }
	 
	 @Override
	 public List<Cuentas> listarPorTipo(int idTipoCuenta) {
	     List<Cuentas> lista = new ArrayList<>();
	     String sql = "SELECT c.nro_cuenta, CONCAT(cl.nombre, ' ', cl.apellido) AS cliente, " +
	                  "t.descripcion AS tipo_cuenta, c.saldo, c.estado, c.id_cuenta " +
	                  "FROM cuentas c " +
	                  "INNER JOIN clientes cl ON cl.id_cliente = c.id_cliente " +
	                  "INNER JOIN tipos_cuenta t ON t.id_tipo_cuenta = c.id_tipo_cuenta " +
	                  "WHERE c.estado = TRUE AND c.id_tipo_cuenta = ?";

	     try (PreparedStatement ps = conexion.prepareStatement(sql)) {
	         ps.setInt(1, idTipoCuenta); 
	         ResultSet rs = ps.executeQuery();
	         while (rs.next()) {
	             Cuentas cuenta = new Cuentas();
	             cuenta.setNro_cuenta(rs.getString("nro_cuenta"));
	             cuenta.setCliente(rs.getString("cliente"));
	             cuenta.setTipo_cuenta(rs.getString("tipo_cuenta"));
	             cuenta.setSaldo(rs.getDouble("saldo"));
	             cuenta.setEstado(rs.getBoolean("estado"));
	             cuenta.setId_cuenta(rs.getInt("id_cuenta"));
	             lista.add(cuenta);
	         }
	     } catch (SQLException e) {
	         System.err.println("Error al listar cuentas por tipo: " + e.getMessage());
	         e.printStackTrace();
	     }
	     return lista;
	 }
	 
	 @Override
	 public List<Cuentas> obtenerCuentasPorIdCliente(int idCliente) {
	     List<Cuentas> lista = new ArrayList<>();
	     try (Connection conn = Conexion.getConexion().getSQLConexion();
	          PreparedStatement stmt = conn.prepareStatement(
	              "SELECT c.id_cuenta, c.nro_cuenta, c.cbu, c.saldo, tc.descripcion AS tipoCuenta " +
	              "FROM Cuentas c " +
	              "JOIN Tipos_Cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta " +
	              "WHERE c.id_cliente = ? AND c.estado = true")) {

	         stmt.setInt(1, idCliente);
	         ResultSet rs = stmt.executeQuery();

	         while (rs.next()) {
	             Cuentas cuenta = new Cuentas();
	             cuenta.setId_cuenta(rs.getInt("id_cuenta"));
	             cuenta.setNro_cuenta(rs.getString("nro_cuenta"));
	             cuenta.setCbu(rs.getString("cbu"));
	             cuenta.setSaldo(rs.getDouble("saldo"));
	             cuenta.setTipo_cuenta(rs.getString("tipoCuenta"));
	             lista.add(cuenta);
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     return lista;
	 }
	 @Override
	 public Cuentas obtenerCuentaPorCBU(String cbu) {
	     Cuentas cuenta = null;
	     String sql = "SELECT c.id_cuenta, c.id_cliente, c.nro_cuenta, c.cbu, c.saldo, " +
	                  "c.estado, c.fecha_creacion, c.id_tipo_cuenta, " +
	                  "tc.descripcion AS tipo_cuenta, " +
	                  "CONCAT(cl.nombre, ' ', cl.apellido) AS cliente " +  
	                  "FROM cuentas c " +
	                  "INNER JOIN tipos_cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta " +
	                  "INNER JOIN clientes cl ON c.id_cliente = cl.id_cliente " +
	                  "WHERE c.cbu = ?";
	     try (PreparedStatement ps = conexion.prepareStatement(sql)) {
	         ps.setString(1, cbu);
	         try (ResultSet rs = ps.executeQuery()) {
	             if (rs.next()) {
	                 cuenta = mapearCuenta2(rs);  
	             }
	         }
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
	     return cuenta;
	 }
	 private Cuentas mapearCuenta2(ResultSet rs) throws SQLException {
		    Cuentas cuenta = new Cuentas();
		    cuenta.setId_cuenta(rs.getInt("id_cuenta"));
		    cuenta.setId_cliente(rs.getInt("id_cliente"));
		    cuenta.setNro_cuenta(rs.getString("nro_cuenta"));
		    cuenta.setCbu(rs.getString("cbu"));
		    cuenta.setSaldo(rs.getDouble("saldo"));
		    cuenta.setEstado(rs.getBoolean("estado"));
		    cuenta.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
		    cuenta.setTipo_cuenta(rs.getString("tipo_cuenta"));
		    cuenta.setCliente(rs.getString("cliente"));  
		    return cuenta;
		}
	 
	 


}