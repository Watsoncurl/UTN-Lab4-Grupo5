package servlets;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import entidades.Prestamos;
import entidades.Cuentas;
import negocio.PrestamosNegocio;
import negocioImpl.PrestamosNegocioImpl;
import negocio.CuentasNegocio;
import negocioImpl.CuentasNegocioImpl;

@WebServlet("/ServletAdminPrestamosDetalle")
public class ServletAdminPrestamosDetalle extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PrestamosNegocio prestamosNegocio;
    private CuentasNegocio cuentasNegocio;

    @Override
    public void init() throws ServletException {
        super.init();
        prestamosNegocio = new PrestamosNegocioImpl();
        cuentasNegocio = new CuentasNegocioImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("ServletAdminPrestamos");
            return;
        }

        int idPrestamo;
        try {
            idPrestamo = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("ServletAdminPrestamos");
            return;
        }

        Prestamos prestamo = prestamosNegocio.obtenerPrestamoPorId(idPrestamo);
        if (prestamo == null) {
            response.sendRedirect("ServletAdminPrestamos");
            return;
        }

        String busqueda = request.getParameter("busqueda");
        String estado = request.getParameter("estado");
        String pagina = request.getParameter("pagina");

        request.setAttribute("busqueda", busqueda);
        request.setAttribute("estado", estado);
        request.setAttribute("pagina", pagina);
        request.setAttribute("prestamo", prestamo);

        request.getRequestDispatcher("AdminPrestamosDetalle.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String idParam = request.getParameter("id");
        String busqueda = request.getParameter("busqueda");
        String estado = request.getParameter("estado");
        String pagina = request.getParameter("pagina");

        if (accion == null || idParam == null) {
            response.sendRedirect("ServletAdminPrestamos");
            return;
        }

        int idPrestamo;
        try {
            idPrestamo = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("ServletAdminPrestamos");
            return;
        }

        String nuevoEstado = null;
        if ("aceptar".equalsIgnoreCase(accion)) {
            nuevoEstado = "aprobado";
        } else if ("rechazar".equalsIgnoreCase(accion)) {
            nuevoEstado = "rechazado";
        }

        if (nuevoEstado != null) {
            boolean exito = prestamosNegocio.cambiarEstadoPrestamo(idPrestamo, nuevoEstado);
            if (!exito) {
                request.setAttribute("error", "Error al actualizar estado del pr�stamo");
                request.setAttribute("prestamo", prestamosNegocio.obtenerPrestamoPorId(idPrestamo));
                request.getRequestDispatcher("AdminPrestamosDetalle.jsp").forward(request, response);
                return;
            }

            // Si el pr�stamo se aprueba, actualiza el saldo de la cuenta
            if ("aprobado".equalsIgnoreCase(nuevoEstado)) {
                actualizarSaldoCuentaPrestamo(idPrestamo, request, response);
            }
        }

        String redirectUrl = "ServletAdminPrestamos?pagina=1";
        if (pagina != null && !pagina.isEmpty()) {
            redirectUrl = "ServletAdminPrestamos?pagina=" + pagina;
        }
        if (busqueda != null && !busqueda.isEmpty()) {
            redirectUrl += "&busqueda=" + URLEncoder.encode(busqueda, "UTF-8");
        }
        if (estado != null && !estado.isEmpty()) {
            redirectUrl += "&estado=" + URLEncoder.encode(estado, "UTF-8");
        }

        response.sendRedirect(redirectUrl);
    }

    private void actualizarSaldoCuentaPrestamo(int idPrestamo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("actualizarSaldoCuentaPrestamo");
        Prestamos prestamo = prestamosNegocio.obtenerPrestamoPorId(idPrestamo);

        if (prestamo != null) {
            int idCuenta = prestamo.getIdCuenta();
            double importePrestamo = prestamo.getImporte();
            System.out.println("ID del pr�stamo: " + idPrestamo);
            System.out.println("ID de la cuenta: " + idCuenta);
            System.out.println("Importe del pr�stamo: " + importePrestamo);

            Cuentas cuenta = cuentasNegocio.obtenerCuentaPorId(idCuenta); // Obtiene la cuenta del negocio
           if (cuenta != null) {
                double saldoActual = cuenta.getSaldo();
                double nuevoSaldo = saldoActual + importePrestamo;
                System.out.println("Saldo actual de la cuenta: " + saldoActual);
                System.out.println("Nuevo saldo de la cuenta: " + nuevoSaldo);

                cuenta.setSaldo(nuevoSaldo); // Actualiza el saldo en la entidad Cuenta

                boolean exito = cuentasNegocio.cambiaSaldo(cuenta); // Actualiza la cuenta en la base de datos
                 System.out.println("�xito al actualizar el saldo: " + exito);

                if (!exito) {
                    request.setAttribute("error", "Error al actualizar el saldo de la cuenta.");
                    request.setAttribute("prestamo", prestamosNegocio.obtenerPrestamoPorId(idPrestamo));
                    request.getRequestDispatcher("AdminPrestamosDetalle.jsp").forward(request, response);
                    return;
                } else {
                    // Puedes agregar un mensaje de �xito si lo deseas
                    request.setAttribute("mensaje", "El pr�stamo ha sido aprobado y el saldo de la cuenta ha sido actualizado.");
                }
            } else {
                request.setAttribute("error", "No se encontr� la cuenta asociada al pr�stamo.");
                request.setAttribute("prestamo", prestamosNegocio.obtenerPrestamoPorId(idPrestamo));
                request.getRequestDispatcher("AdminPrestamosDetalle.jsp").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("error", "No se encontr� el pr�stamo con ID: " + idPrestamo);
            request.getRequestDispatcher("AdminPrestamosDetalle.jsp").forward(request, response);
        }
    }
}