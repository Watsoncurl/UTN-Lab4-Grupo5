package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.Cliente;
import negocio.ClientesNegocio;
import negocioImpl.ClientesNegocioImpl;

@WebServlet("/ListarClientesServlet")
public class ServletCliente extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int REGISTROS_POR_PAGINA = 7;
    private ClientesNegocio clienteNegocio;
    
    @Override
    public void init() throws ServletException {
        super.init();
        clienteNegocio = new ClientesNegocioImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // 1. Obtener parámetros de paginación
            int pagina = obtenerPagina(request);
            
            // 2. Calcular inicio para la consulta
            int inicio = (pagina - 1) * REGISTROS_POR_PAGINA;
            
            // 3. Obtener clientes paginados
            List<Cliente> listaClientes = clienteNegocio.listarPaginados(inicio, REGISTROS_POR_PAGINA);
            
            // 4. Obtener total de clientes y calcular páginas
            int totalClientes = clienteNegocio.contarTotalClientes();
            int totalPaginas = (int) Math.ceil((double) totalClientes / REGISTROS_POR_PAGINA);
            
            // 5. Establecer atributos para la vista
            request.setAttribute("listaClientes", listaClientes);
            request.setAttribute("paginaActual", pagina);
            request.setAttribute("totalPaginas", totalPaginas);
            
        } catch (Exception e) {
            request.setAttribute("error", "Error al listar clientes: " + e.getMessage());
            e.printStackTrace();
        }
        
        // 6. Redirigir a la vista
        request.getRequestDispatcher("AdminClientes.jsp").forward(request, response);
    }
    
    // Método auxiliar para obtener el número de página
    private int obtenerPagina(HttpServletRequest request) {
        String paginaParam = request.getParameter("pagina");
        try {
            return paginaParam != null ? Integer.parseInt(paginaParam) : 1;
        } catch (NumberFormatException e) {
            return 1; // Valor por defecto si hay error en el parámetro
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        String idParam = request.getParameter("id");

        if ("eliminar".equals(accion) && idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                boolean eliminado = clienteNegocio.eliminar(id);
                
                if (eliminado) {
                    request.getSession().setAttribute("mensaje", "Cliente eliminado correctamente");
                } else {
                    request.getSession().setAttribute("error", "No se pudo eliminar el cliente");
                }
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("error", "ID de cliente inválido");
            } catch (Exception e) {
                request.getSession().setAttribute("error", "Error al eliminar cliente: " + e.getMessage());
            }
            

            String pagina = request.getParameter("paginaActual");
            String urlRedireccion = request.getContextPath() + "/ListarClientesServlet";
            if (pagina != null && !pagina.isEmpty()) {
                urlRedireccion += "?pagina=" + pagina;
            }
            response.sendRedirect(urlRedireccion);
        }
    }
}