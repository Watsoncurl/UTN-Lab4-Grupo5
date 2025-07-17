package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import datos.CuentasDao;
import datos.PrestamosDao;
import datosImpl.CuentasDaoImpl;
import datosImpl.PrestamosDaoImpl;
import entidades.Prestamos;
import negocio.CuentasNegocio;
import negocio.PrestamosNegocio;
import negocioImpl.CuentasNegocioImpl;
import negocioImpl.PrestamosNegocioImpl;

@WebServlet("/PrestamoServlet")
public class PrestamoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CuentasNegocio cuentasNegocio;
    private PrestamosNegocio prestamoNegocio;

    public PrestamoServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        CuentasDao cuentasDao = new CuentasDaoImpl();
        PrestamosDao prestamoDao = new PrestamosDaoImpl();
        cuentasNegocio = new CuentasNegocioImpl(cuentasDao);
        prestamoNegocio = new PrestamosNegocioImpl(prestamoDao, cuentasNegocio);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("idCliente") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        int idCliente = (int) session.getAttribute("idCliente");
        String idCuentaStr = request.getParameter("idCuenta");
        String importeStr = request.getParameter("importe");
        String plazoMesesStr = request.getParameter("plazoMeses");

        if (idCuentaStr == null || idCuentaStr.isEmpty() || importeStr == null || importeStr.isEmpty() || plazoMesesStr == null || plazoMesesStr.isEmpty()) {
            request.setAttribute("error", "Faltan datos obligatorios.");
            request.getRequestDispatcher("ClientePrestamos_1.jsp").forward(request, response);
            return;
        }

        try {
            int idCuenta = Integer.parseInt(idCuentaStr);
            double importe = Double.parseDouble(importeStr);
            int plazoMeses = Integer.parseInt(plazoMesesStr);

            if (importe <= 0) {
                request.setAttribute("error", "El importe debe ser mayor a cero.");
                request.getRequestDispatcher("ClientePrestamos_1.jsp").forward(request, response);
                return;
            }

            if (plazoMeses <= 0) {
                request.setAttribute("error", "El plazo en meses debe ser mayor a cero.");
                request.getRequestDispatcher("ClientePrestamos_1.jsp").forward(request, response);
                return;
            }

         
            Prestamos prestamo = new Prestamos();
            prestamo.setIdCliente(idCliente);
            prestamo.setIdCuenta(idCuenta);
            prestamo.setImporte(importe);
            prestamo.setPlazoMeses(plazoMeses);
            prestamo.setEstado("pendiente"); 
            prestamo.setCuotasPendientes(plazoMeses);
            prestamo.setCuotasPagas(0);


            boolean exito = prestamoNegocio.solicitarPrestamo(prestamo);

            if (exito) {
                request.setAttribute("mensaje", "Solicitud de prestamo enviada correctamente.");
                request.getRequestDispatcher("SolicitudPrestamo.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "No se pudo completar la solicitud de prestamo.");
                request.getRequestDispatcher("ClientePrestamos_1.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error en el formato de los datos numericos.");
            request.getRequestDispatcher("ClientePrestamos_1.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("ClientePrestamos_1.jsp").forward(request, response);
        }
    }
}