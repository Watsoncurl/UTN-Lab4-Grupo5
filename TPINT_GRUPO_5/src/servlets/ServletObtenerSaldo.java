package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.CuentasNegocio;
import negocioImpl.CuentasNegocioImpl;
import datos.CuentasDao;
import datosImpl.CuentasDaoImpl;
import entidades.Cuentas;
@WebServlet("/ServletObtenerSaldo")
public class ServletObtenerSaldo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentasNegocio cuentasNegocio;

    @Override
    public void init() throws ServletException {
        CuentasDao cuentasDao = new CuentasDaoImpl();
        cuentasNegocio = new CuentasNegocioImpl(cuentasDao);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCuentaStr = request.getParameter("idCuenta");
        if (idCuentaStr != null && !idCuentaStr.isEmpty()) {
            try {
                int idCuenta = Integer.parseInt(idCuentaStr);
                Cuentas cuenta = cuentasNegocio.obtenerCuentaPorId(idCuenta); 
                if (cuenta != null) {
                    response.getWriter().write(String.valueOf(cuenta.getSaldo()));
                } else {
                    response.getWriter().write("0.00"); 
                }
            } catch (NumberFormatException e) {
                response.getWriter().write("Error");
            }
        } else {
            response.getWriter().write("0.00"); 
        }
    }
}