package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.PrestamosDao;
import entidades.Cliente;
import entidades.Cuentas;
import entidades.Prestamos;

public class PrestamosDaoImpl implements PrestamosDao {

    public PrestamosDaoImpl() {}

    public boolean solicitarPrestamo(Prestamos prestamo) {
        boolean exito = false;
        Connection conexion = null;
        PreparedStatement pstmt = null;

        try {
            Conexion connect = Conexion.getConexion();
            conexion = connect.getSQLConexion();
            conexion.setAutoCommit(false);

            String query = "INSERT INTO Prestamos (id_cliente, id_cuenta,fecha_alta, importe, plazo_meses, cuota_mensual, estado, cuotas_pendientes, cuotas_pagas) VALUES (?, ?, CURDATE(), ?, ?, ?, ?, ?, ?)";            
            pstmt = conexion.prepareStatement(query);
            pstmt.setInt(1, prestamo.getIdCliente());
            pstmt.setInt(2, prestamo.getIdCuenta());
            pstmt.setDouble(3, prestamo.getImporte());
            pstmt.setInt(4, prestamo.getPlazoMeses());
            pstmt.setDouble(5, prestamo.getCuotaMensual());
            pstmt.setString(6, prestamo.getEstado());
            pstmt.setInt(7, prestamo.getCuotasPendientes());
            pstmt.setInt(8, prestamo.getCuotasPagas());
            

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                conexion.commit();
                exito = true;
            } else {
                conexion.rollback();
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar prestamo: " + e.getMessage());
            e.printStackTrace();
            try {
                if (conexion != null) conexion.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: " + ex.getMessage());
                ex.printStackTrace();
            }
        } finally {
           try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
           if (conexion != null) {
                Conexion.getConexion().cerrarConexion(conexion);
            }
        }

        return exito;
    }
    
    @Override
    public List<Prestamos> obtenerPrestamosPaginados(String busqueda, String estado, int pagina, int cantidadPorPagina) {
        List<Prestamos> lista = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Conexion connect = Conexion.getConexion();
            conexion = connect.getSQLConexion();
            StringBuilder query = new StringBuilder();
            query.append("SELECT p.*, c.nombre, c.apellido, c.dni, cta.nro_cuenta AS nro_cuenta, cta.saldo AS saldo FROM Prestamos p ")
                .append("JOIN Clientes c ON p.id_cliente = c.id_cliente ")
                 .append("JOIN Cuentas cta ON p.id_cuenta = cta.id_cuenta ")
                .append("WHERE 1=1 ");
            if (busqueda != null && !busqueda.isEmpty()) {
                query.append("AND (c.dni LIKE ? OR c.nombre LIKE ? OR c.apellido LIKE ?) ");
            }
            if (estado != null && !estado.isEmpty()) {
                query.append("AND p.estado = ? ");
            }
            query.append("ORDER BY p.fecha_alta DESC LIMIT ? OFFSET ?");
            pstmt = conexion.prepareStatement(query.toString());
            int paramIndex = 1;
            if (busqueda != null && !busqueda.isEmpty()) {
                String wildcard = "%" + busqueda + "%";
                pstmt.setString(paramIndex++, wildcard);
                pstmt.setString(paramIndex++, wildcard);
                pstmt.setString(paramIndex++, wildcard);
            }
            if (estado != null && !estado.isEmpty()) {
                pstmt.setString(paramIndex++, estado);
            }
            pstmt.setInt(paramIndex++, cantidadPorPagina);
            pstmt.setInt(paramIndex, (pagina - 1) * cantidadPorPagina);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Prestamos p = new Prestamos();
                p.setIdPrestamo(rs.getInt("id_prestamo"));
                p.setIdCliente(rs.getInt("id_cliente"));
                p.setIdCuenta(rs.getInt("id_cuenta"));
                p.setFechaAlta(rs.getString("fecha_alta"));
                p.setImporte(rs.getDouble("importe"));
                p.setPlazoMeses(rs.getInt("plazo_meses"));
                p.setCuotaMensual(rs.getDouble("cuota_mensual"));
                p.setEstado(rs.getString("estado"));
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDni(rs.getString("dni"));
                p.setCliente(cliente);
                Cuentas cuenta = new Cuentas();
                cuenta.setSaldo(rs.getDouble("saldo"));
                p.setSaldo(cuenta.getSaldo());
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conexion != null) Conexion.getConexion().cerrarConexion(conexion);
        }
        return lista;
    }
    
    @Override
    public int contarPrestamos(String busqueda, String estado) {
        int total = 0;
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Conexion connect = Conexion.getConexion();
            conexion = connect.getSQLConexion();

            StringBuilder query = new StringBuilder();
            query.append("SELECT COUNT(*) AS total ")
                 .append("FROM Prestamos p ")
                 .append("JOIN Clientes c ON p.id_cliente = c.id_cliente ")
                 .append("WHERE 1=1 ");

            if (busqueda != null && !busqueda.isEmpty()) {
                query.append("AND (c.dni LIKE ? OR c.nombre LIKE ? OR c.apellido LIKE ?) ");
            }

            if (estado != null && !estado.isEmpty()) {
                query.append("AND p.estado = ? ");
            }

            pstmt = conexion.prepareStatement(query.toString());

            int paramIndex = 1;
            if (busqueda != null && !busqueda.isEmpty()) {
                String wildcard = "%" + busqueda + "%";
                pstmt.setString(paramIndex++, wildcard);
                pstmt.setString(paramIndex++, wildcard);
                pstmt.setString(paramIndex++, wildcard);
            }

            if (estado != null && !estado.isEmpty()) {
                pstmt.setString(paramIndex++, estado);
            }

            rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conexion != null) Conexion.getConexion().cerrarConexion(conexion);
        }

        return total;
    }

    @Override
    public Prestamos obtenerPrestamoPorId(int id) {
        Prestamos p = null;
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexion = Conexion.getConexion().getSQLConexion();
            String query = "SELECT p.*, c.nombre, c.apellido, c.dni FROM prestamos p "
                         + "JOIN clientes c ON p.id_cliente = c.id_cliente WHERE p.id_prestamo = ?";
            stmt = conexion.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cli = new Cliente();
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido(rs.getString("apellido"));
                cli.setDni(rs.getString("dni"));

                p = new Prestamos();
                p.setIdPrestamo(rs.getInt("id_prestamo"));
                p.setIdCliente(rs.getInt("id_cliente"));
                p.setIdCuenta(rs.getInt("id_cuenta"));
                p.setFechaAlta(rs.getString("fecha_alta"));
                p.setImporte(rs.getDouble("importe"));
                p.setPlazoMeses(rs.getInt("plazo_meses"));
                p.setCuotaMensual(rs.getDouble("cuota_mensual"));
                p.setEstado(rs.getString("estado"));
                p.setCliente(cli);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            if (conexion != null) Conexion.getConexion().cerrarConexion(conexion);
        }

        return p;
    }

    @Override
    public boolean actualizarEstadoPrestamo(int idPrestamo, String nuevoEstado) {
        boolean exito = false;
        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            conexion = Conexion.getConexion().getSQLConexion();
            String query = "UPDATE prestamos SET estado = ? WHERE id_prestamo = ?";
            stmt = conexion.prepareStatement(query);
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idPrestamo);

            int filas = stmt.executeUpdate();
            System.out.println("ðŸ› ï¸� Filas actualizadas: " + filas);

            if (filas > 0) {
                conexion.commit();
                exito = true;
            } else {
                conexion.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conexion != null) conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (conexion != null) Conexion.getConexion().cerrarConexion(conexion);
        }

        return exito;
    }
    
    @Override
    public List<Prestamos> obtenerPrestamosPaginadosPorCliente(int idCliente, String busqueda, String estado, int pagina, int cantidadPorPagina) {
        List<Prestamos> lista = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Conexion connect = Conexion.getConexion();
            conexion = connect.getSQLConexion();

            StringBuilder query = new StringBuilder();
            query.append("SELECT p.*, c.nombre, c.apellido, c.dni ")
                 .append("FROM Prestamos p ")
                 .append("JOIN Clientes c ON p.id_cliente = c.id_cliente ")
                 .append("WHERE p.estado in ('aprobado','pagado') AND p.id_cliente = ? ");

            if (busqueda != null && !busqueda.isEmpty()) {
                query.append("AND (c.dni LIKE ? OR c.nombre LIKE ? OR c.apellido LIKE ?) ");
            }

            if (estado != null && !estado.isEmpty()) {
                query.append("AND p.estado = ? ");
            }

            query.append("ORDER BY p.fecha_alta DESC LIMIT ? OFFSET ?");

            pstmt = conexion.prepareStatement(query.toString());

            int paramIndex = 1;
            pstmt.setInt(paramIndex++, idCliente);

            if (busqueda != null && !busqueda.isEmpty()) {
                String wildcard = "%" + busqueda + "%";
                pstmt.setString(paramIndex++, wildcard);
                pstmt.setString(paramIndex++, wildcard);
                pstmt.setString(paramIndex++, wildcard);
            }

            if (estado != null && !estado.isEmpty()) {
                pstmt.setString(paramIndex++, estado);
            }

            pstmt.setInt(paramIndex++, cantidadPorPagina);
            pstmt.setInt(paramIndex, (pagina - 1) * cantidadPorPagina);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Prestamos p = new Prestamos();
                p.setIdPrestamo(rs.getInt("id_prestamo"));
                p.setIdCliente(rs.getInt("id_cliente"));
                p.setIdCuenta(rs.getInt("id_cuenta"));
                p.setFechaAlta(rs.getString("fecha_alta"));
                p.setImporte(rs.getDouble("importe"));
                p.setPlazoMeses(rs.getInt("plazo_meses"));
                p.setCuotaMensual(rs.getDouble("cuota_mensual"));
                p.setEstado(rs.getString("estado"));

                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDni(rs.getString("dni"));
                p.setCliente(cliente);
                p.setCuotasPendientes(rs.getInt("cuotas_pendientes"));
                p.setCuotasPagas(rs.getInt("cuotas_pagas"));

                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conexion != null) Conexion.getConexion().cerrarConexion(conexion);
        }

        return lista;
    }
    
    @Override
    public int contarPrestamosPorCliente(int idCliente, String busqueda, String estado) {
        int total = 0;
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Conexion connect = Conexion.getConexion();
            conexion = connect.getSQLConexion();

            StringBuilder query = new StringBuilder();
            query.append("SELECT COUNT(*) AS total ")
                 .append("FROM Prestamos p ")
                 .append("JOIN Clientes c ON p.id_cliente = c.id_cliente ")
                 .append("WHERE p.id_cliente = ? ");

            if (busqueda != null && !busqueda.isEmpty()) {
                query.append("AND (c.dni LIKE ? OR c.nombre LIKE ? OR c.apellido LIKE ?) ");
            }

            if (estado != null && !estado.isEmpty()) {
                query.append("AND p.estado = ? ");
            }

            pstmt = conexion.prepareStatement(query.toString());

            int paramIndex = 1;
            pstmt.setInt(paramIndex++, idCliente);

            if (busqueda != null && !busqueda.isEmpty()) {
                String wildcard = "%" + busqueda + "%";
                pstmt.setString(paramIndex++, wildcard);
                pstmt.setString(paramIndex++, wildcard);
                pstmt.setString(paramIndex++, wildcard);
            }

            if (estado != null && !estado.isEmpty()) {
                pstmt.setString(paramIndex++, estado);
            }

            rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conexion != null) Conexion.getConexion().cerrarConexion(conexion);
        }

        return total;
    }
    @Override
    public boolean pagarCuota(int idCuenta, int idPrestamo) {
        boolean exito = false;
        Connection conexion = null;
        PreparedStatement stmtPrestamo = null;
        PreparedStatement stmtCuota = null;
        PreparedStatement stmtCuenta = null;
        PreparedStatement stmtMovimiento = null;
        PreparedStatement stmtActualizarEstadoPrestamo = null;
        ResultSet rsObtenerCuotasPendientes = null;
        PreparedStatement stmtObtenerCuotasPendientes = null;

        try {
            Conexion connect = Conexion.getConexion();
            conexion = connect.getSQLConexion();
            conexion.setAutoCommit(false);

            String sqlActualizarPrestamo = "UPDATE Prestamos SET cuotas_pendientes = cuotas_pendientes - 1, cuotas_pagas = cuotas_pagas + 1 WHERE id_prestamo = ? AND cuotas_pendientes > 0";
            stmtPrestamo = conexion.prepareStatement(sqlActualizarPrestamo);
            stmtPrestamo.setInt(1, idPrestamo);
            int filasAfectadas = stmtPrestamo.executeUpdate();
            System.out.println("Filas Afectadas Prestamo: " + filasAfectadas); 

            if (filasAfectadas > 0) {
                String sqlActualizarCuota = "UPDATE Cuotas SET estado = TRUE, fecha_pago = CURRENT_DATE WHERE id_prestamo = ? AND estado = FALSE LIMIT 1";
                stmtCuota = conexion.prepareStatement(sqlActualizarCuota);
                stmtCuota.setInt(1, idPrestamo);
                int filasAfectadasCuota = stmtCuota.executeUpdate();
                System.out.println("Filas Afectadas Cuota: " + filasAfectadasCuota); 

                String sqlDebitarCuenta = "UPDATE Cuentas SET saldo = saldo - (SELECT cuota_mensual FROM Prestamos WHERE id_prestamo = ?) WHERE id_cuenta = ?";
                stmtCuenta = conexion.prepareStatement(sqlDebitarCuenta);
                stmtCuenta.setInt(1, idPrestamo);
                stmtCuenta.setInt(2, idCuenta);
                int filasAfectadasCuenta = stmtCuenta.executeUpdate();
                System.out.println("Filas Afectadas Cuenta: " + filasAfectadasCuenta); 

                double montoCuota = obtenerCuotaMensualPorPrestamo(idPrestamo);

                int idTipoMovimientoPago = 3; 
                String sqlRegistrarMovimiento = "INSERT INTO Movimientos (id_cuenta, id_tipo_movimiento, fecha, concepto, importe) VALUES (?, ?, CURRENT_TIMESTAMP, ?, ?)";
                stmtMovimiento = conexion.prepareStatement(sqlRegistrarMovimiento);
                stmtMovimiento.setInt(1, idCuenta);
                stmtMovimiento.setInt(2, idTipoMovimientoPago);
                stmtMovimiento.setString(3, "Pago de cuota préstamo");
                stmtMovimiento.setDouble(4, montoCuota);
                int filasAfectadasMovimiento = stmtMovimiento.executeUpdate();
                System.out.println("Filas Afectadas Movimiento: " + filasAfectadasMovimiento); 

                String sqlObtenerCuotasPendientes = "SELECT cuotas_pendientes FROM Prestamos WHERE id_prestamo = ?";
                  stmtObtenerCuotasPendientes = conexion.prepareStatement(sqlObtenerCuotasPendientes);
                    stmtObtenerCuotasPendientes.setInt(1, idPrestamo);
                     rsObtenerCuotasPendientes = stmtObtenerCuotasPendientes.executeQuery();
                      int cuotasPendientes = 0;
                       if (rsObtenerCuotasPendientes.next()) {
                     cuotasPendientes = rsObtenerCuotasPendientes.getInt("cuotas_pendientes");
                      System.out.println("Cuotas Pendientes: " + cuotasPendientes); 
                    }

                      if (cuotasPendientes == 0) { 
                               String sqlActualizarEstadoPrestamo = "UPDATE Prestamos SET estado = 'pagado' WHERE id_prestamo = ?";
                                 stmtActualizarEstadoPrestamo = conexion.prepareStatement(sqlActualizarEstadoPrestamo);
                                stmtActualizarEstadoPrestamo.setInt(1, idPrestamo);
                               int filasAfectadasEstadoPrestamo = stmtActualizarEstadoPrestamo.executeUpdate();
                                  if (filasAfectadasEstadoPrestamo > 0) {
                          System.out.println("Se cambio el estado del prÃ©stamo a pagado");
                                } else {
                            System.out.println("no se pudo cambiar el estado del prestamo a pagado");
                            }
                    } else
            {
             System.out.println("Cuotas pendientes es mayor a cero por lo que no se actualiza el prestamo");
            }
                 conexion.commit();
                 exito = true;
               System.out.println("Pago exitoso");

            } else {
                System.out.println("No se actualizo el prestamo debido a que las filas afectatas fueron 0");
                conexion.rollback(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conexion != null) conexion.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            System.err.println("Error en el pago: " + e.getMessage()); 

        } finally {
            try {
                if (stmtPrestamo != null) stmtPrestamo.close();
                if (stmtCuota != null) stmtCuota.close();
                if (stmtCuenta != null) stmtCuenta.close();
                if (stmtMovimiento != null) stmtMovimiento.close();
                   if (stmtObtenerCuotasPendientes != null) stmtObtenerCuotasPendientes.close();
                    if (conexion != null) Conexion.getConexion().cerrarConexion(conexion);
                      if (rsObtenerCuotasPendientes != null) rsObtenerCuotasPendientes.close();
                      if (stmtActualizarEstadoPrestamo != null) stmtActualizarEstadoPrestamo.close(); //Cerrar
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exito;
    }
    
    public double obtenerCuotaMensualPorPrestamo(int idPrestamo) {
        double cuotaMensual = 0.0;
        Connection conexion = null; 
        PreparedStatement ps = null;
        ResultSet rs = null; 

        try {
            Conexion connect = Conexion.getConexion();
            conexion = connect.getSQLConexion(); 
            String sql = "SELECT cuota_mensual FROM Prestamos WHERE id_prestamo = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idPrestamo); 
            rs = ps.executeQuery(); 
            
            if (rs.next()) {
                cuotaMensual = rs.getDouble("cuota_mensual"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) Conexion.getConexion().cerrarConexion(conexion); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cuotaMensual; 
    }


}