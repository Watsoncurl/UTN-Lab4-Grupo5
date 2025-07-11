package servlets;

import negocio.InformesNegocio;
import negocioImpl.InformesNegocioImpl;
import entidades.ResumenTransaccional;
import entidades.TasaCrecimiento;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet("/AdminInformes")
public class ServletAdminInformes extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private InformesNegocio informesNegocio = new InformesNegocioImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaInicio = LocalDate.parse("01/06/2025", formatter);
            LocalDate fechaFin = LocalDate.parse("30/06/2025", formatter);

            // Segmentación
            Map<String, Integer> cantidadClientesPorSegmento = informesNegocio.obtenerCantidadClientesPorSegmento();
            Map<String, Double> montoTotalPorSegmento = informesNegocio.obtenerMontosPorSegmento();

            // Porcentaje por segmento
            double totalMontos = montoTotalPorSegmento.values().stream().mapToDouble(Double::doubleValue).sum();
            Map<String, Double> porcentajeSaldoPorSegmento = new HashMap<>();
            for (String segmento : montoTotalPorSegmento.keySet()) {
                double porcentaje = totalMontos > 0 ? montoTotalPorSegmento.get(segmento) * 100 / totalMontos : 0;
                porcentajeSaldoPorSegmento.put(segmento, porcentaje);
            }

            // Nuevos clientes y cuentas por fecha
            Map<LocalDate, Integer> nuevosClientesPorFecha = informesNegocio.obtenerNuevosClientesPorFecha();
            Map<LocalDate, Integer> nuevasCuentasPorFecha = informesNegocio.obtenerNuevasCuentasPorFecha();

            // Cuentas por tipo
            Map<String, Integer> cuentasPorTipo = informesNegocio.obtenerCuentasPorTipo();

            // Tasa de crecimiento
            TasaCrecimiento tasaCrecimiento = informesNegocio.calcularTasaCrecimiento(fechaInicio, fechaFin);

            // Datos de préstamos reales
            double capitalPrestado = informesNegocio.obtenerCapitalPrestado();
            int cantidadPrestamos = informesNegocio.obtenerCantidadPrestamos();
            double tasaAprobacion = informesNegocio.obtenerTasaAprobacion();

            Map<String, Integer> prestamosPorEstado = informesNegocio.obtenerPrestamosPorEstado();
            Map<String, Map<String, Integer>> prestamosPorMesEstado = informesNegocio.obtenerPrestamosPorMesEstado();

            // Resumen transaccional
            Map<String, ResumenTransaccional> resumenTransaccional = informesNegocio.obtenerResumenTransaccional(null);
            Map<String, Integer> volumenPorTipo = new HashMap<>();
            Map<String, Double> montoPorTipo = new HashMap<>();
            Map<String, Double> promedioPorTipo = new HashMap<>();

            for (Map.Entry<String, ResumenTransaccional> entry : resumenTransaccional.entrySet()) {
                volumenPorTipo.put(entry.getKey(), entry.getValue().getVolumen());
                montoPorTipo.put(entry.getKey(), entry.getValue().getMontoTotal());
                promedioPorTipo.put(entry.getKey(), entry.getValue().getImportePromedio());
            }

            String tipoMovimiento = null; // podría venir como parámetro

            // Seteo de atributos para JSP
            request.setAttribute("fechaInicio", "01/06/2025");
            request.setAttribute("fechaFin", "30/06/2025");
            request.setAttribute("fechaReporte", "01/07/2025");

            request.setAttribute("cantidadClientesPorSegmento", cantidadClientesPorSegmento);
            request.setAttribute("montoTotalPorSegmento", montoTotalPorSegmento);
            request.setAttribute("porcentajeSaldoPorSegmento", porcentajeSaldoPorSegmento);

            request.setAttribute("nuevosClientesPorFecha", nuevosClientesPorFecha);
            request.setAttribute("nuevasCuentasPorFecha", nuevasCuentasPorFecha);
            request.setAttribute("cuentasPorTipo", cuentasPorTipo);
            request.setAttribute("tasaCrecimiento", tasaCrecimiento != null ? tasaCrecimiento.getPorcentaje() : 0.0);

            request.setAttribute("volumenPorTipo", volumenPorTipo);
            request.setAttribute("montoPorTipo", montoPorTipo);
            request.setAttribute("importePromedioPorTipo", promedioPorTipo);
            request.setAttribute("tipoMovimiento", tipoMovimiento);

            request.setAttribute("capitalPrestado", capitalPrestado);
            request.setAttribute("cantidadPrestamos", cantidadPrestamos);
            request.setAttribute("tasaAprobacion", tasaAprobacion);

            request.setAttribute("prestamosPorEstado", prestamosPorEstado);
            request.setAttribute("prestamosPorMesEstado", prestamosPorMesEstado);

            request.getRequestDispatcher("/AdminInformes.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error interno en el servidor: " + e.getMessage());
        }
    }
}
