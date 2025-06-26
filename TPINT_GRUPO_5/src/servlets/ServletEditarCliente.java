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

	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ID recibido: " + request.getParameter("id"));

		try {
			String idParam = request.getParameter("id");
			if (idParam != null && !idParam.isEmpty()) {
				int id = Integer.parseInt(idParam);
				ClientesNegocio ClienteNegocio = new ClientesNegocioImpl();
				Cliente cliente = ClienteNegocio.obtenerPorId(id);
				if (cliente != null) {
					request.setAttribute("cliente", cliente);
					request.getRequestDispatcher("AdminEditarCliente.jsp").forward(request, response);
				} else {
					request.getSession().setAttribute("error", "Cliente no encontrado");
					response.sendRedirect("ListarClientesServlet");
				}
			} else {
				request.getSession().setAttribute("error", "ID inválido");
				response.sendRedirect("ListarClientesServlet");
			}

		} catch (Exception e) {
			request.getSession().setAttribute("error", "Error al cargar cliente: " + e.getMessage());
			response.sendRedirect("ListarClientesServlet");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DEBUG ServletEditarCliente (POST): Procesando actualización de cliente.");
		ClientesNegocio ClienteNegocio = new ClientesNegocioImpl();

		try {
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			String dni = request.getParameter("dni");
			String cuil = request.getParameter("cuil");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String sexo = request.getParameter("sexo");
			String nacionalidad = request.getParameter("nacionalidad");
			String fechaNac = request.getParameter("fechaNac");
			String direccion = request.getParameter("direccion");
			String localidad = request.getParameter("localidad");
			String provincia = request.getParameter("provincia");
			String email = request.getParameter("email");
			String telefono = request.getParameter("telefono");

			Cliente clienteActualizado = new Cliente();
			clienteActualizado.setIdCliente(idCliente);
			clienteActualizado.setDni(dni);
			clienteActualizado.setCuil(cuil);
			clienteActualizado.setNombre(nombre);
			clienteActualizado.setApellido(apellido);
			clienteActualizado.setSexo(sexo);
			clienteActualizado.setNacionalidad(nacionalidad);
			clienteActualizado.setFechaNac(fechaNac);
			clienteActualizado.setDireccion(direccion);
			clienteActualizado.setLocalidad(localidad);
			clienteActualizado.setProvincia(provincia);
			clienteActualizado.setEmail(email);
			clienteActualizado.setTelefono(telefono);
			clienteActualizado.setEstado(true);

			boolean exito = ClienteNegocio.actualizar(clienteActualizado);

			if (exito) {
				request.getSession().setAttribute("mensaje", "Cliente actualizado exitosamente.");
				response.sendRedirect("ListarClientesServlet");
			} else {
				request.getSession().setAttribute("error", "No se pudo actualizar el cliente. Intente nuevamente.");
				response.sendRedirect("ListarClientesServlet");
			}

		} catch (NumberFormatException e) {
			request.getSession().setAttribute("error",
					"Error en el formato de algún dato del formulario: " + e.getMessage());
			response.sendRedirect("ListarClientesServlet");
		} catch (Exception e) {
			System.err.println("Error en ServletEditarCliente (POST): " + e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("error", "Error al actualizar cliente: " + e.getMessage());
			response.sendRedirect("ListarClientesServlet");
		}
	}
}
