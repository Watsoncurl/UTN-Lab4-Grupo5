package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        try {
            int pagina = obtenerPagina(request);
            int inicio = (pagina - 1) * REGISTROS_POR_PAGINA;

            // Obtener parámetros
            String busqueda = request.getParameter("busqueda");
            String tipoCuenta = request.getParameter("tipoCuenta");
            String estadoParam = request.getParameter("estado");

            // Crear objeto filtro
            CuentasFiltros filtro = new CuentasFiltros();
            filtro.setBusqueda((busqueda != null && !busqueda.isEmpty()) ? busqueda : null);
            filtro.setTipoCuenta((tipoCuenta != null && !tipoCuenta.isEmpty() && !"Todos los tipos".equals(tipoCuenta)) ? tipoCuenta : null);

            Boolean estadoFiltro = null;
            if ("Activas".equalsIgnoreCase(estadoParam)) {
                estadoFiltro = true;
            } else if ("Inactivas".equalsIgnoreCase(estadoParam)) {
                estadoFiltro = false;
            }

            filtro.setEstado(estadoFiltro);
            
            // Usar los métodos con filtro
            int totalCuentas = cuentasNegocio.contarFiltradas(filtro);
            int totalPaginas = (int) Math.ceil((double) totalCuentas / REGISTROS_POR_PAGINA);
            if (pagina < 1) pagina = 1;
            if (pagina > totalPaginas && totalPaginas != 0) pagina = totalPaginas;

            List<Cuentas> listaCuentas = cuentasNegocio.filtrar(filtro, inicio, REGISTROS_POR_PAGINA);

            // Pasar datos a la vista
            request.setAttribute("listaCuentas", listaCuentas);
            request.setAttribute("paginaActual", pagina);
            request.setAttribute("totalPaginas", totalPaginas);
            request.setAttribute("busqueda", filtro.getBusqueda());
            request.setAttribute("tipoCuenta", filtro.getTipoCuenta());
            request.setAttribute("estadoFiltro", filtro.getEstado());

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al listar cuentas: " + e.getMessage());
        }

        request.getRequestDispatcher("AdminCuentas.jsp").forward(request, response);
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
