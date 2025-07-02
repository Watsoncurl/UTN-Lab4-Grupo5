package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuentas;
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
            // 1. Obtener número de página desde el request
            int pagina = obtenerPagina(request);

            // 2. Calcular índice de inicio para la consulta
            int inicio = (pagina - 1) * REGISTROS_POR_PAGINA;

            // 3. Obtener lista paginada de cuentas
            List<Cuentas> listaCuentas = cuentasNegocio.listarPaginadas(inicio, REGISTROS_POR_PAGINA);

            // 4. Calcular total de páginas
            int totalCuentas = cuentasNegocio.contarTotalCuentas();
            int totalPaginas = (int) Math.ceil((double) totalCuentas / REGISTROS_POR_PAGINA);

            // 5. Establecer atributos
            request.setAttribute("listaCuentas", listaCuentas);
            request.setAttribute("paginaActual", pagina);
            request.setAttribute("totalPaginas", totalPaginas);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al listar cuentas: " + e.getMessage());
        }

        // 6. Redirigir a la vista
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
