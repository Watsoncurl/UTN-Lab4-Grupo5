package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import entidades.Cliente;
import negocio.ClientesNegocio;
import negocioImpl.ClientesNegocioImpl;

@WebServlet("/ServletEditarCliente")
public class ServletEditarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClientesNegocio clienteNegocio;

    @Override
    public void init() throws ServletException {
        super.init();
        clienteNegocio = new ClientesNegocioImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        String modo = request.getParameter("modo");
        
        try {
            // Validación básica
            if (idParam == null || idParam.isEmpty()) {
                manejarError(request, response, "ID de cliente no proporcionado");
                return;
            }
            
            int id = Integer.parseInt(idParam);
            Cliente cliente = clienteNegocio.obtenerPorId(id);
            
            if (cliente == null) {
                manejarError(request, response, "Cliente no encontrado");
                return;
            }
            
            // Establecer atributos para la vista
            request.setAttribute("cliente", cliente);
            
            // Determinar el modo (con valor por defecto "ver")
            String modoVista = "ver".equals(modo) ? "ver" : "editar".equals(modo) ? "editar" : "ver";
            request.setAttribute("modo", modoVista);
            
            // Redirigir a la vista
            request.getRequestDispatcher("AdminEditarCliente.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            manejarError(request, response, "ID de cliente inválido");
        } catch (Exception e) {
            manejarError(request, response, "Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Verificar si es un cambio de modo
        if ("true".equals(request.getParameter("cambiarModo"))) {
            String id = request.getParameter("idCliente");
            String modo = request.getParameter("nuevoModo");
            response.sendRedirect("ServletEditarCliente?id=" + id + "&modo=" + modo);
            return;
        }
        
        // Procesar actualización del cliente
        try {
            Cliente clienteActualizado = mapearClienteDesdeRequest(request);
            
            boolean exito = clienteNegocio.actualizar(clienteActualizado);
            
            if (exito) {
                request.getSession().setAttribute("mensaje", "Cliente actualizado exitosamente.");
                // Redirigir a la vista en modo visualización después de editar
                response.sendRedirect("ServletEditarCliente?id=" + clienteActualizado.getIdCliente() + "&modo=ver");
            } else {
                request.getSession().setAttribute("error", "No se pudo actualizar el cliente.");
                // Volver a la vista de edición con los datos ingresados
                request.setAttribute("cliente", clienteActualizado);
                request.setAttribute("modo", "editar");
                request.getRequestDispatcher("AdminEditarCliente.jsp").forward(request, response);
            }
            
        } catch (NumberFormatException e) {
            manejarError(request, response, "Error en el formato de los datos: " + e.getMessage());
        } catch (Exception e) {
            manejarError(request, response, "Error al actualizar cliente: " + e.getMessage());
        }
    }

    private Cliente mapearClienteDesdeRequest(HttpServletRequest request) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
        cliente.setDni(request.getParameter("dni"));
        cliente.setCuil(request.getParameter("cuil"));
        cliente.setNombre(request.getParameter("nombre"));
        cliente.setApellido(request.getParameter("apellido"));
        cliente.setSexo(request.getParameter("sexo"));
        cliente.setNacionalidad(request.getParameter("nacionalidad"));
        cliente.setFechaNac(request.getParameter("fechaNac"));
        cliente.setDireccion(request.getParameter("direccion"));
        cliente.setLocalidad(request.getParameter("localidad"));
        cliente.setProvincia(request.getParameter("provincia"));
        cliente.setEmail(request.getParameter("email"));
        cliente.setTelefono(request.getParameter("telefono"));
        cliente.setEstado(true); // Siempre true porque solo editamos clientes activos
        
        return cliente;
    }
	
	private void manejarError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws IOException {
        request.getSession().setAttribute("error", mensaje);
        response.sendRedirect("ListarClientesServlet");
    }
}
