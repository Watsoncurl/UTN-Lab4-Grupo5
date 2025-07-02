package servlets;

import datosImpl.CuentasDaoImpl;
import datos.CuentasDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EliminarCuentaServlet")
public class EliminarCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentasDao cuentaDAO = new CuentasDaoImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nroCuenta = request.getParameter("nro_cuenta");

        try {
            boolean eliminado = cuentaDAO.eliminarCuenta(nroCuenta);
            if (eliminado) {
                System.out.println("Cuenta " + nroCuenta + " eliminada correctamente.");
            } else {
                System.out.println("No se encontró la cuenta " + nroCuenta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ListarCuentasServlet");
    }
}

