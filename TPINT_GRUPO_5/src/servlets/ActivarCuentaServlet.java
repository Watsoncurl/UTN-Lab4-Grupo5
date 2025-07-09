package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import negocio.CuentasNegocio;
import negocioImpl.CuentasNegocioImpl;

@WebServlet("/ActivarCuentaServlet")
public class ActivarCuentaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentasNegocio cuentasNegocio = new CuentasNegocioImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nroCuenta = request.getParameter("nro_cuenta");

        if (nroCuenta != null && !nroCuenta.isEmpty()) {
            boolean exito = cuentasNegocio.activarCuentaPorNroCuenta(nroCuenta);

            if (exito) {
                request.getSession().setAttribute("mensaje", "Cuenta activada exitosamente.");
            } else {
                request.getSession().setAttribute("error", "No se pudo activar la cuenta.");
            }
        } else {
            request.getSession().setAttribute("error", "Número de cuenta inválido.");
        }

        response.sendRedirect("ListarCuentasServlet?pagina=1");
    }
}
