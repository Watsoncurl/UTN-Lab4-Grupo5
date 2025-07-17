package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entidades.Cuentas;
import negocio.CuentasNegocio;
import negocioImpl.CuentasNegocioImpl;
import datos.CuentasDao;
import datosImpl.CuentasDaoImpl;

@WebServlet("/ServletMisCuentas")
public class ServletMisCuentas extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentasNegocio cuentasNegocio;

    public ServletMisCuentas() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Se inicializa el CuentasNegocio con el CuentasDao.
        CuentasDao cuentasDao = new CuentasDaoImpl();
        cuentasNegocio = new CuentasNegocioImpl(cuentasDao);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("idCliente") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        int idCliente = (int) session.getAttribute("idCliente");

        try {
            // Obtener las cuentas RECIENTES desde la base de datos
            List<Cuentas> cuentas = cuentasNegocio.obtenerCuentasPorIdCliente(idCliente);
            request.setAttribute("cuentas", cuentas);

            request.getRequestDispatcher("ClienteMisCuentas.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar las cuentas: " + e.getMessage());
            request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}