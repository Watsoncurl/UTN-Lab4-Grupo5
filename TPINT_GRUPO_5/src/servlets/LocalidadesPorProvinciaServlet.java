package servlets;

import entidades.Localidad;
import negocio.LocalidadNegocio;
import negocioImpl.LocalidadNegocioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/LocalidadesPorProvincia")
public class LocalidadesPorProvinciaServlet extends HttpServlet {

    private LocalidadNegocio localidadNegocio = new LocalidadNegocioImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idProvinciaStr = request.getParameter("idProvincia");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (idProvinciaStr == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Falta idProvincia\"}");
            return;
        }

        try {
            int idProvincia = Integer.parseInt(idProvinciaStr);
            List<Localidad> localidades = localidadNegocio.obtenerPorProvincia(idProvincia);

            // Armamos JSON simple manualmente
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < localidades.size(); i++) {
                Localidad loc = localidades.get(i);
                json.append("{")
                    .append("\"idLocalidad\":").append(loc.getIdLocalidad()).append(",")
                    .append("\"nombre\":\"").append(loc.getNombre().replace("\"", "\\\"")).append("\"")
                    .append("}");
                if (i < localidades.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");

            response.getWriter().write(json.toString());

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"idProvincia inválido\"}");
        }
    }
}
