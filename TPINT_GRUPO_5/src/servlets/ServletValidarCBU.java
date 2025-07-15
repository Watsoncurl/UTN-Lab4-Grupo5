package servlets;

import datos.CuentasDao;
import datosImpl.CuentasDaoImpl;
import entidades.Cuentas;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ServletValidarCBU")
public class ServletValidarCBU extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cbu = request.getParameter("cbu");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (cbu != null && !cbu.isEmpty()) {
            CuentasDao cuentasDao = new CuentasDaoImpl();
            Cuentas cuenta = cuentasDao.obtenerCuentaPorCBU(cbu);

            if (cuenta != null) {
                String nombreCompleto = cuenta.getCliente();
                out.println("<span style='color:green;'>CBU Válido - Destinatario: " + nombreCompleto + "</span>");
            } else {
                out.println("<span style='color:red;'>CBU No Encontrado</span>");
            }
        } else {
            out.println("<span style='color:red;'>Ingrese un CBU</span>");
        }
    }
}