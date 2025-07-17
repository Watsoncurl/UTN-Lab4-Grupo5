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
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        if (cbu != null && !cbu.isEmpty()) {
            CuentasDao cuentasDao = new CuentasDaoImpl();
            Cuentas cuenta = cuentasDao.obtenerCuentaPorCBU(cbu);

            if (cuenta != null) {
                String nombreCompleto = cuenta.getCliente();
                out.println("<div class='alert alert-success mt-2' role='alert'><i class='bi bi-check-circle me-2'></i>CBU Válido - Destinatario: <b>" + nombreCompleto + "</b></div>");
            } else {
                out.println("<div class='alert alert-danger mt-2' role='alert'><i class='bi bi-exclamation-triangle me-2'></i>CBU No Encontrado</div>");
            }
        } else {
            out.println("<div class='alert alert-warning mt-2' role='alert'><i class='bi bi-exclamation-triangle me-2'></i>Ingrese un CBU</div>");
        }

        out.flush();
    }
}