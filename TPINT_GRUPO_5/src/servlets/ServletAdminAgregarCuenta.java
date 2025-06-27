package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.ClientesDaoImpl;
import datosImpl.Conexion;
import entidades.Cliente;
import negocioImpl.ClientesNegocioImpl;
import negocioImpl.CuentasNegocioImpl;
import entidades.Cuentas;
import entidades.tipo_cuenta;

/**
 * Servlet implementation class ServletAdminAgregarCuenta
 */
@WebServlet("/ServletAdminAgregarCuenta")
public class ServletAdminAgregarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletAdminAgregarCuenta() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnCrearCuenta")!= null) {
			/// Parametros de la pagina web
			int idCliente = Integer.parseInt(request.getParameter("id_cliente"));
			String idTipoCuenta = Integer.parseInt(request.getParameter("id_tipo_cuenta"));
			String nroCuenta = request.getParameter("nro_cuenta");
			String cbu = request.getParameter("cbu");
			

			
			Cuentas cuenta = new Cuentas();
			cuenta.setId_cliente(idCliente);
			cuenta.setTipo_cuenta(idTipoCuenta);
			cuenta.setNro_cuenta(nroCuenta);
			cuenta.setCbu(cbu);
			cuenta.setSaldo(10000);
			cuenta.setEstado(true);
			
			boolean existo = new CuentasNegocioImpl().crearCuenta(cuenta);
			
			if(existo) {
				response.sendRedirect("AdminCuenta.jsp");
			} else {
				request.setAttribute("error", "No se pudo crear la cuenta.");
	            request.getRequestDispatcher("AdminAgregarCuenta.jsp").forward(request, response);
			}
		}
	}

}
