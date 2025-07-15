package negocioImpl;

import datos.InformesDao;
import datosImpl.InformesDaoImpl;
import entidades.InformeSimple;
import entidades.RegistroPorFecha;
import entidades.ResumenTransaccional;
import entidades.SegmentoSaldo;
import entidades.TasaCrecimiento;
import negocio.InformesNegocio;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class InformesNegocioImpl implements InformesNegocio {

    private InformesDao dao = new InformesDaoImpl();
    
    @Override
    public Map<String, Integer> obtenerCantidadClientesPorSegmento() {
        try {
            return dao.obtenerCantidadClientesPorSegmento();
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public Map<String, Double> obtenerMontosPorSegmento() {
        try {
            return dao.obtenerMontosPorSegmento();
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public Map<LocalDate, Integer> obtenerNuevosClientesPorFecha() {
        try {
            return dao.obtenerNuevosClientesPorFecha();
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public Map<LocalDate, Integer> obtenerNuevasCuentasPorFecha() {
        try {
            return dao.obtenerNuevasCuentasPorFecha();
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    
    @Override
    public Map<String, Integer> obtenerCuentasPorTipo() {
        try {
            return dao.obtenerCuentasPorTipo();
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public TasaCrecimiento calcularTasaCrecimiento(LocalDate fechaInicio, LocalDate fechaFin) {
        try {
            int clientesAntes = dao.obtenerCantidadClientesAntesDe(fechaInicio);
            int clientesDespues = dao.obtenerCantidadClientesHasta(fechaFin);

            int nuevosClientes = clientesDespues - clientesAntes;
            double tasa = clientesAntes > 0 ? (nuevosClientes * 100.0 / clientesAntes) : 0;

            return new TasaCrecimiento(fechaInicio, fechaFin, clientesAntes, clientesDespues, tasa);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Double obtenerTasaCrecimientoComoPorcentaje(LocalDate inicio, LocalDate fin) {
        TasaCrecimiento tasa = calcularTasaCrecimiento(inicio, fin);
        return tasa != null ? tasa.getPorcentaje() : 0.0;
    }
    
    // Resumen transaccional
    @Override
    public Map<String, ResumenTransaccional> obtenerResumenTransaccional(String tipoMovimientoOpcional) {
        try {
            return dao.obtenerResumenTransaccional(tipoMovimientoOpcional);
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    // Prestamos
    @Override
    public double obtenerCapitalPrestado() throws SQLException {
        return dao.obtenerCapitalPrestado();
    }

    @Override
    public int obtenerCantidadPrestamos() throws SQLException {
        return dao.obtenerCantidadPrestamos();
    }

    @Override
    public double obtenerTasaAprobacion() throws SQLException {
        return dao.obtenerTasaAprobacion();
    }

    @Override
    public double obtenerTasaMorosidad() throws SQLException {
        return dao.obtenerTasaMorosidad();
    }

    @Override
    public Map<String, Integer> obtenerPrestamosPorEstado() throws SQLException {
        return dao.obtenerPrestamosPorEstado();
    }

    @Override
    public Map<String, Map<String, Integer>> obtenerPrestamosPorMesEstado() throws SQLException {
        return dao.obtenerPrestamosPorMesEstado();
    }
    
    @Override
    public InformeSimple obtenerInformeClientes() {
        return dao.obtenerInformeClientes();
    }

    @Override
    public InformeSimple obtenerInformePrestamos() {
        return dao.obtenerInformePrestamos();
    }

    @Override
    public InformeSimple obtenerInformeCuentas() {
        return dao.obtenerInformeCuentas();
    }

    @Override
    public InformeSimple obtenerInformeTransacciones() {
        return dao.obtenerInformeTransacciones();
    }
}
