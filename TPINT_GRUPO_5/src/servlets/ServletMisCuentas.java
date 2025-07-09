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
 * Servlet implementation class ServletMisCuentas
 */
@WebServlet("/ServletMisCuentas")
public class ServletMisCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CuentasNegocio cuentasNegocio;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMisCuentas() {
        super();
        cuentasNegocio = new CuentasNegocioImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idCliente = (int) session.getAttribute("idCliente");
		List<Cuentas> cuentas = cuentasNegocio.listarCuentasPorCliente(idCliente);
		request.setAttribute("cuentas", cuentas);
		request.getRequestDispatcher("ClienteMisCuentas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
