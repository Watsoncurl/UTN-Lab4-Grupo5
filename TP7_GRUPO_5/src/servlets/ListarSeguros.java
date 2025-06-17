package servlets;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import NegocioImpl.SeguroNegocioImpl;
import entidad.Seguro;

@WebServlet("/ListarSeguros")
public class ListarSeguros extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListarSeguros() {
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

			SeguroNegocioImpl negocio = new SeguroNegocioImpl();
			
			ArrayList<Seguro> listaSeguros = (ArrayList<Seguro>) negocio.obtenerTodos();
			
			request.setAttribute("seguros", listaSeguros);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
			rd.forward(request, response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
