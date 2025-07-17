package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entidades.Cuentas;
import entidades.Movimientos;
import filtros.MovimientosFiltros;
import negocio.CuentasNegocio;
import negocio.MovimientosNegocio;
import negocioImpl.CuentasNegocioImpl;
import negocioImpl.MovimientosNegocioImpl;

@WebServlet("/ServletIngresarCuenta")
public class ServletIngresarCuenta extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CuentasNegocio cuentasNegocio;
    private MovimientosNegocio movimientosNegocio;

    public ServletIngresarCuenta() {
        super();
        cuentasNegocio = new CuentasNegocioImpl();
        movimientosNegocio = new MovimientosNegocioImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nroCuenta = request.getParameter("nroCuenta");

            HttpSession session = request.getSession();
            Object idClienteObj = session.getAttribute("idCliente");

            if (idClienteObj == null) {
                request.setAttribute("error", "Error: No se encontr� el ID del cliente en la sesi�n.");
                request.getRequestDispatcher("ClienteIngresarCuenta.jsp").forward(request, response);
                return;
            }

            int idCliente = (int) idClienteObj;

            List<Cuentas> listaCuentas = cuentasNegocio.obtenerCuentasPorIdCliente(idCliente);
            request.setAttribute("cuentas", listaCuentas); // Siempre pasar la lista de cuentas

            if (nroCuenta != null && !nroCuenta.isEmpty()) {
                // Obtener la informaci�n de la cuenta seleccionada
                Cuentas cuentaSeleccionada = cuentasNegocio.obtenerPorNroCuenta(nroCuenta);

                if (cuentaSeleccionada == null) {
                    request.setAttribute("error", "No se encontr� la cuenta con el n�mero: " + nroCuenta);
                    request.getRequestDispatcher("ClienteIngresarCuenta.jsp").forward(request, response);
                    return;
                }
                 request.setAttribute("Cuenta", cuentaSeleccionada);

                // --- Paginaci�n ---
                int pagina = 1;
                try {
                    pagina = Integer.parseInt(request.getParameter("pagina"));
                    if (pagina <= 0) {
                        pagina = 1; // Valor por defecto si es inv�lido
                    }
                } catch (NumberFormatException e) {
                    // Si no se puede parsear a entero, usa la p�gina 1
                }
                int registrosPorPagina = 10;
                int offset = (pagina - 1) * registrosPorPagina;
                // --- Fin Paginaci�n ---

                MovimientosFiltros filtro = new MovimientosFiltros();
                filtro.setBusqueda(nroCuenta); // Filtrar movimientos por la cuenta seleccionada

                String tipoMovimiento = request.getParameter("tipoMovimiento");
                String fechaDesde = request.getParameter("desdeDate");
                String fechaHasta = request.getParameter("hastaDate");

                if (tipoMovimiento != null && !tipoMovimiento.isEmpty()) filtro.setTipoMovimiento(tipoMovimiento);
                if (fechaDesde != null && !fechaDesde.isEmpty()) filtro.setFechaDesde(fechaDesde);
                if (fechaHasta != null && !fechaHasta.isEmpty()) filtro.setFechaHasta(fechaHasta);

                List<Movimientos> movimientosFiltrados = movimientosNegocio.ListarTodosFiltradosPaginado(filtro, offset, registrosPorPagina);

                // Obtener la cantidad total de movimientos filtrados para calcular el total de paginas.
                int totalMovimientos = movimientosNegocio.obtenerTotalMovimientosFiltrados(filtro);
                int totalPaginas = (int) Math.ceil((double) totalMovimientos / registrosPorPagina);

                request.setAttribute("listaMovimientos", movimientosFiltrados);
                request.setAttribute("totalPaginas", totalPaginas);
                request.setAttribute("paginaActual", pagina);

                // --- Mantener los par�metros para la paginaci�n ---
                request.setAttribute("nroCuenta", nroCuenta);
                request.setAttribute("desdeDate", fechaDesde);
                request.setAttribute("hastaDate", fechaHasta);
                request.setAttribute("tipoMovimiento", tipoMovimiento);
                // --- Fin Mantener Par�metros ---
            } else {
                // Si no se ha seleccionado ninguna cuenta, establecer un mensaje o valor predeterminado
                request.setAttribute("mensaje", "Seleccione una cuenta para ver los detalles y movimientos.");
            }

            request.getRequestDispatcher("ClienteIngresarCuenta.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar movimientos: " + e.getMessage());
            request.getRequestDispatcher("ClienteIngresarCuenta.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}