package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import datos.InformesDao;
import entidades.InformeSimple;
import entidades.ResumenTransaccional;

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
    
    // Resumen Transaccional
    @Override
    public Map<String, ResumenTransaccional> obtenerResumenTransaccional(String tipoMovimientoOpcional) throws SQLException {
        Map<String, ResumenTransaccional> resumen = new HashMap<>();

        String sql = "SELECT tm.descripcion AS tipo, COUNT(*) AS volumen, SUM(m.importe) AS monto_total, AVG(m.importe) AS importe_promedio " +
                     "FROM movimientos m " +
                     "JOIN tipos_movimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento ";

        if (tipoMovimientoOpcional != null && !tipoMovimientoOpcional.isEmpty()) {
            sql += "WHERE tm.descripcion = ? ";
        }

        sql += "GROUP BY tm.descripcion";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            if (tipoMovimientoOpcional != null && !tipoMovimientoOpcional.isEmpty()) {
                ps.setString(1, tipoMovimientoOpcional);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String tipo = rs.getString("tipo");
                    int volumen = rs.getInt("volumen");
                    double monto = rs.getDouble("monto_total");
                    double promedio = rs.getDouble("importe_promedio");

                    ResumenTransaccional resumenItem = new ResumenTransaccional(tipo, volumen, monto, promedio);
                    resumen.put(tipo, resumenItem);
                }
            }
        }

        return resumen;
    }
    
    // Prestamos
    
    @Override
    public double obtenerCapitalPrestado() throws SQLException {
        String sql = "SELECT COALESCE(SUM(importe), 0) AS total FROM Prestamos";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble("total");
        }
        return 0.0;
    }

    @Override
    public int obtenerCantidadPrestamos() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM Prestamos";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt("total");
        }
        return 0;
    }

    @Override
    public double obtenerTasaAprobacion() throws SQLException {
        String sql = "SELECT (COUNT(CASE WHEN estado = 'aprobado' THEN 1 END) * 100.0 / COUNT(*)) AS tasa " +
                     "FROM Prestamos";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble("tasa");
        }
        return 0.0;
    }

    @Override
    public double obtenerTasaMorosidad() throws SQLException {
        String sql = "SELECT (COUNT(CASE WHEN estado = 'pendiente' THEN 1 END) * 100.0 / COUNT(*)) AS tasa " +
                     "FROM Prestamos";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble("tasa");
        }
        return 0.0;
    }

    @Override
    public Map<String, Integer> obtenerPrestamosPorEstado() throws SQLException {
        String sql = "SELECT estado, COUNT(*) AS cantidad FROM Prestamos GROUP BY estado";
        Map<String, Integer> resultado = new HashMap<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.put(rs.getString("estado"), rs.getInt("cantidad"));
            }
        }
        return resultado;
    }

    @Override
    public Map<String, Map<String, Integer>> obtenerPrestamosPorMesEstado() throws SQLException {
        String sql = "SELECT DATE_FORMAT(fecha_alta, '%M') AS mes, estado, COUNT(*) AS cantidad " +
                     "FROM Prestamos GROUP BY mes, estado ORDER BY STR_TO_DATE(CONCAT('01 ', mes, ' 2025'), '%d %M %Y')";
        Map<String, Map<String, Integer>> resultado = new LinkedHashMap<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String mes = rs.getString("mes");
                String estado = rs.getString("estado");
                int cantidad = rs.getInt("cantidad");
                resultado.putIfAbsent(mes, new HashMap<>());
                resultado.get(mes).put(estado, cantidad);
            }
        }
        return resultado;
    }

    // Informes Simples
    @Override
    public InformeSimple obtenerInformeClientes() {
        String columnas = "Clientes Activos, Clientes Inactivos, Total de Clientes";
        String valores = "0,0,0";
        try {
            String sql = "SELECT " +
                         "SUM(CASE WHEN estado = 1 THEN 1 ELSE 0 END) AS activos, " +
                         "SUM(CASE WHEN estado = 0 THEN 1 ELSE 0 END) AS inactivos, " +
                         "COUNT(*) AS total " +
                         "FROM Clientes";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                valores = rs.getInt("activos") + "," + rs.getInt("inactivos") + "," + rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new InformeSimple(columnas, valores);
    }

    @Override
    public InformeSimple obtenerInformePrestamos() {
        String columnas = "Préstamos Activos, Préstamos Pagados, Total de Préstamos";
        String valores = "0,0,0";
        try {
            String sql = "SELECT " +
                         "SUM(CASE WHEN estado IN ('pendiente', 'aprobado') THEN 1 ELSE 0 END) AS activos, " +
                         "SUM(CASE WHEN estado = 'pagado' THEN 1 ELSE 0 END) AS pagados, " +
                         "COUNT(*) AS total " +
                         "FROM Prestamos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                valores = rs.getInt("activos") + "," + rs.getInt("pagados") + "," + rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new InformeSimple(columnas, valores);
    }

    @Override
    public InformeSimple obtenerInformeCuentas() {
        String columnas = "Cuentas Corrientes, Cuentas de Ahorro, Total de Cuentas";
        String valores = "0,0,0";
        try {
            String sql = "SELECT " +
                         "SUM(CASE WHEN id_tipo_cuenta = 1 THEN 1 ELSE 0 END) AS corriente, " +
                         "SUM(CASE WHEN id_tipo_cuenta = 2 THEN 1 ELSE 0 END) AS ahorro, " +
                         "COUNT(*) AS total " +
                         "FROM Cuentas";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                valores = rs.getInt("corriente") + "," + rs.getInt("ahorro") + "," + rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new InformeSimple(columnas, valores);
    }

    @Override
    public InformeSimple obtenerInformeTransacciones() {
        String columnas = "Transferencias, Depósitos, Total Movimientos";
        String valores = "0,0,0";
        try {
            String sql = "SELECT " +
                         "(SELECT COUNT(*) FROM Transferencias) AS transferencias, " +
                         "(SELECT COUNT(*) FROM Movimientos WHERE id_tipo_movimiento = 1) AS depositos, " +
                         "(SELECT COUNT(*) FROM Movimientos) AS total";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                valores = rs.getInt("transferencias") + "," + rs.getInt("depositos") + "," + rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new InformeSimple(columnas, valores);
    }
}
