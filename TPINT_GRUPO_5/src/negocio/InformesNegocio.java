package negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import entidades.SegmentoSaldo;
import entidades.RegistroPorFecha;
import entidades.TasaCrecimiento;

public interface InformesNegocio {
    
	Map<String, Integer> obtenerCantidadClientesPorSegmento();
	
	Map<String, Double> obtenerMontosPorSegmento();

    Map<LocalDate, Integer> obtenerNuevosClientesPorFecha();
    
    Map<LocalDate, Integer> obtenerNuevasCuentasPorFecha();
    
    Map<String, Integer> obtenerCuentasPorTipo();
    
    TasaCrecimiento calcularTasaCrecimiento(LocalDate fechaInicio, LocalDate fechaFin);

	Double obtenerTasaCrecimientoComoPorcentaje(LocalDate inicio, LocalDate fin);
}
