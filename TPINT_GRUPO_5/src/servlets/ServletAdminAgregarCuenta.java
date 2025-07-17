package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuentas;
import negocioImpl.CuentasNegocioImpl;

@WebServlet("/ServletAdminAgregarCuenta")
public class ServletAdminAgregarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ServletAdminAgregarCuenta() {
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnCrearCuenta")!= null) {
	
			int idCliente = Integer.parseInt(request.getParameter("id_cliente"));
			String idTipoCuenta = (request.getParameter("id_tipo_cuenta"));
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
			    request.getSession().setAttribute("mensaje", "Cuenta creada exitosamente.");
			} else {
			    request.getSession().setAttribute("error", "No se pudo crear la cuenta.");
			}
			response.sendRedirect("ListarCuentasServlet");

		}
	}

}
