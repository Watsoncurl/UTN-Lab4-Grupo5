package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.PrestamosDao;
import entidades.Cliente;
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

            String query = "INSERT INTO Prestamos (id_cliente, id_cuenta, fecha_alta, importe, plazo_meses, cuota_mensual, estado) VALUES (?, ?, CURDATE(), ?, ?, ?, ?)";
            pstmt = conexion.prepareStatement(query);
            pstmt.setInt(1, prestamo.getIdCliente());
            pstmt.setInt(2, prestamo.getIdCuenta());
            pstmt.setDouble(3, prestamo.getImporte());
            pstmt.setInt(4, prestamo.getPlazoMeses());
            pstmt.setDouble(5, prestamo.getCuotaMensual());
            pstmt.setString(6, prestamo.getEstado());

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                conexion.commit();
                exito = true;
            } else {
                conexion.rollback();
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar pr�stamo: " + e.getMessage());
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
            query.append("SELECT p.*, c.nombre, c.apellido, c.dni ")
                 .append("FROM Prestamos p ")
                 .append("JOIN Clientes c ON p.id_cliente = c.id_cliente ")
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


}