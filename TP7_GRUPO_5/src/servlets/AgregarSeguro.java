package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import NegocioImpl.SeguroNegocioImpl;
import NegocioImpl.TipoSeguroNegocioImpl;
import entidad.Seguro;
import entidad.TipoSeguro;

@WebServlet("/AgregarSeguro")
public class AgregarSeguro extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    AgregarSeguro(){
    	
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

    	TipoSeguroNegocioImpl negocio = new TipoSeguroNegocioImpl();
		
		ArrayList<TipoSeguro> listaTipos = (ArrayList<TipoSeguro>) negocio.obtenerTipos();
		
		request.setAttribute("listaTipos", listaTipos);
		
		RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguros.jsp");
		rd.forward(request, response);
	
	
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("btnAceptar") != null) {
            try {
                // Obtener parámetros como String
                //String idStr = request.getParameter("IdSeguro");
                String descripcion = request.getParameter("Descripcion");
                String tipoStr = request.getParameter("Tipo");
                String costoContStr = request.getParameter("CostoCont");
                String costoMaxStr = request.getParameter("CostoMax");

                // Validación básica
                if (
                		//idStr == null || idStr.trim().isEmpty() ||
                    descripcion == null || descripcion.trim().isEmpty() ||
                    tipoStr == null || tipoStr.trim().isEmpty() ||
                    costoContStr == null || costoContStr.trim().isEmpty() ||
                    costoMaxStr == null || costoMaxStr.trim().isEmpty()) {

                    request.setAttribute("mensaje", "Todos los campos son obligatorios.");
                } else {
                    // Parseo seguro
                    //int id = Integer.parseInt(idStr.trim());
                    int idTipo = Integer.parseInt(tipoStr.trim());
                    double costoCont = Double.parseDouble(costoContStr.trim());
                    double costoMax = Double.parseDouble(costoMaxStr.trim());

                    Seguro s = new Seguro();
                    //s.setIdSeguro(id);
                    s.setDescripcion(descripcion.trim());
                    s.setIdTipoSeguro(idTipo);
                    s.setCostoContratacion(costoCont);
                    s.setCostoAsegurado(costoMax);

                    SeguroNegocioImpl alta = new SeguroNegocioImpl();
                    if (alta.agregar(s)) {
                        request.setAttribute("mensaje", "✅ Seguro agregado correctamente.");
                    } else {
                        request.setAttribute("mensaje", "❌ No se pudo agregar el seguro.");
                    }
                }

            } catch (NumberFormatException e) {
                request.setAttribute("mensaje", "⚠ Error: ingrese valores numéricos válidos.");
            } catch (Exception e) {
                request.setAttribute("mensaje", "⚠ Error inesperado: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        doGet(request, response);
        request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
    }
}