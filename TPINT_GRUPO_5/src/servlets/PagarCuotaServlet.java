package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import negocio.PrestamosNegocio;
import negocioImpl.PrestamosNegocioImpl;

@WebServlet("/PagarCuotaServlet")
public class PagarCuotaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PrestamosNegocio prestamosNegocio = new PrestamosNegocioImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer idCliente = (Integer) session.getAttribute("idCliente");

        if (idCliente == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String idCuentaStr = request.getParameter("idCuenta");
        String idPrestamoStr = request.getParameter("idPrestamo");

        if (idCuentaStr == null || idCuentaStr.isEmpty() || idPrestamoStr == null || idPrestamoStr.isEmpty()) {
            request.setAttribute("error", "Datos incorrectos para realizar el pago.");
            response.sendRedirect("ServletClientePrestamo");
            return;
        }

        try {
            int idCuenta = Integer.parseInt(idCuentaStr);
            int idPrestamo = Integer.parseInt(idPrestamoStr);


            boolean pagado = prestamosNegocio.pagarCuota(idCuenta, idPrestamo);

            request.setAttribute("pagoExitoso", "true");

            response.sendRedirect("ServletClientePrestamo");

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error al convertir los datos numéricos.");
            response.sendRedirect("ServletClientePrestamo");
        } catch (Exception e) {
            request.setAttribute("error", "Error al pagar la cuota: " + e.getMessage());
            response.sendRedirect("ServletClientePrestamo");
        }
    }
}