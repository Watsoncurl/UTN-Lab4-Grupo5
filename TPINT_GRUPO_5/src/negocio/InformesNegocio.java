package negocio;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import entidades.SegmentoSaldo;
import entidades.RegistroPorFecha;
import entidades.ResumenTransaccional;
import entidades.TasaCrecimiento;

public interface InformesNegocio {
    
	Map<String, Integer> obtenerCantidadClientesPorSegmento();
	
	Map<String, Double> obtenerMontosPorSegmento();

    Map<LocalDate, Integer> obtenerNuevosClientesPorFecha();
    
    Map<LocalDate, Integer> obtenerNuevasCuentasPorFecha();
    
    Map<String, Integer> obtenerCuentasPorTipo();
    
    TasaCrecimiento calcularTasaCrecimiento(LocalDate fechaInicio, LocalDate fechaFin);

	Double obtenerTasaCrecimientoComoPorcentaje(LocalDate inicio, LocalDate fin);
	
	Map<String, ResumenTransaccional> obtenerResumenTransaccional(String tipoMovimientoOpcional);
	
	double obtenerCapitalPrestado() throws SQLException;
    int obtenerCantidadPrestamos() throws SQLException;
    double obtenerTasaAprobacion() throws SQLException;
    double obtenerTasaMorosidad() throws SQLException;
    Map<String, Integer> obtenerPrestamosPorEstado() throws SQLException;
    Map<String, Map<String, Integer>> obtenerPrestamosPorMesEstado() throws SQLException;

}
