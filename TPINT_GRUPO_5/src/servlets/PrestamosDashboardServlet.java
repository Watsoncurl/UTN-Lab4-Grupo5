package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entidades.Prestamos;
import negocio.PrestamosNegocio;
import negocioImpl.PrestamosNegocioImpl;

@WebServlet("/PrestamosDashboardServlet")
public class PrestamosDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PrestamosNegocio prestamosNegocio = new PrestamosNegocioImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer idCliente = (Integer) session.getAttribute("idCliente");

        // Si no hay idCliente en la sesi�n, redirige al login (o donde corresponda)
        if (idCliente == null) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        // Par�metros de paginaci�n y filtro (valores por defecto)
        String busqueda = request.getParameter("busqueda") != null ? request.getParameter("busqueda").trim() : "";
        String estado = request.getParameter("estado") != null ? request.getParameter("estado").trim() : "";
        int paginaActual = 1;
        int cantidadPorPagina = 10; // O el valor que uses en tu aplicaci�n

        // Obtener los par�metros de la petici�n (si se proporcionan)
        if (request.getParameter("pagina") != null) {
            try {
                paginaActual = Integer.parseInt(request.getParameter("pagina"));
            } catch (NumberFormatException e) {
                // Si no es un n�mero v�lido, usa el valor por defecto
                paginaActual = 1;
            }
        }

        // Obtener la lista de pr�stamos paginada y filtrada
        List<Prestamos> prestamos = prestamosNegocio.obtenerPrestamosPaginadosPorCliente(
                idCliente, busqueda, estado, paginaActual, cantidadPorPagina);

        // Contar el total de registros (para la paginaci�n)
        int totalRegistros = prestamosNegocio.contarPrestamosPorCliente(idCliente, busqueda, estado);
        int totalPaginas = (int) Math.ceil((double) totalRegistros / cantidadPorPagina);

        // Establecer los atributos en la petici�n
        request.setAttribute("listaPrestamos", prestamos);
        request.setAttribute("paginaActual", paginaActual);
        request.setAttribute("totalPaginas", totalPaginas);
        request.setAttribute("busqueda", busqueda);
        request.setAttribute("estado", estado);

        // Reenviar la petici�n al JSP
        request.getRequestDispatcher("/PrestamoDashboard.jsp").forward(request, response);
    }
}