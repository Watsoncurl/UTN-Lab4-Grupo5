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

    private PrestamosNegocio negocio = new PrestamosNegocioImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Parámetros de filtros
        String busqueda = request.getParameter("busqueda") != null ? request.getParameter("busqueda").trim() : "";
        String estado = request.getParameter("estado") != null ? request.getParameter("estado").trim() : "";

        // Paginación
        int paginaActual = 1;
        int cantidadPorPagina = 10;

        if (request.getParameter("pagina") != null) {
            try {
                paginaActual = Integer.parseInt(request.getParameter("pagina"));
            } catch (NumberFormatException e) {
                paginaActual = 1;
            }
        }

        // Datos desde la base
        List<Prestamos> prestamos = negocio.obtenerPrestamosPaginados(busqueda, estado, paginaActual, cantidadPorPagina);
        int totalRegistros = negocio.contarPrestamos(busqueda, estado);
        int totalPaginas = (int) Math.ceil((double) totalRegistros / cantidadPorPagina);

        // Enviar datos a la JSP
        request.setAttribute("listaPrestamos", prestamos);
        request.setAttribute("paginaActual", paginaActual);
        request.setAttribute("totalPaginas", totalPaginas);
        request.setAttribute("busqueda", busqueda);
        request.setAttribute("estado", estado);

        request.getRequestDispatcher("/AdminPrestamos.jsp").forward(request, response);
    }
}
