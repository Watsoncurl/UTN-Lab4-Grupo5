package datosImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entidades.Cuentas;
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

        if (existeCbu(cuenta.getCbu())) {
            System.out.println("Error: El CBU ya esta en uso.");
            return false;
        }

        String queryInsertar = "INSERT INTO cuentas (id_cliente, id_tipo_cuenta, nro_cuenta, cbu, saldo, estado) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement statementInsertar = conexion.prepareStatement(queryInsertar, Statement.RETURN_GENERATED_KEYS)) {
            statementInsertar.setInt(1, cuenta.getId_cliente());
            statementInsertar.setInt(2, Integer.parseInt(cuenta.getTipo_cuenta())); // Asume que tipo_cuenta es el ID
            statementInsertar.setString(3, cuenta.getNro_cuenta());
            statementInsertar.setString(4, cuenta.getCbu());
            statementInsertar.setDouble(5, cuenta.getSaldo());
            statementInsertar.setBoolean(6, cuenta.isEstado());

            int filasAfectadas = statementInsertar.executeUpdate();
            
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = statementInsertar.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cuenta.setId_cuenta(generatedKeys.getInt(1));
                    }
                }
                conexion.commit();
                return true;
            }
            return false;
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
        
        try (Connection con = Conexion.getConexion().getSQLConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nroCuenta.trim());
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCuenta(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cuenta por número: " + e.getMessage());
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
/*
    @Override
    public List<Cuentas> listarPorCliente(int idCliente) {
        List<Cuentas> cuentas = new ArrayList<>();
        String sql = "SELECT c.id_cuenta, c.id_cliente, c.nro_cuenta, c.cbu, c.saldo, c.estado, "
                   + "c.fecha_creacion, c.id_tipo_cuenta, tc.descripcion AS tipo_cuenta, "
                   + "CONCAT(cl.nombre, ' ', cl.apellido) AS cliente "
                   + "FROM cuentas c "
                   + "JOIN tipos_cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta "
                   + "JOIN clientes cl ON c.id_cliente = cl.id_cliente "
                   + "WHERE c.id_cliente = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cuentas.add(mapearCuenta(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cuentas por cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return cuentas;
    }

    @Override
    public List<Cuentas> listarTodas() {
        List<Cuentas> cuentas = new ArrayList<>();
        String sql = "SELECT c.id_cuenta, c.id_cliente, c.nro_cuenta, c.cbu, c.saldo, c.estado, "
                   + "c.fecha_creacion, c.id_tipo_cuenta, tc.descripcion AS tipo_cuenta, "
                   + "CONCAT(cl.nombre, ' ', cl.apellido) AS cliente "
                   + "FROM cuentas c "
                   + "JOIN tipos_cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta "
                   + "JOIN clientes cl ON c.id_cliente = cl.id_cliente";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cuentas.add(mapearCuenta(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar todas las cuentas: " + e.getMessage());
            e.printStackTrace();
        }
        return cuentas;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "UPDATE cuentas SET estado = false WHERE id_cuenta = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cuenta: " + e.getMessage());
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }
*/
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
}