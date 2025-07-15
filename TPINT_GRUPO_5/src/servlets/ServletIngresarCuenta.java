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
import entidades.Movimientos;
import filtros.MovimientosFiltros;
import negocio.CuentasNegocio;
import negocio.MovimientosNegocio;
import negocioImpl.CuentasNegocioImpl;
import negocioImpl.MovimientosNegocioImpl;

/**
 * Servlet implementation class ServletIngresarCuenta
 */
@WebServlet("/ServletIngresarCuenta")
public class ServletIngresarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentasNegocio cuentasNegocio;
	private MovimientosNegocio movimientosNegocio;
	
	public ServletIngresarCuenta() {
		super();
		cuentasNegocio = new CuentasNegocioImpl();
		movimientosNegocio = new MovimientosNegocioImpl();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    try {
	        String nroCuenta = request.getParameter("nroCuenta");
	        if (nroCuenta == null || nroCuenta.isEmpty()) {
	           
	            response.sendRedirect("error.jsp");
	            return;
	        }

	        Cuentas cuenta = cuentasNegocio.obtenerPorNroCuenta(nroCuenta);

	        MovimientosFiltros filtro = new MovimientosFiltros();
	        filtro.setNroCuenta(nroCuenta); 

	
	        String tipoCuenta = request.getParameter("cuenta");
	        if (tipoCuenta != null && !tipoCuenta.isEmpty()) filtro.setTipoCuenta(tipoCuenta);

	        String tipoMovimiento = request.getParameter("tipoMovimiento");
	        if (tipoMovimiento != null && !tipoMovimiento.isEmpty()) filtro.setTipoMovimiento(tipoMovimiento);

	        String fechaDesde = request.getParameter("desdeDate");
	        if (fechaDesde != null && !fechaDesde.isEmpty()) filtro.setFechaDesde(fechaDesde);

	        String fechaHasta = request.getParameter("hastaDate");
	        if (fechaHasta != null && !fechaHasta.isEmpty()) filtro.setFechaHasta(fechaHasta);

	        List<Movimientos> movimientosFiltrados = movimientosNegocio.obtenerPorCuenta(filtro.getNroCuenta());

	        request.setAttribute("Cuenta", cuenta);
	        request.setAttribute("listaMovimientos", movimientosFiltrados);

	        request.getRequestDispatcher("ClienteIngresarCuenta.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "Error al cargar movimientos: " + e.getMessage());
	        request.getRequestDispatcher("ClienteIngresarCuenta.jsp").forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
