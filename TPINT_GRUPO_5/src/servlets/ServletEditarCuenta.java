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
import entidades.tipo_cuenta;
import negocio.CuentasNegocio;
import negocio.TiposCuentaNegocio;
import negocioImpl.CuentasNegocioImpl;
import negocioImpl.TipoCuentaNegocioImpl;
import entidades.Cliente;
import negocio.ClientesNegocio;
import negocioImpl.ClientesNegocioImpl;

@WebServlet("/ServletEditarCuenta")
public class ServletEditarCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentasNegocio cuentasNegocio = new CuentasNegocioImpl();
    private TiposCuentaNegocio tiposCuentaNegocio = new TipoCuentaNegocioImpl();
    private ClientesNegocio clientesNegocio = new ClientesNegocioImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String modo = request.getParameter("modo");
        String nroCuenta = request.getParameter("nroCuenta");
        int idCuenta = 0;

        if (nroCuenta != null && !nroCuenta.isEmpty()) {
            try {
                idCuenta = cuentasNegocio.obtenerIdCuentaPorNroCuenta(nroCuenta);
            } catch (NumberFormatException e) {
                session.setAttribute("error", "Número de cuenta no válido.");
                response.sendRedirect("ListarCuentasServlet");
                return;
            }
        } else {
            session.setAttribute("error", "Número de cuenta no proporcionado.");
            response.sendRedirect("ListarCuentasServlet");
            return;
        }

        Cuentas cuenta = cuentasNegocio.obtenerCuentaPorId(idCuenta);
        if (cuenta == null) {
            session.setAttribute("error", "No se encontró la cuenta con el número: " + nroCuenta);
            response.sendRedirect("ListarCuentasServlet");
            return;
        }

        List<tipo_cuenta> listaTiposCuenta = tiposCuentaNegocio.listarTodos();
        request.setAttribute("listaTiposCuenta", listaTiposCuenta);

        List<Cliente> listaClientes = clientesNegocio.listarTodos();
        request.setAttribute("listaClientes", listaClientes);

        request.setAttribute("cuenta", cuenta);
        request.setAttribute("modo", modo);
        request.getRequestDispatcher("AdminEditarCuenta.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
        String nroCuenta = request.getParameter("nroCuenta");
        String cbu = request.getParameter("cbu");
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String tipoCuenta = request.getParameter("tipoCuenta");
        double saldo = Double.parseDouble(request.getParameter("saldo")); 

        Cuentas cuenta = new Cuentas();
        cuenta.setId_cuenta(idCuenta);
        cuenta.setNro_cuenta(nroCuenta);
        cuenta.setCbu(cbu);
        cuenta.setId_cliente(idCliente);
        cuenta.setTipo_cuenta(tipoCuenta);
        cuenta.setSaldo(saldo); 

        boolean actualizado = cuentasNegocio.actualizar(cuenta);

        if (actualizado) {
            session.setAttribute("mensaje", "Cuenta actualizada correctamente.");
        } else {
            session.setAttribute("error", "Error al actualizar la cuenta.  Verifique los datos.");
        }

        response.sendRedirect("ListarCuentasServlet");
    }
}