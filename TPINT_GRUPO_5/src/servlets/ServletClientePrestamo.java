package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import negocio.PrestamosNegocio;
import negocioImpl.PrestamosNegocioImpl;
import entidades.Prestamos;

@WebServlet("/ServletClientePrestamo")
public class ServletClientePrestamo extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PrestamosNegocio negocio = new PrestamosNegocioImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer idCliente = (Integer) session.getAttribute("idCliente");

        if (idCliente == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String busqueda = request.getParameter("busqueda") != null ? request.getParameter("busqueda").trim() : "";
        String estado = request.getParameter("estado") != null ? request.getParameter("estado").trim() : "";
        int paginaActual = 1;
        int cantidadPorPagina = 10;

        if (request.getParameter("pagina") != null) {
            try {
                paginaActual = Integer.parseInt(request.getParameter("pagina"));
            } catch (NumberFormatException e) {
                paginaActual = 1;
            }
        }

        // Obtener la lista de préstamos
        List<Prestamos> prestamos = negocio.obtenerPrestamosPaginadosPorCliente(idCliente, busqueda, estado, paginaActual, cantidadPorPagina);

        // Verificar si se pagó una cuota exitosamente
        String pagoExitoso = request.getParameter("pagoExitoso");

        // Si se pagó una cuota, recargar la lista de préstamos
        if ("true".equals(pagoExitoso)) {
            prestamos = negocio.obtenerPrestamosPaginadosPorCliente(idCliente, busqueda, estado, paginaActual, cantidadPorPagina);
        }

        int totalRegistros = negocio.contarPrestamosPorCliente(idCliente, busqueda, estado);
        int totalPaginas = (int) Math.ceil((double) totalRegistros / cantidadPorPagina);

        request.setAttribute("listaPrestamos", prestamos);
        request.setAttribute("paginaActual", paginaActual);
        request.setAttribute("totalPaginas", totalPaginas);
        request.setAttribute("busqueda", busqueda);
        request.setAttribute("estado", estado);

        request.getRequestDispatcher("/ClientePrestamos.jsp").forward(request, response);
    }
}