package servlets;

import negocio.InformesNegocio;
import negocioImpl.InformesNegocioImpl;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InformesNegocio informesNegocio = new InformesNegocioImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // Parámetros de fechas fijas o desde request (puedes adaptarlo para recibirlos)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaInicio = LocalDate.parse("01/06/2025", formatter);
            LocalDate fechaFin = LocalDate.parse("30/06/2025", formatter);

            // Segmentación
            Map<String, Integer> cantidadClientesPorSegmento = informesNegocio.obtenerCantidadClientesPorSegmento();
            Map<String, Double> montoTotalPorSegmento = informesNegocio.obtenerMontosPorSegmento();

            // Calcular porcentaje saldo por segmento
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

            // Datos de préstamos (puedes obtener de negocio o mock aquí)
            double capitalPrestado = 1250000.00;
            int cantidadPrestamos = 320;
            double tasaAprobacion = 72.5;
            double tasaMorosidad = 5.8;

            Map<String, Integer> prestamosPorEstado = new HashMap<>();
            prestamosPorEstado.put("Aprobado", 180);
            prestamosPorEstado.put("En Revisión", 70);
            prestamosPorEstado.put("Rechazado", 40);
            prestamosPorEstado.put("Moroso", 30);

            Map<String, Map<String, Integer>> prestamosPorMesEstado = new LinkedHashMap<>();
            Map<String, Integer> junio = new HashMap<>();
            junio.put("Aprobado", 90);
            junio.put("Rechazado", 20);
            junio.put("Moroso", 10);
            prestamosPorMesEstado.put("Junio", junio);

            Map<String, Integer> julio = new HashMap<>();
            julio.put("Aprobado", 90);
            julio.put("En Revisión", 70);
            julio.put("Moroso", 20);
            prestamosPorMesEstado.put("Julio", julio);

            // Datos para transaccionalidad (mock o negocio)
            // Aquí debes agregar tus métodos para obtener estos datos reales
            Map<String, Integer> volumenPorTipo = new HashMap<>(); // ejemplo vacío
            Map<String, Double> montoPorTipo = new HashMap<>();
            Map<String, Double> promedioPorTipo = new HashMap<>();
            String tipoMovimiento = null;

            // Asignar todos los atributos al request
            request.setAttribute("cantidadClientesPorSegmento", cantidadClientesPorSegmento);
            request.setAttribute("montoTotalPorSegmento", montoTotalPorSegmento);
            request.setAttribute("porcentajeSaldoPorSegmento", porcentajeSaldoPorSegmento);
            request.setAttribute("fechaReporte", "01/07/2025");
            request.setAttribute("nuevosClientesPorFecha", nuevosClientesPorFecha);
            request.setAttribute("nuevasCuentasPorFecha", nuevasCuentasPorFecha);
            request.setAttribute("cuentasPorTipo", cuentasPorTipo);
            request.setAttribute("tasaCrecimiento", tasaCrecimiento.getPorcentaje());
            request.setAttribute("fechaInicio", "01/06/2025");
            request.setAttribute("fechaFin", "30/06/2025");

            request.setAttribute("volumenPorTipo", volumenPorTipo);
            request.setAttribute("montoPorTipo", montoPorTipo);
            request.setAttribute("importePromedioPorTipo", promedioPorTipo);
            request.setAttribute("tipoMovimiento", tipoMovimiento);

            request.setAttribute("capitalPrestado", capitalPrestado);
            request.setAttribute("cantidadPrestamos", cantidadPrestamos);
            request.setAttribute("tasaAprobacion", tasaAprobacion);
            request.setAttribute("tasaMorosidad", tasaMorosidad);
            request.setAttribute("prestamosPorEstado", prestamosPorEstado);
            request.setAttribute("prestamosPorMesEstado", prestamosPorMesEstado);

            // Forward a la JSP
            request.getRequestDispatcher("/AdminInformes.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error interno en el servidor: " + e.getMessage());
        }
    }
}
