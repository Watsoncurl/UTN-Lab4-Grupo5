package datos;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import entidades.ResumenTransaccional;

public interface InformesDao {

    // Segmentación de clientes
    Map<String, Integer> obtenerCantidadClientesPorSegmento() throws SQLException;

    Map<String, Double> obtenerMontosPorSegmento() throws SQLException;

    // Nuevos clientes y cuentas por fecha
    TreeMap<LocalDate, Integer> obtenerNuevosClientesPorFecha() throws SQLException;

    TreeMap<LocalDate, Integer> obtenerNuevasCuentasPorFecha() throws SQLException;

    // Cuentas agrupadas por tipo
    Map<String, Integer> obtenerCuentasPorTipo() throws SQLException;

    // Tasa de crecimiento entre dos fechas
    int obtenerCantidadClientesAntesDe(LocalDate fechaInicio) throws SQLException;

    int obtenerCantidadClientesHasta(LocalDate fechaFin) throws SQLException;
    
    // Resumen transaccional
    Map<String, ResumenTransaccional> obtenerResumenTransaccional(String tipoMovimientoOpcional) throws SQLException;
    
    // Prestamos
    double obtenerCapitalPrestado() throws SQLException;
    int obtenerCantidadPrestamos() throws SQLException;
    double obtenerTasaAprobacion() throws SQLException;
    double obtenerTasaMorosidad() throws SQLException;
    Map<String, Integer> obtenerPrestamosPorEstado() throws SQLException;
    Map<String, Map<String, Integer>> obtenerPrestamosPorMesEstado() throws SQLException;
}
