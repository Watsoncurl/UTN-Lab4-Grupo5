package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Movimientos;

import filtros.MovimientosFiltros;
import negocio.CuentasNegocio;
import negocio.MovimientosNegocio;

import negocioImpl.CuentasNegocioImpl;
import negocioImpl.MovimientosNegocioImpl;
import entidades.Cuentas;


@WebServlet("/ServletAdminMovimientos")
public class ServletAdminMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovimientosNegocio movimientosNegocio;
	private CuentasNegocio cuentasNegocio;

	
	public ServletAdminMovimientos() {
		super();
		movimientosNegocio = new MovimientosNegocioImpl();
		cuentasNegocio = new CuentasNegocioImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    List<Movimientos> listaMovimientos = new ArrayList<>();
	    List<Cuentas> cuentas = cuentasNegocio.listarTodas();
	    request.setAttribute("cuentas", cuentas);

	    String busqueda = request.getParameter("busqueda");
	    String tipoMovimiento = request.getParameter("tipoMovimiento");
	    String fechaDesde = request.getParameter("desdeDate");
	    String fechaHasta = request.getParameter("hastaDate");
	    String accion = request.getParameter("accion");

	    if ("buscar".equals(accion)) {
	        MovimientosFiltros filtro = new MovimientosFiltros();

	        filtro.setBusqueda(busqueda); // Si MovimientosFiltros tiene este campo, sino adaptá.

	        filtro.setTipoMovimiento(tipoMovimiento);
	        filtro.setFechaDesde(fechaDesde);
	        filtro.setFechaHasta(fechaHasta);

	        listaMovimientos = movimientosNegocio.ListarTodosFiltrados(filtro);
	        System.out.println("Aplicando filtros...");
	    } else {
	        listaMovimientos = movimientosNegocio.ListarTodos();
	        System.out.println("Sin filtros, listado completo.");
	    }

	    if (listaMovimientos != null) {
	        for (Movimientos m : listaMovimientos) {
	            System.out.println("Movimiento: " + m.getFecha() + " | Cuenta: " + m.getIdCuenta() + " | " + m.getConcepto());
	        }
	    }

	    request.setAttribute("listaMovimientos", listaMovimientos);
	    request.setAttribute("busqueda", busqueda); // Para que el valor quede en el input
	    request.getRequestDispatcher("AdminMovimientos.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
