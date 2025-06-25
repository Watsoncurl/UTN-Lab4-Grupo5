package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import negocio.ClientesNegocio;
import negocioImpl.ClientesNegocioImpl;

@WebServlet("/admin/clientes")
public class ClientesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClientesNegocio clientesNegocio;

    @Override
    public void init() throws ServletException {
        super.init();
        clientesNegocio = new ClientesNegocioImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        String mensaje = null;

        if ("eliminar".equals(accion)) {
            int idEliminar = Integer.parseInt(request.getParameter("id"));
            boolean eliminado = clientesNegocio.eliminarCliente(idEliminar);
            mensaje = eliminado ? "Cliente eliminado correctamente" : "Error al eliminar cliente";
        }

        // Obtener parámetros de filtro
        String filtro = request.getParameter("filtro");
        String estado = request.getParameter("estado");
        String sexo = request.getParameter("sexo");

        List<Cliente> clientes;

        if ((filtro == null || filtro.isEmpty()) &&
            (estado == null || estado.isEmpty()) &&
            (sexo == null || sexo.isEmpty()) &&
            (accion == null)) {
            
            clientes = clientesNegocio.listarTodos();

        } else if (filtro != null && !filtro.isEmpty()) {
            clientes = clientesNegocio.buscarClientes(filtro);
        } else if (estado != null && !estado.isEmpty()) {
            clientes = clientesNegocio.filtrarPorEstado(Boolean.parseBoolean(estado));
        } else if (sexo != null && !sexo.isEmpty()) {
            clientes = clientesNegocio.filtrarPorSexo(sexo);
        } else {
            clientes = clientesNegocio.listarTodos();
        }

        request.setAttribute("clientes", clientes);
        if (mensaje != null) {
            request.setAttribute("mensaje", mensaje);
        }

        request.getRequestDispatcher("/AdminClientes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if ("agregar".equals(accion)) {
                Cliente nuevoCliente = new Cliente(
                        0,
                        request.getParameter("dni"),
                        request.getParameter("cuil"),
                        request.getParameter("nombre"),
                        request.getParameter("apellido"),
                        request.getParameter("sexo"),
                        request.getParameter("nacionalidad"),
                        LocalDate.parse(request.getParameter("fechaNac")),
                        request.getParameter("direccion"),
                        request.getParameter("localidad"),
                        request.getParameter("provincia"),
                        request.getParameter("email"),
                        request.getParameter("telefono"),
                        Boolean.parseBoolean(request.getParameter("estado"))
                );

                boolean agregado = clientesNegocio.agregarCliente(nuevoCliente);
                request.setAttribute("mensaje", agregado ? "Cliente agregado correctamente" : "Error al agregar cliente");

            } else if ("editar".equals(accion)) {
                Cliente clienteActualizado = new Cliente(
                        Integer.parseInt(request.getParameter("idCliente")),
                        request.getParameter("dni"),
                        request.getParameter("cuil"),
                        request.getParameter("nombre"),
                        request.getParameter("apellido"),
                        request.getParameter("sexo"),
                        request.getParameter("nacionalidad"),
                        LocalDate.parse(request.getParameter("fechaNac")),
                        request.getParameter("direccion"),
                        request.getParameter("localidad"),
                        request.getParameter("provincia"),
                        request.getParameter("email"),
                        request.getParameter("telefono"),
                        Boolean.parseBoolean(request.getParameter("estado"))
                );

                boolean editado = clientesNegocio.editarCliente(clienteActualizado);
                request.setAttribute("mensaje", editado ? "Cliente actualizado correctamente" : "Error al actualizar cliente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Ocurrió un error al procesar el cliente.");
        }

        doGet(request, response);
    }
}
