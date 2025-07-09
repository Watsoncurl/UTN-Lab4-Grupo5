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
        	
        	String busqueda = request.getParameter("busqueda");
            String estadoFiltro = request.getParameter("estado"); 
            String sexoFiltro = request.getParameter("sexo");     
            
            
            int pagina = obtenerPagina(request);
            
            int inicio = (pagina - 1) * REGISTROS_POR_PAGINA;
            
            List<Cliente> listaClientes = clienteNegocio.listarPaginados(inicio, REGISTROS_POR_PAGINA);
            
            int totalClientes = clienteNegocio.contarTotalClientes();
            
            if ((busqueda != null && !busqueda.trim().isEmpty()) || (estadoFiltro != null && !estadoFiltro.isEmpty()) || (sexoFiltro != null && !sexoFiltro.isEmpty())) {
                listaClientes = clienteNegocio.filtrarPorBusquedaEstadoYSexo(busqueda, estadoFiltro, sexoFiltro);
                totalClientes = listaClientes.size();
                pagina = 1; 
            } else {
                listaClientes = clienteNegocio.listarPaginados(inicio, REGISTROS_POR_PAGINA);
                totalClientes = clienteNegocio.contarTotalClientes();
            }
            
            int totalPaginas = (int) Math.ceil((double) totalClientes / REGISTROS_POR_PAGINA);
            
            request.setAttribute("listaClientes", listaClientes);
            request.setAttribute("paginaActual", pagina);
            request.setAttribute("totalPaginas", totalPaginas);
            request.setAttribute("estadoFiltro", estadoFiltro);
            request.setAttribute("sexoFiltro", sexoFiltro);
            
        } catch (Exception e) {
            request.setAttribute("error", "Error al listar clientes: " + e.getMessage());
            e.printStackTrace();
        }
        
        request.getRequestDispatcher("AdminClientes.jsp").forward(request, response);
    }
    
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