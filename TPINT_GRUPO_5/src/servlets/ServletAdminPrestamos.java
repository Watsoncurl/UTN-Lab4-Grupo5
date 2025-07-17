package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.PrestamosNegocio;
import negocioImpl.PrestamosNegocioImpl;
import entidades.Prestamos;

@WebServlet("/ServletAdminPrestamos")
public class ServletAdminPrestamos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instancia de negocio para usar métodos de instancia
    private PrestamosNegocio negocio = new PrestamosNegocioImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String busqueda = request.getParameter("busqueda");
        if (busqueda == null) busqueda = "";

        String estado = request.getParameter("estado");
        if (estado == null) estado = "";

        String paginaParam = request.getParameter("pagina");
        int pagina = 1;
        int cantidadPorPagina = 10;

        if (paginaParam != null) {
            try {
                pagina = Integer.parseInt(paginaParam);
            } catch (NumberFormatException e) {
                pagina = 1;
            }
        }

        List<Prestamos> listaPrestamos = negocio.obtenerPrestamosPaginados(busqueda, estado, pagina, cantidadPorPagina);
        int totalPrestamos = negocio.contarPrestamos(busqueda, estado);
        int totalPaginas = (int) Math.ceil((double) totalPrestamos / cantidadPorPagina);

        request.setAttribute("listaPrestamos", listaPrestamos);
        request.setAttribute("totalPaginas", totalPaginas);
        request.setAttribute("paginaActual", pagina);
        request.setAttribute("busqueda", busqueda);
        request.setAttribute("estado", estado);

        // Evitar cache en la respuesta HTTP
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.

        request.getRequestDispatcher("/AdminPrestamos.jsp").forward(request, response);
    }

}
