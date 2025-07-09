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

/**
 * Servlet implementation class ServletIngresarCuenta
 */
@WebServlet("/ServletIngresarCuenta")
public class ServletIngresarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentasNegocio cuentasNegocio;

	public ServletIngresarCuenta() {
		super();
		cuentasNegocio = new CuentasNegocioImpl();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nroCuenta = request.getParameter("nroCuenta");
		System.out.println("nroCuenta: " + nroCuenta);

		Cuentas Cuenta = cuentasNegocio.obtenerPorNroCuenta(nroCuenta);

		HttpSession session = request.getSession();
		session.setAttribute("Cuenta", Cuenta);

		request.getRequestDispatcher("ClienteIngresarCuenta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
