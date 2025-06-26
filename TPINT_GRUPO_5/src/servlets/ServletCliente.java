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
    private ClientesNegocio clienteNegocio;
    
    @Override
    public void init() throws ServletException {
        super.init();
        clienteNegocio = new ClientesNegocioImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            List<Cliente> listaClientes = clienteNegocio.listarTodos();
            request.setAttribute("listaClientes", listaClientes);
        } catch (Exception e) {
            request.setAttribute("error", "Error al listar clientes: " + e.getMessage());
        }
        
        request.getRequestDispatcher("AdminClientes.jsp").forward(request, response);
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
            
            response.sendRedirect(request.getContextPath() + "/ListarClientesServlet");
            return;
        }
        
        
    }
}