package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import entidades.Cliente;
import filtros.ClientesFiltros;
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
        procesarSolicitud(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesarSolicitud(request, response);
    }

    private void procesarSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accion = request.getParameter("accion");

        // Recibe y procesa acciones (eliminar, activar)
        if ("eliminar".equals(accion) || "activar".equals(accion)) {
            int idCliente = Integer.parseInt(request.getParameter("id"));
            String mensaje = null;
            boolean exito = false;
            try {
                if ("eliminar".equals(accion)) {
                    clienteNegocio.eliminarCliente(idCliente);
                    mensaje = "Cliente eliminado correctamente.";
                    exito = true;
                } else if ("activar".equals(accion)) {
                    clienteNegocio.activarCliente(idCliente);
                    mensaje = "Cliente activado correctamente.";
                    exito = true;
                }

                if (exito) {
                    session.setAttribute("mensaje", mensaje);
                    session.removeAttribute("error");  // Limpia cualquier error anterior
                }
            } catch (Exception e) {
                session.setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
                e.printStackTrace();
            }
        }
            try {
              // Recarga la lista de clientes
              String busqueda = request.getParameter("busqueda");
              String estadoFiltro = request.getParameter("estado");
              String sexoFiltro = request.getParameter("sexo");
              int pagina = obtenerPagina(request);
              int inicio = (pagina - 1) * REGISTROS_POR_PAGINA;
              ClientesFiltros filtro = new ClientesFiltros();
              filtro.setBusqueda(busqueda);
              filtro.setEstado(estadoFiltro);
               filtro.setSexo(sexoFiltro);
               List<Cliente> listaClientes;
             int totalClientes;
          boolean hayFiltros =
              (busqueda != null && !busqueda.trim().isEmpty()) ||
               (estadoFiltro != null && !estadoFiltro.isEmpty()) ||
              (sexoFiltro != null && !sexoFiltro.isEmpty());
            if (hayFiltros) {
                   listaClientes = clienteNegocio.filtrar(filtro);
                 totalClientes = listaClientes.size();
                pagina = 1;
            }else {
              listaClientes = clienteNegocio.listarPaginados(inicio, REGISTROS_POR_PAGINA);
             totalClientes = clienteNegocio.contarTotalClientes();
           }
               int totalPaginas = (int) Math.ceil((double) totalClientes / REGISTROS_POR_PAGINA);
             request.setAttribute("listaClientes", listaClientes);
               request.setAttribute("paginaActual", pagina);
               request.setAttribute("totalPaginas", totalPaginas);
               request.setAttribute("estadoFiltro", estadoFiltro);
             request.setAttribute("sexoFiltro", sexoFiltro);
                request.setAttribute("busqueda", busqueda);
             request.getRequestDispatcher("AdminClientes.jsp").forward(request, response);

        }catch (Exception e) {
                 request.setAttribute("error", "Error al listar clientes: " + e.getMessage());
          e.printStackTrace();
           request.getRequestDispatcher("AdminClientes.jsp").forward(request, response);
        }
    }
    private int obtenerPagina(HttpServletRequest request) {
        String paginaParam = request.getParameter("pagina");
        try {
            return paginaParam != null ? Integer.parseInt(paginaParam) : 1;
        } catch (NumberFormatException e) {
            return 1; // Valor por defecto si hay error en el parámetro
        }
    }
}