package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;  
import entidades.Cliente;
import entidades.Provincia;
import entidades.Localidad;
import negocio.ClientesNegocio;
import negocio.ProvinciaNegocio;
import negocio.LocalidadNegocio;
import negocioImpl.ClientesNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;

@WebServlet("/ServletEditarCliente")
public class ServletEditarCliente extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ClientesNegocio clienteNegocio;
    private ProvinciaNegocio provinciaNegocio;
    private LocalidadNegocio localidadNegocio;

    @Override
    public void init() throws ServletException {
        super.init();
        clienteNegocio = new ClientesNegocioImpl();
        provinciaNegocio = new ProvinciaNegocioImpl();
        localidadNegocio = new LocalidadNegocioImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String modo = request.getParameter("modo");
        try {
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

            List<Provincia> provincias = provinciaNegocio.obtenerTodas();
            request.setAttribute("provincias", provincias);

            int idProvincia = 0;
            if (cliente.getProvincia() != null && !cliente.getProvincia().isEmpty()) {
                idProvincia = provinciaNegocio.obtenerIdPorNombre(cliente.getProvincia());
            }
            cliente.setIdProvincia(idProvincia);

            List<Localidad> localidades = localidadNegocio.obtenerPorProvincia(idProvincia);
            request.setAttribute("localidades", localidades);

            if (cliente.getIdLocalidad() != 0) {
                Localidad loc = localidadNegocio.obtenerPorId(cliente.getIdLocalidad());
                if (loc != null) {
                    cliente.setLocalidadNombre(loc.getNombre());
                }
            }

            if (cliente.getIdProvincia() > 0) {
                String nombreProvincia = provinciaNegocio.obtenerNombrePorId(cliente.getIdProvincia());
                cliente.setProvincia(nombreProvincia);
            }

            request.setAttribute("cliente", cliente);
            String modoVista = "editar".equalsIgnoreCase(modo) ? "editar" : "ver";
            request.setAttribute("modo", modoVista);
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

        if ("true".equals(request.getParameter("cambiarModo"))) {
            String id = request.getParameter("idCliente");
            String nuevoModo = request.getParameter("nuevoModo");
            response.sendRedirect("ServletEditarCliente?id=" + id + "&modo=" + nuevoModo);
            return;
        }

        try {
            Cliente clienteActualizado = mapearClienteDesdeRequest(request);

            if (clienteActualizado.getIdProvincia() > 0) {
                String nombreProvincia = provinciaNegocio.obtenerNombrePorId(clienteActualizado.getIdProvincia());
                clienteActualizado.setProvincia(nombreProvincia);
            }

            if (clienteActualizado.getIdLocalidad() > 0) {
                Localidad loc = localidadNegocio.obtenerPorId(clienteActualizado.getIdLocalidad());
                if (loc != null) {
                    clienteActualizado.setLocalidadNombre(loc.getNombre());
                }
            }

            boolean exito = clienteNegocio.actualizar(clienteActualizado);

            if (exito) {
                request.getSession().setAttribute("mensaje", "Cliente actualizado exitosamente.");
            } else {
                request.getSession().setAttribute("error", "No se pudo actualizar el cliente.");
            }
        // Redirige a ListarClientesServlet con parámetros para mantener filtros y paginación
            StringBuilder redirectURL = new StringBuilder("ListarClientesServlet?recargar=true");
            String busqueda = request.getParameter("busqueda");
            String estadoFiltro = request.getParameter("estado");
            String sexoFiltro = request.getParameter("sexo");
            String pagina = request.getParameter("pagina");

            if (busqueda != null && !busqueda.isEmpty()) {
                redirectURL.append("&busqueda=").append(busqueda);
            }
            if (estadoFiltro != null && !estadoFiltro.isEmpty()) {
                redirectURL.append("&estado=").append(estadoFiltro);
            }
            if (sexoFiltro != null && !sexoFiltro.isEmpty()) {
                redirectURL.append("&sexo=").append(sexoFiltro);
            }
            if (pagina != null && !pagina.isEmpty()) {
                redirectURL.append("&pagina=").append(pagina);
            }

            response.sendRedirect(redirectURL.toString());

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
        String localidadStr = request.getParameter("localidad");
        if (localidadStr != null && !localidadStr.isEmpty()) {
            try {
                cliente.setIdLocalidad(Integer.parseInt(localidadStr));
            } catch (NumberFormatException e) {
                cliente.setIdLocalidad(0);
            }
        }

        String provinciaStr = request.getParameter("provincia");
        if (provinciaStr != null && !provinciaStr.isEmpty()) {
            try {
                cliente.setIdProvincia(Integer.parseInt(provinciaStr));
            } catch (NumberFormatException e) {
                cliente.setIdProvincia(0);
            }
        }
        cliente.setEmail(request.getParameter("email"));
        cliente.setTelefono(request.getParameter("telefono"));
        cliente.setEstado(true);
        return cliente;
    }

    private void manejarError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws IOException, ServletException {
        request.getSession().setAttribute("error", mensaje);
        response.sendRedirect("ListarClientesServlet");
    }
}