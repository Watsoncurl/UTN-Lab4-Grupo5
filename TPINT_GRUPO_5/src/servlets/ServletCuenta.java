package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import entidades.Cuentas;
import filtros.CuentasFiltros;
import negocio.CuentasNegocio;
import negocioImpl.CuentasNegocioImpl;

@WebServlet("/ListarCuentasServlet")
public class ServletCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int REGISTROS_POR_PAGINA = 7;
    private CuentasNegocio cuentasNegocio;

    @Override
    public void init() throws ServletException {
        super.init();
        cuentasNegocio = new CuentasNegocioImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesarSolicitud(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesarSolicitud(request, response);
    }

    private void procesarSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion) || "activar".equals(accion)) {
            // Procesa la acción de eliminar o activar cuenta
            String nroCuenta = request.getParameter("nro_cuenta");
            String busqueda = request.getParameter("busqueda");
            String tipoCuenta = request.getParameter("tipoCuenta");
            String estado = request.getParameter("estado");
            String pagina = request.getParameter("pagina");

            boolean resultado = false;

            if ("eliminar".equals(accion)) {
                System.out.println("Eliminando cuenta: " + nroCuenta);
                resultado = cuentasNegocio.eliminarCuenta(nroCuenta);
                if (resultado) {
                    request.getSession().setAttribute("mensaje", "Cuenta eliminada correctamente.");
                } else {
                    request.getSession().setAttribute("error", "No se pudo eliminar la cuenta.");
                }
            } else if ("activar".equals(accion)) {
                System.out.println("Activando cuenta: " + nroCuenta);
                resultado = cuentasNegocio.activarCuentaPorNroCuenta(nroCuenta);
                if (resultado) {
                    request.getSession().setAttribute("mensaje", "Cuenta activada correctamente.");
                } else {
                    request.getSession().setAttribute("error", "No se pudo activar la cuenta.");
                }
            }

            StringBuilder redirectURL = new StringBuilder("ListarCuentasServlet?pagina=");
            redirectURL.append(pagina != null ? pagina : "1");
            if (busqueda != null && !busqueda.isEmpty()) {
                redirectURL.append("&busqueda=").append(busqueda);
            }
            if (tipoCuenta != null && !tipoCuenta.isEmpty()) {
                redirectURL.append("&tipoCuenta=").append(tipoCuenta);
            }
            if (estado != null && !estado.isEmpty()) {
                redirectURL.append("&estado=").append(estado);
            }
            System.out.println("Redireccionando a: " + redirectURL.toString());
            response.sendRedirect(redirectURL.toString());
        } else {
            // Carga y muestra la lista de cuentas
            try {
                int pagina = obtenerPagina(request);
                int inicio = (pagina - 1) * REGISTROS_POR_PAGINA;
                String busqueda = request.getParameter("busqueda");
                String tipoCuenta = request.getParameter("tipoCuenta");
                String estadoParam = request.getParameter("estado");

                Boolean estadoFiltro = null;
                if ("Activas".equalsIgnoreCase(estadoParam)) {
                    estadoFiltro = true;
                } else if ("Inactivas".equalsIgnoreCase(estadoParam)) {
                    estadoFiltro = false;
                }
                 CuentasNegocio cuentasNegocio = new CuentasNegocioImpl();
                List<Cuentas> listaCuentas = cuentasNegocio.listarPaginadasFiltradas(
                        inicio,
                        REGISTROS_POR_PAGINA,
                        (busqueda != null && !busqueda.isEmpty()) ? busqueda : null,
                        (tipoCuenta != null && !tipoCuenta.isEmpty() && !"Todos los tipos".equals(tipoCuenta)) ? tipoCuenta : null,
                        estadoFiltro
                );

                int totalCuentas = cuentasNegocio.contarTotalCuentasFiltradas(
                        (busqueda != null && !busqueda.isEmpty()) ? busqueda : null,
                        (tipoCuenta != null && !tipoCuenta.isEmpty() && !"Todos los tipos".equals(tipoCuenta)) ? tipoCuenta : null,
                        estadoFiltro
                );

                int totalPaginas = (int) Math.ceil((double) totalCuentas / REGISTROS_POR_PAGINA);

                if (pagina < 1) pagina = 1;
                if (pagina > totalPaginas && totalPaginas != 0) pagina = totalPaginas;

                request.setAttribute("listaCuentas", listaCuentas);
                request.setAttribute("paginaActual", pagina);
                request.setAttribute("totalPaginas", totalPaginas);
                request.setAttribute("busqueda", busqueda);
                request.setAttribute("tipoCuenta", tipoCuenta);
                request.setAttribute("estadoFiltro", estadoParam);

                request.getRequestDispatcher("AdminCuentas.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al listar cuentas: " + e.getMessage());
                request.getRequestDispatcher("AdminCuentas.jsp").forward(request, response);
            }
        }
    }
    private int obtenerPagina(HttpServletRequest request) {
        String paginaParam = request.getParameter("pagina");
        try {
            return paginaParam != null ? Integer.parseInt(paginaParam) : 1;
        } catch (NumberFormatException e) {
            return 1;
        }
    }
}