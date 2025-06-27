package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.ClientesDaoImpl;
import datosImpl.Conexion;
import entidades.Cliente;
import entidades.TiposUsuario;
import entidades.Usuario;
import negocio.ClientesNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.ClientesNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class AgregarCliente
 */
@WebServlet("/AgregarCliente")
public class AgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientesNegocio clientesNegocio;
	private UsuarioNegocio usuarioNegocio;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgregarCliente() {
		super();
		this.clientesNegocio = new ClientesNegocioImpl();
		this.usuarioNegocio = new UsuarioNegocioImpl();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("btnGuardar") != null) {


			String dni = request.getParameter("DNI").trim();
			String cuil = request.getParameter("CUIL").trim();
			String nombre = request.getParameter("Nombre").trim();
			String apellido = request.getParameter("Apellido").trim();
			String sexo = request.getParameter("Sexo").trim();
			String nacionalidad = request.getParameter("Nacionalidad").trim();
			String fechaNac = request.getParameter("FechaNac").trim();
			String direccion = request.getParameter("Direccion").trim();
			String localidad = request.getParameter("Localidad").trim();
			String provincia = request.getParameter("Provincia").trim();
			String email = request.getParameter("Email").trim();
			String telefono = request.getParameter("Telefono").trim();
			String usuario = request.getParameter("Usuario").trim();
			String contrasenia = request.getParameter("Contrasenia").trim();

			System.out.println(dni);
			System.out.println(cuil);
			System.out.println(nombre);
			System.out.println(apellido);
			System.out.println(sexo);
			System.out.println(nacionalidad);
			System.out.println(telefono);
			System.out.println(email);
			System.out.println(fechaNac);
			System.out.println(direccion);
			System.out.println(localidad);
			System.out.println(provincia);
			System.out.println(usuario);
			System.out.println(contrasenia);

			Connection conexion = null;
			boolean operacionExitosa = false;

			try {

				conexion = Conexion.getConexion().getSQLConexion();

				if (conexion != null) {
					conexion.setAutoCommit(false);
				} else {
					System.err.println("Error: No se pudo establecer conexión a la base de datos.");
					request.setAttribute("mensaje", "Error interno del servidor: Fallo de conexión.");
					request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
					return;
				}

				Cliente clienteNuevo = new Cliente();
				clienteNuevo.setDni(dni);
				clienteNuevo.setCuil(cuil);
				clienteNuevo.setNombre(nombre);
				clienteNuevo.setApellido(apellido);
				clienteNuevo.setSexo(sexo);
				clienteNuevo.setNacionalidad(nacionalidad);
				clienteNuevo.setFechaNac(fechaNac);
				clienteNuevo.setDireccion(direccion);
				clienteNuevo.setLocalidad(localidad);
				clienteNuevo.setProvincia(provincia);
				clienteNuevo.setEmail(email);
				clienteNuevo.setTelefono(telefono);
				clienteNuevo.setEstado(true);

				if (clientesNegocio.crear(clienteNuevo)) {
					System.out.println(
							"DEBUG: Cliente insertado en DB, IdCliente generado: " + clienteNuevo.getIdCliente());

					Usuario nuevoUsuario = new Usuario();
					nuevoUsuario.setUsuario(usuario);
					nuevoUsuario.setPassword(contrasenia);
					nuevoUsuario.setEstado(true);

					nuevoUsuario.setCliente(clienteNuevo);

					TiposUsuario tipoUsuarioCliente = new TiposUsuario();
					tipoUsuarioCliente.setIdTipoUsuario(2);
					nuevoUsuario.setTipoUsuario(tipoUsuarioCliente);

					if (usuarioNegocio.agregarUsuario(nuevoUsuario)) {

						conexion.commit();
						operacionExitosa = true;
						System.out.println("Cliente y Usuario agregados exitosamente. Transacción confirmada.");
						request.setAttribute("mensaje", "Cliente y Usuario registrados con éxito!");
						response.sendRedirect(request.getContextPath() + "/ListarClientesServlet");
					} else {

						System.err.println("ERROR: No se pudo agregar el usuario. Realizando rollback.");
						request.setAttribute("mensaje", "Error al registrar el usuario. El cliente no fue guardado.");
						conexion.rollback();
					}
				} else {

					System.err.println("ERROR: No se pudo agregar el cliente. Realizando rollback.");
					request.setAttribute("mensaje", "Error al registrar el cliente. No se guardó nada.");
					conexion.rollback();
				}

			} catch (SQLException e) {
				System.err.println("Error de SQL durante la transacción: " + e.getMessage());
				e.printStackTrace();
				request.setAttribute("mensaje", "Error de base de datos. Por favor, intente de nuevo más tarde.");
				try {
					if (conexion != null && !conexion.getAutoCommit()) {
						conexion.rollback();
						System.out.println("DEBUG: Rollback realizado debido a SQLException.");
					}
				} catch (SQLException rb_e) {
					System.err.println("Error al realizar rollback después de SQLException: " + rb_e.getMessage());
				}
			} catch (Exception e) {

				System.err.println("Error inesperado al procesar registro de cliente/usuario: " + e.getMessage());
				e.printStackTrace();
				request.setAttribute("mensaje", "Ha ocurrido un error inesperado. Por favor, intente de nuevo.");
				try {
					if (conexion != null && !conexion.getAutoCommit()) {
						conexion.rollback();
						System.out.println("DEBUG: Rollback realizado debido a Exception general.");
					}
				} catch (SQLException rb_e) {
					System.err.println("Error al realizar rollback después de Exception general: " + rb_e.getMessage());
				}
			} finally {

				try {
					if (conexion != null) {
					    conexion.setAutoCommit(true);
					    System.out.println("DEBUG: Auto-commit restaurado.");
					}
				} catch (SQLException e) {
					System.err
							.println("Error al cerrar conexión o restaurar auto-commit en finally: " + e.getMessage());
				}

				if (!operacionExitosa && !response.isCommitted()) {
					request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
				}
			}
		} else {

			response.sendRedirect(request.getContextPath() + "/ListarClientesServlet");
		}
	}
}
