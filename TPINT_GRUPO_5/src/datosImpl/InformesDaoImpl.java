package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

import datos.InformesDao;

public class InformesDaoImpl implements InformesDao {

    private Connection conn = Conexion.getConexion().getSQLConexion();
    
    // Segmentation report
    @Override
    public Map<String, Integer> obtenerCantidadClientesPorSegmento() throws SQLException {
        String sql = "SELECT segmento, COUNT(*) AS cantidad_clientes " +
                     "FROM ( " +
                     "  SELECT c.id_cliente, " +
                     "         CASE " +
                     "           WHEN SUM(cta.saldo) < 50000 THEN 'Bajo' " +
                     "           WHEN SUM(cta.saldo) < 200000 THEN 'Medio' " +
                     "           ELSE 'Alto' " +
                     "         END AS segmento " +
                     "  FROM Clientes c " +
                     "  JOIN Cuentas cta ON c.id_cliente = cta.id_cliente " +
                     "  GROUP BY c.id_cliente " +
                     ") AS clientes_segmentados " +
                     "GROUP BY segmento;";

        Map<String, Integer> resultado = new HashMap<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.put(rs.getString("segmento"), rs.getInt("cantidad_clientes"));
            }
        }
        return resultado;
    }

    @Override
    public Map<String, Double> obtenerMontosPorSegmento() throws SQLException {
        String sql = "SELECT segmento, SUM(saldo_total) AS monto_total " +
                     "FROM ( " +
                     "  SELECT c.id_cliente, " +
                     "         SUM(cta.saldo) AS saldo_total, " +
                     "         CASE " +
                     "           WHEN SUM(cta.saldo) < 50000 THEN 'Bajo' " +
                     "           WHEN SUM(cta.saldo) < 200000 THEN 'Medio' " +
                     "           ELSE 'Alto' " +
                     "         END AS segmento " +
                     "  FROM Clientes c " +
                     "  JOIN Cuentas cta ON c.id_cliente = cta.id_cliente " +
                     "  GROUP BY c.id_cliente " +
                     ") AS clientes_segmentados " +
                     "GROUP BY segmento;";

        Map<String, Double> resultado = new HashMap<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.put(rs.getString("segmento"), rs.getDouble("monto_total"));
            }
        }
        return resultado;
    }

    @Override
    public TreeMap<LocalDate, Integer> obtenerNuevosClientesPorFecha() throws SQLException {
        String sql = 
            "SELECT fecha_alta, COUNT(*) AS nuevos_clientes " +
            "FROM ( " +
            "  SELECT c.id_cliente, MIN(cu.fecha_creacion) AS fecha_alta " +
            "  FROM Clientes c " +
            "  JOIN Cuentas cu ON c.id_cliente = cu.id_cliente " +
            "  GROUP BY c.id_cliente " +
            ") AS fechas_altas_clientes " +
            "GROUP BY fecha_alta " +
            "ORDER BY fecha_alta;";

        TreeMap<LocalDate, Integer> resultado = new TreeMap<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.put(rs.getDate("fecha_alta").toLocalDate(), rs.getInt("nuevos_clientes"));
            }
        }
        return resultado;
    }


    @Override
    public TreeMap<LocalDate, Integer> obtenerNuevasCuentasPorFecha() throws SQLException {
        String sql = "SELECT DATE(fecha_creacion) AS fecha_creacion, COUNT(*) AS cantidad " +
                     "FROM Cuentas " +
                     "GROUP BY DATE(fecha_creacion) " +
                     "ORDER BY fecha_creacion;";

        TreeMap<LocalDate, Integer> resultado = new TreeMap<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.put(rs.getDate("fecha_creacion").toLocalDate(), rs.getInt("cantidad"));
            }
        }
        return resultado;
    }

    @Override
    public Map<String, Integer> obtenerCuentasPorTipo() throws SQLException {
        String sql = "SELECT tc.descripcion AS tipo_cuenta, COUNT(*) AS cantidad " +
                     "FROM Cuentas c " +
                     "JOIN Tipos_Cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta " +
                     "GROUP BY tc.descripcion;";

        Map<String, Integer> resultado = new HashMap<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.put(rs.getString("tipo_cuenta"), rs.getInt("cantidad"));
            }
        }
        return resultado;
    }

    @Override
    public int obtenerCantidadClientesAntesDe(LocalDate fechaInicio) throws SQLException {
        String sql = "SELECT COUNT(DISTINCT c.id_cliente) AS cantidad " +
                     "FROM Clientes c " +
                     "JOIN Cuentas cu ON c.id_cliente = cu.id_cliente " +
                     "WHERE cu.fecha_creacion < ?;";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(fechaInicio));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("cantidad");
            }
        }
        return 0;
    }

    @Override
    public int obtenerCantidadClientesHasta(LocalDate fechaFin) throws SQLException {
        String sql = "SELECT COUNT(DISTINCT c.id_cliente) AS cantidad " +
                     "FROM Clientes c " +
                     "JOIN Cuentas cu ON c.id_cliente = cu.id_cliente " +
                     "WHERE cu.fecha_creacion <= ?;";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(fechaFin));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("cantidad");
            }
        }
        return 0;
    }
    
    // customerGrowthReport
    
}
