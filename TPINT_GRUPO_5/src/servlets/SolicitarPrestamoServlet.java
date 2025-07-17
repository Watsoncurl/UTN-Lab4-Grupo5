package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import datos.CuentasDao;
import datosImpl.CuentasDaoImpl;
import entidades.Cuentas;
import negocio.CuentasNegocio;
import negocioImpl.CuentasNegocioImpl;

@WebServlet("/SolicitarPrestamoServlet")
public class SolicitarPrestamoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CuentasNegocio cuentasNegocio;

    public SolicitarPrestamoServlet() {
        super();
        CuentasDao cuentasDao = new CuentasDaoImpl();
        cuentasNegocio = new CuentasNegocioImpl(cuentasDao);
    }

    @Override
    public void init() throws ServletException {
        CuentasDao cuentasDao = new CuentasDaoImpl();
        cuentasNegocio = new CuentasNegocioImpl(cuentasDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("idCliente") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        int idCliente = (int) session.getAttribute("idCliente");

        try {
            List<Cuentas> cuentas = cuentasNegocio.obtenerCuentasPorIdCliente(idCliente);
            request.setAttribute("cuentas", cuentas);
            request.getRequestDispatcher("ClientePrestamos_1.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar el formulario de prestamo: " + e.getMessage());
            request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}